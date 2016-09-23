package Controllers;

import Entities.Cozinha;
import Entities.Quarto;
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
@WebServlet("/List/Quarto")
public class ListQuartoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("basePath", GetServerBasePath(request));
        request.setAttribute("list", GetQuartos());
        RequestDispatcher view = request.getRequestDispatcher("ListQuarto.jsp");
        view.forward(request, response);
    }

    private String GetServerBasePath(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        return requestUrl.substring(0, requestUrl.indexOf(request.getServletPath()));
    }

    private JSONArray GetQuartos(){
        JSONArray array = new JSONArray();

        for (Iterator it = SessionFactoryBuilder.GetObjects(Quarto.class).iterator();
                it.hasNext();){
            Quarto quarto = (Quarto) it.next();
            JSONObject obj = new JSONObject();

            obj.put("Id", quarto.getId());
            obj.put("Descrição", quarto.getDescription());

            array.put(obj);
        }

        return array;
    }
}
