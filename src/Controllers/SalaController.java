package Controllers;

import Entities.Cozinha;
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
@WebServlet("/Salas")
public class SalaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");

        Sala novaSala = new Sala();
        novaSala.set_description(description);

        SessionFactoryBuilder.SaveObject(novaSala);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("Views/EditSala.jsp");
        view.forward(request, response);
    }
}
