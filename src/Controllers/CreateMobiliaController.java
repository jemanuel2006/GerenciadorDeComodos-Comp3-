package Controllers;

import Entities.Comodo;
import Entities.Mobilia;
import HibernateUtils.SessionFactoryBuilder;
import TransactionScripts.ComodoTransactions;
import TransactionScripts.MobiliaTransactions;
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
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by juane on 28/09/2016.
 */
@WebServlet("/Create/Mobilia")
public class CreateMobiliaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");

        float cost = Float.parseFloat(request.getParameter("cost"));
        int deliveryTime = Integer.parseInt(request.getParameter("deliveryTime"));

        String[] stringIds = request.getParameter("hfComodosIds").split(";");
        ArrayList<Integer> ids = new ArrayList<>();

        for(String id : stringIds){
            if (id != null && id.isEmpty() == false) {
                ids.add(Integer.parseInt(id));
            }
        }

        try {
            Mobilia novaMobilia = MobiliaTransactions.CreateMobilia(description, deliveryTime, cost, ids);

            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Mobilia criada com sucesso.");

            response.sendRedirect("/Edit/Mobilia?id=" + novaMobilia.getId());
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Ocorreu um erro ao criar a mobilia.");
            response.sendRedirect("/Create/Mobilia");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JSONArray comodos = GetComodosArray();
            request.setAttribute("Comodos", comodos);
        } catch (Exception e) {
            request.setAttribute("Comodos", new JSONArray());
            request.setAttribute("error", "Ocorreu um erro ao carregar a lista de comodos.");
        }

        RequestDispatcher view = request.getRequestDispatcher("CreateMobilia.jsp");
        view.forward(request, response);
    }

    private JSONArray GetComodosArray() throws Exception {
        JSONArray array = new JSONArray();

        for (Comodo comodo : ComodoTransactions.GetAllComodos()){
            JSONObject obj = new JSONObject();

            obj.put("Id", comodo.getId());
            obj.put("Descrição", comodo.getDescription());

            array.put(obj);
        }

        return array;
    }
}
