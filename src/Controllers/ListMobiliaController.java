package Controllers;

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
import java.util.Iterator;

/**
 * Created by juane on 23/09/2016.
 */
@WebServlet("/List/Mobilia")
public class ListMobiliaController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("basePath", GetServerBasePath(request));
        request.setAttribute("list", GetMobilias());
        RequestDispatcher view = request.getRequestDispatcher("ListMobilia.jsp");
        view.forward(request, response);
    }

    private String GetServerBasePath(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        return requestUrl.substring(0, requestUrl.indexOf(request.getServletPath()));
    }

    private JSONArray GetMobilias(){
        JSONArray array = new JSONArray();

        for (Iterator it = SessionFactoryBuilder.GetObjects(Mobilia.class).iterator();
             it.hasNext();){
            Mobilia mobilia = (Mobilia) it.next();
            JSONObject obj = new JSONObject();

            obj.put("Id", mobilia.getId());
            obj.put("Descrição", mobilia.getDescription());
            obj.put("Custo", mobilia.getCost());
            obj.put("Tempo", mobilia.getDeliveryTime());

            array.put(obj);
        }

        return array;
    }
}
