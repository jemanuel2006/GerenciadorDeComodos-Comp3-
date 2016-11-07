package Controllers;

import Entities.*;
import HibernateUtils.SessionFactoryBuilder;
import TransactionScripts.ComodoTransactions;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by juane on 10/10/2016.
 */
@WebServlet("/Create/ComodoComposto")
public class CreateComodoComposto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        String[] stringIds = request.getParameter("hfComodosIds").split(";");
        ArrayList<Integer> ids = new ArrayList<>();

        for(String id : stringIds){
            if (id.isEmpty() == false) {
                ids.add(Integer.parseInt(id));
            }
        }

        try {
            ComodoComposto novoComodoComposto = ComodoTransactions.CreateComodoComposto(description, ids);
            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Cômodo composto criado com sucesso.");
            response.sendRedirect("/Edit/ComodoComposto?id=" + novoComodoComposto.getId());
        } catch (Exception e) {
            request.setAttribute("error", "Ocorreu um erro ao criar o cômodo composto.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JSONArray comodos = GetComodosArray();
            request.setAttribute("Comodos", comodos);
        } catch (Exception e) {
            request.setAttribute("error", "Ocorreu um erro ao carregar os cômodos.");
        }

        RequestDispatcher view = request.getRequestDispatcher("CreateComodoComposto.jsp");
        view.forward(request, response);
    }

    private JSONArray GetComodosArray() throws Exception{
        JSONArray array = new JSONArray();
        ArrayList<Comodo> comodos = new ArrayList<>();
        comodos.addAll(ComodoTransactions.GetComodos(Cozinha.class));
        comodos.addAll(ComodoTransactions.GetComodos(Sala.class));
        comodos.addAll(ComodoTransactions.GetComodos(Quarto.class));
        comodos.addAll(ComodoTransactions.GetComodos(ComodoComposto.class));

        for (Comodo comodo : comodos){
            JSONObject obj = new JSONObject();

            obj.put("Id", comodo.getId());
            obj.put("Descrição", comodo.getDescription());

            array.put(obj);
        }

        return array;
    }
}
