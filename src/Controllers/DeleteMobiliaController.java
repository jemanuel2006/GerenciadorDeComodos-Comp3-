package Controllers;

import TransactionScripts.MobiliaTransactions;
import Utils.QueryStringHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by juane on 07/11/2016.
 */
@WebServlet("/Delete/Mobilia")
public class DeleteMobiliaController extends HttpServlet {
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map atts = QueryStringHelper.getQueryMap(request.getQueryString());
        int id = Integer.parseInt(atts.get("id").toString());
        try {
            MobiliaTransactions.DeleteMobilia(id);
            response.getWriter().write("Ok!");
        } catch (Exception e) {
            response.sendError(500);
        }
    }
}
