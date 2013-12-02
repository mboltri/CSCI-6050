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

@WebServlet("/admin/ModifyLocation")
public class ModifyLocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocationAccess dao = new LocationAccess();

        int id              = Integer.parseInt(request.getParameter("id"));
        String name         = request.getParameter("name");
        String streetNumber = request.getParameter("streetNumber");
        String streetName   = request.getParameter("streetName");
        String city         = request.getParameter("city");
        String state        = request.getParameter("state");
        int    zipCode      = Integer.parseInt(request.getParameter("zipCode"));
        int    capacity     = Integer.parseInt(request.getParameter("capacity"));
        RentalLocation rentalLocation = new RentalLocation(id, name, streetNumber, streetName,
                city, state, zipCode, capacity);
        dao.modifyRentalLocation(rentalLocation);

        String forwardAddress = "ViewLocations";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}