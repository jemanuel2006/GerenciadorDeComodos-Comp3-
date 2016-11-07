package Controllers;

import Entities.Cozinha;
import Entities.Sala;
import HibernateUtils.SessionFactoryBuilder;
import TransactionScripts.ComodoTransactions;
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
@WebServlet("/Delete/Sala")
public class DeleteSalaController extends HttpServlet {
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map atts = QueryStringHelper.getQueryMap(request.getQueryString());
        int id = Integer.parseInt(atts.get("id").toString());
        try{
            ComodoTransactions.DeleteComodo(Sala.class, id);
            response.getWriter().write("Ok!");
        }catch (Exception e){
            response.sendError(500);
        }
    }
}
