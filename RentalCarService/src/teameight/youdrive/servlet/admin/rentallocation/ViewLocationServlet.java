package teameight.youdrive.servlet.admin.rentallocation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.LocationAccess;
import teameight.youdrive.entity.RentalLocation;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/ViewLocation")
public class ViewLocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocationAccess dao = new LocationAccess();

        int id = Integer.parseInt(request.getParameter("id"));
        RentalLocation rentalLocation = dao.getRentalLocation(id);
        request.setAttribute("rentalLocation", rentalLocation);

        String forwardAddress = "/admin/modifyLocation.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
    }
}
