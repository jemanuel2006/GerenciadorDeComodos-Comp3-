package Controllers;

import Entities.Cozinha;
import Entities.Sala;
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
 * Created by juane on 19/09/2016.
 */
@WebServlet("/List/Sala")
public class ListSalaController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("basePath", GetServerBasePath(request));
        request.setAttribute("list", GetSalas());
        RequestDispatcher view = request.getRequestDispatcher("ListSala.jsp");
        view.forward(request, response);
    }

    private String GetServerBasePath(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        return requestUrl.substring(0, requestUrl.indexOf(request.getServletPath()));
    }

    private JSONArray GetSalas(){
        JSONArray array = new JSONArray();

        for (Iterator it = SessionFactoryBuilder.GetObjects(Sala.class).iterator();
             it.hasNext();){
            Sala sala = (Sala) it.next();
            JSONObject obj = new JSONObject();

            obj.put("Id", sala.getId());
            obj.put("Descrição", sala.getDescription());

            array.put(obj);
        }

        return array;
    }
}
