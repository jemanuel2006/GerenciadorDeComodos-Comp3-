package Controllers;

import Entities.Cozinha;
import HibernateUtils.SessionFactoryBuilder;
import TransactionScripts.ComodoTransactions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by juane on 05/09/2016.
 */
@WebServlet("/Create/Cozinha")
public class CreateCozinhaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        try {
            Cozinha novaCozinha = ComodoTransactions.CreateComodo(Cozinha.class, description);
            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Cozinha criada com sucesso.");
            response.sendRedirect("/Edit/Cozinha?id=" + novaCozinha.getId());
        } catch (Exception e) {
            request.getSession().setAttribute("success", false);
            request.getSession().setAttribute("message", "Ocorreu um erro ao criar a cozinha.");
            response.sendRedirect("/Create/Cozinha");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("CreateCozinha.jsp");
        view.forward(request, response);
    }
}
