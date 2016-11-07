package Controllers;

import Entities.ComodoComposto;
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
import java.util.Iterator;

/**
 * Created by juane on 10/10/2016.
 */
@WebServlet("/List/ComodoComposto")
public class ListComodoComposto extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("basePath", GetServerBasePath(request));
        try {
            request.setAttribute("list", GetComodosCompostos());
        } catch (Exception e) {
            request.setAttribute("error", "Ocorreu um erro ao carregar a lista de Comodos Compostos.");
        }
        RequestDispatcher view = request.getRequestDispatcher("ListComodoComposto.jsp");
        view.forward(request, response);
    }

    private String GetServerBasePath(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        return requestUrl.substring(0, requestUrl.indexOf(request.getServletPath()));
    }

    private JSONArray GetComodosCompostos() throws Exception{
        JSONArray array = new JSONArray();

        for (ComodoComposto comodoComposto : ComodoTransactions.GetComodos(ComodoComposto.class)){
            JSONObject obj = new JSONObject();

            obj.put("Id", comodoComposto.getId());
            obj.put("Descrição", comodoComposto.getDescription());

            array.put(obj);
        }

        return array;
    }
}
