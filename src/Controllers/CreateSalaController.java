package Controllers;

import Entities.Sala;
import HibernateUtils.SessionFactoryBuilder;

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

        Sala novaSala = new Sala();
        novaSala.setDescription(description);

        SessionFactoryBuilder.SaveObject(novaSala);

        request.getSession().setAttribute("success", true);
        request.getSession().setAttribute("message", "Sala criada com sucesso.");

        response.sendRedirect("/Edit/Sala?id=" + novaSala.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("CreateSala.jsp");
        view.forward(request, response);
    }
}
