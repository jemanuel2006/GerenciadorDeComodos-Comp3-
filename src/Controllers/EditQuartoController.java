package Controllers;

import Entities.Comodo;
import Entities.Quarto;
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
@WebServlet("/Edit/Quarto")
public class EditQuartoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("hfId"));

        try {
            ComodoTransactions.UpdateComodo(Quarto.class, id,description);
            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Sala atualizada com sucesso.");
        } catch (Exception e) {
            request.getSession().setAttribute("success", false);
            request.getSession().setAttribute("message", "Ocorreu um erro ao atualizar o quarto.");
        }

        response.sendRedirect("/Edit/Quarto?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> params = QueryStringHelper.getQueryMap(request.getQueryString());
        int id = Integer.parseInt(params.get("id"));

        try {
            Quarto quarto = ComodoTransactions.GetComodo(Quarto.class, id);
            request.setAttribute("hfId", id);
            request.setAttribute("description", quarto.getDescription());
        } catch (Exception e) {
            request.setAttribute("error", "Ocorreu um erro ao carregar o quarto.");
        }

        RequestDispatcher view = request.getRequestDispatcher("EditQuarto.jsp");
        view.forward(request, response);
    }
}
