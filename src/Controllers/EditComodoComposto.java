package Controllers;

import Entities.*;
import HibernateUtils.SessionFactoryBuilder;
import TransactionScripts.ComodoTransactions;
import Utils.QueryStringHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by juane on 10/10/2016.
 */
@WebServlet("/Edit/ComodoComposto")
public class EditComodoComposto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("hfId"));
        String description = request.getParameter("description");

        String[] stringIds = request.getParameter("hfComodosIds").split(";");
        ArrayList<Integer> ids = new ArrayList<>();

        for(String stringId : stringIds){
            if (stringId.isEmpty() == false) {
                ids.add(Integer.parseInt(stringId));
            }
        }

        try {
            ComodoTransactions.UpdateComodoComposto(id,description,ids);
            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Cômodo Composto alterado com sucesso.");
        } catch (Exception e) {
            request.getSession().setAttribute("success", false);
            request.getSession().setAttribute("message", "Ocorreu um erro ao alterar o cômodo.");
        }

        response.sendRedirect("/Edit/ComodoComposto?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
        int id = Integer.parseInt(params.get("id"));
        List<String> ids = new ArrayList<>();
        JSONArray comodosArray = new JSONArray();

        try {
            ComodoComposto comodoComposto = ComodoTransactions.GetComodoComposto(id);
            request.setAttribute("hfId", id);
            request.setAttribute("description", comodoComposto.getDescription());

            Collection<Comodo> comodos = new ArrayList<>();
            comodos.addAll(ComodoTransactions.GetComodos(Cozinha.class));
            comodos.addAll(ComodoTransactions.GetComodos(Sala.class));
            comodos.addAll(ComodoTransactions.GetComodos(Quarto.class));
            comodos.addAll(ComodoTransactions.GetComodos(ComodoComposto.class));

            for (Comodo comodo : comodos){
                if(comodo.getId() == id)
                    continue;

                JSONObject obj = new JSONObject();

                obj.put("Id", comodo.getId());
                obj.put("Descrição", comodo.getDescription());

                comodosArray.put(obj);

                if(comodoComposto.ContainsComodo(comodo)){
                    ids.add(Integer.toString(comodo.getId()));
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Ocorreu um erro ao carregar o Comodo Composto.");
        }

        request.setAttribute("Comodos", comodosArray);
        request.setAttribute("hfComodosIds", String.join(";",ids));
        RequestDispatcher view = request.getRequestDispatcher("EditComodoComposto.jsp");
        view.forward(request, response);
    }

    private Comodo GetComodo(List<Comodo> list, int id){
        for (Comodo c: list) {
            if(c.getId() == id)
                return c;
        }
        return null;
    }
}
