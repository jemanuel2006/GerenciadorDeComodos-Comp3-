package Controllers;

import Entities.Sala;
import HibernateUtils.SessionFactoryBuilder;
import TransactionScripts.ComodoTransactions;
import Utils.QueryStringHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by juane on 18/09/2016.
 */
@WebServlet("/Edit/Sala")
public class EditSalaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("hfId"));

        try {
            ComodoTransactions.UpdateComodo(Sala.class, id, description);
            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Sala alterada com sucesso.");
        } catch (Exception e){
            request.getSession().setAttribute("success", false);
            request.getSession().setAttribute("message", "Ocorreu um erro ao alterar a sala.");
        }

        response.sendRedirect("/Edit/Sala?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
        int id = Integer.parseInt(params.get("id"));
        Sala sala = null;
        try {
            sala = ComodoTransactions.GetComodo(Sala.class, id);
        } catch (Exception e){

        }

        request.setAttribute("hfId", id);
        request.setAttribute("description", sala.getDescription());

        RequestDispatcher view = request.getRequestDispatcher("EditSala.jsp");
        view.forward(request, response);
    }
}
