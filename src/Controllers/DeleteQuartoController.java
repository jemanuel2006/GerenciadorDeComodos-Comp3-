package Controllers;

import Entities.Cozinha;
import Entities.Quarto;
import HibernateUtils.SessionFactoryBuilder;
import Utils.QueryStringHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by juane on 20/09/2016.
 */
@WebServlet("/Delete/Quarto")
public class DeleteQuartoController extends HttpServlet {
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map atts = QueryStringHelper.getQueryMap(request.getQueryString());
        int id = Integer.parseInt(atts.get("id").toString());
        SessionFactoryBuilder.DeleteObjectById(Quarto.class, id);
        response.getWriter().write("Ok!");
    }
}
