package teameight.youdrive.servlet.admin.rentallocation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.LocationAccess;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/RemoveLocation")
public class RemoveLocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocationAccess dao = new LocationAccess();

        int id = Integer.parseInt(request.getParameter("id"));
        dao.removeRentalLocation(id);

        String forwardAddress = "ViewLocations";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}