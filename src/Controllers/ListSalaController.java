package Controllers;

import Entities.Sala;
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

/**
 * Created by juane on 19/09/2016.
 */
@WebServlet("/List/Sala")
public class ListSalaController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("basePath", GetServerBasePath(request));
        //Colocar mensagem de erro na tela
        try {
            request.setAttribute("list", GetSalas());
        } catch (Exception e){
            request.setAttribute("list", new JSONArray());
        }

        RequestDispatcher view = request.getRequestDispatcher("ListSala.jsp");
        view.forward(request, response);
    }

    private String GetServerBasePath(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        return requestUrl.substring(0, requestUrl.indexOf(request.getServletPath()));
    }

    private JSONArray GetSalas() throws Exception{
        JSONArray array = new JSONArray();

        for (Sala sala : ComodoTransactions.GetComodos(Sala.class)){
            JSONObject obj = new JSONObject();

            obj.put("Id", sala.getId());
            obj.put("Descrição", sala.getDescription());

            array.put(obj);
        }

        return array;
    }
}
