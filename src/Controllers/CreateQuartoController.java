package Controllers;

import Entities.Quarto;
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
 * Created by juane on 18/09/2016.
 */
@WebServlet("/Create/Quarto")
public class CreateQuartoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");

        try {
            Quarto novoQuarto = ComodoTransactions.CreateComodo(Quarto.class, description);
            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Quarto criado com sucesso.");

            response.sendRedirect("/Edit/Quarto?id=" + novoQuarto.getId());
        } catch (Exception e) {
            request.getSession().setAttribute("success", false);
            request.getSession().setAttribute("message", "Ocorreu um erro ao criar o quarto.");
            response.sendRedirect("/Create/Quarto");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("CreateQuarto.jsp");
        view.forward(request, response);
    }
}
