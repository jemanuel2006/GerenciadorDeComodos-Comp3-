package Controllers;

import Entities.Cozinha;
import Entities.Quarto;
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
import java.sql.SQLException;
import java.util.Iterator;

/**
 * Created by juane on 19/09/2016.
 */
@WebServlet("/List/Quarto")
public class ListQuartoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("basePath", GetServerBasePath(request));
        try {
            request.setAttribute("list", GetQuartos());
        } catch (Exception e){
            request.setAttribute("error", "Ocorreu um erro ao carregar a lista de quartos.");
        }

        RequestDispatcher view = request.getRequestDispatcher("ListQuarto.jsp");
        view.forward(request, response);
    }

    private String GetServerBasePath(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        return requestUrl.substring(0, requestUrl.indexOf(request.getServletPath()));
    }

    private JSONArray GetQuartos() throws Exception{
        JSONArray array = new JSONArray();

        for (Quarto quarto : ComodoTransactions.GetComodos(Quarto.class)){
            JSONObject obj = new JSONObject();

            obj.put("Id", quarto.getId());
            obj.put("Descrição", quarto.getDescription());

            array.put(obj);
        }

        return array;
    }
}
