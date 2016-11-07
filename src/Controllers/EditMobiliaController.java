package Controllers;

import Entities.Comodo;
import Entities.Mobilia;
import HibernateUtils.SessionFactoryBuilder;
import TransactionScripts.ComodoTransactions;
import TransactionScripts.MobiliaTransactions;
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
 * Created by juane on 06/10/2016.
 */
@WebServlet("/Edit/Mobilia")
public class EditMobiliaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("hfId"));
        String description = request.getParameter("description");
        float cost = Float.parseFloat(request.getParameter("cost"));
        int deliveryTime = Integer.parseInt(request.getParameter("deliveryTime"));

        String[] stringIds = request.getParameter("hfComodosIds").split(";");
        ArrayList<Integer> ids = new ArrayList<>();

        for(String stringId : stringIds){
            if (stringId != null && stringId.isEmpty() == false) {
                ids.add(Integer.parseInt(stringId));
            }
        }

        try {
            MobiliaTransactions.UpdateMobilia(id,description,deliveryTime,cost, ids);

            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Mobilia alterada com sucesso.");
        } catch (Exception e) {
            request.getSession().setAttribute("success", false);
            request.getSession().setAttribute("message", "Ocorreu um erro ao salvar a mobilia");
        }

        response.sendRedirect("/Edit/Mobilia?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
        int id = Integer.parseInt(params.get("id"));

        try {
            Mobilia mobilia = MobiliaTransactions.GetMobilia(id);

            request.setAttribute("hfId", id);
            request.setAttribute("description", mobilia.getDescription());
            request.setAttribute("cost", mobilia.getCost());
            request.setAttribute("deliveryTime", mobilia.getDeliveryTime());

            Collection<Comodo> comodos = ComodoTransactions.GetAllComodos();
            List<String> ids = new ArrayList<>();
            JSONArray comodosArray = new JSONArray();

            for (Comodo comodo : comodos){
                JSONObject obj = new JSONObject();

                obj.put("Id", comodo.getId());
                obj.put("Descrição", comodo.getDescription());

                comodosArray.put(obj);

                if(comodo.ContainsMobilia(id)){
                    ids.add(Integer.toString(comodo.getId()));
                }
            }

            request.setAttribute("Comodos", comodosArray);
            request.setAttribute("hfComodosIds", String.join(";",ids));
        } catch (Exception e) {
            request.setAttribute("error", "Ocorreu um erro ao carregar a mobilia.");
        }
        RequestDispatcher view = request.getRequestDispatcher("EditMobilia.jsp");
        view.forward(request, response);
    }
}
