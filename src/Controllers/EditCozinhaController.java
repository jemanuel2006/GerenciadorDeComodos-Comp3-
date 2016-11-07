package Controllers;

import Entities.Cozinha;
import HibernateUtils.HibernateListener;
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
 * Created by juane on 05/09/2016.
 */
@WebServlet("/Edit/Cozinha")
public class EditCozinhaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("hfId"));

        try {
            ComodoTransactions.UpdateComodo(Cozinha.class, id, description);
            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Cozinha atualizada com sucesso.");
        } catch (Exception e) {
            request.getSession().setAttribute("success", false);
            request.getSession().setAttribute("message", "Ocorreu um erro ao alterar a cozinha.");
        }
        response.sendRedirect("/Edit/Cozinha?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
        int id = Integer.parseInt(params.get("id"));

        try {
            Cozinha cozinha = ComodoTransactions.GetComodo(Cozinha.class, id);
            request.setAttribute("hfId", id);
            request.setAttribute("description", cozinha.getDescription());

            RequestDispatcher view = request.getRequestDispatcher("EditCozinha.jsp");
            view.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Ocorreu um erro ao carregar a cozinha.");
            response.sendRedirect("/List/Cozinha");
        }
    }
}
