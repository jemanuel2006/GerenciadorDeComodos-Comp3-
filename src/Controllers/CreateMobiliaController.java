package Controllers;

import Entities.Comodo;
import Entities.Mobilia;
import HibernateUtils.SessionFactoryBuilder;
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
            ids.add(Integer.parseInt(id));
        }

        Mobilia novaMobilia = new Mobilia();
        novaMobilia.setDescription(description);
        novaMobilia.setCost(cost);
        novaMobilia.setDeliveryTime(deliveryTime);

        SessionFactoryBuilder.SaveObject(novaMobilia);

        for(int id : ids){
            Comodo comodo = (Comodo)SessionFactoryBuilder.GetObjectById(Comodo.class, id);
            comodo.AddMobilia(novaMobilia);
            SessionFactoryBuilder.SaveObject(comodo);
        }

        request.getSession().setAttribute("success", true);
        request.getSession().setAttribute("message", "Mobilia criada com sucesso.");

        response.sendRedirect("/Edit/Mobilia?id=" + novaMobilia.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray comodos = GetComodosArray();
        request.setAttribute("Comodos", comodos);

        RequestDispatcher view = request.getRequestDispatcher("CreateMobilia.jsp");
        view.forward(request, response);
    }

    private JSONArray GetComodosArray() {
        JSONArray array = new JSONArray();

        for (Iterator it = SessionFactoryBuilder.GetObjects(Comodo.class).iterator();
             it.hasNext();){
            Comodo comodo = (Comodo) it.next();
            JSONObject obj = new JSONObject();

            obj.put("Id", comodo.getId());
            obj.put("Descrição", comodo.getDescription());

            array.put(obj);
        }

        return array;
    }
}
