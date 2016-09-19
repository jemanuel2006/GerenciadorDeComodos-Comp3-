package Controllers;

import Entities.Quarto;
import Entities.Sala;
import HibernateUtils.SessionFactoryBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by juane on 18/09/2016.
 */
public class QuartoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");

        Quarto novoQuarto = new Quarto();
        novoQuarto.set_description(description);

        SessionFactoryBuilder.SaveObject(novoQuarto);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("Views/EditQuarto.jsp");
        view.forward(request, response);
    }
}
