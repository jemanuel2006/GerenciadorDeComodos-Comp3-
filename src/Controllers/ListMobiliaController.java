package Controllers;

import Entities.Mobilia;
import HibernateUtils.SessionFactoryBuilder;
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
import java.util.Iterator;

/**
 * Created by juane on 23/09/2016.
 */
@WebServlet("/List/Mobilia")
public class ListMobiliaController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("basePath", GetServerBasePath(request));

        try {
            request.setAttribute("list", GetMobilias());
        } catch (Exception e) {
            request.setAttribute("error", "Ocorreu um erro ao carregar a lista de mobilias.");
            request.setAttribute("list", new JSONArray());
        }

        RequestDispatcher view = request.getRequestDispatcher("ListMobilia.jsp");
        view.forward(request, response);
    }

    private String GetServerBasePath(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        return requestUrl.substring(0, requestUrl.indexOf(request.getServletPath()));
    }

    private JSONArray GetMobilias() throws Exception{
        JSONArray array = new JSONArray();

        for (Mobilia mobilia : MobiliaTransactions.GetMobilias()){
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
