package Controllers;

import Entities.Sala;
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
@WebServlet("/Create/Sala")
public class CreateSalaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        try {
            Sala novaSala = ComodoTransactions.CreateComodo(Sala.class, description);
            request.getSession().setAttribute("success", true);
            request.getSession().setAttribute("message", "Sala criada com sucesso.");

            response.sendRedirect("/Edit/Sala?id=" + novaSala.getId());
        } catch (Exception e){
            request.getSession().setAttribute("success", false);
            request.getSession().setAttribute("message", "Ocorreu um erro ao criar a sala.");
            response.sendRedirect("/Create/Sala");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("CreateSala.jsp");
        view.forward(request, response);
    }
}
