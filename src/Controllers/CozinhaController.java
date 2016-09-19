package Controllers;

import Entities.Cozinha;
import HibernateUtils.SessionFactoryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by juane on 05/09/2016.
 */
@WebServlet("/Cozinhas")
public class CozinhaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");

        Cozinha novaCozinha = new Cozinha();
        novaCozinha.set_description(description);

        SessionFactoryBuilder.SaveObject(novaCozinha);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("Views/EditCozinha.jsp");
        view.forward(request, response);
    }
}
