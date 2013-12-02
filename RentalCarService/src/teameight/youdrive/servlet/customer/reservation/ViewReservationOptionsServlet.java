package teameight.youdrive.servlet.customer.reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.LocationAccess;
import teameight.youdrive.entity.RentalLocation;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/ViewReservationOptions")
public class ViewReservationOptionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocationAccess dao = new LocationAccess();

        ArrayList<RentalLocation> rentalLocations = dao.getRentalLocations();
        request.setAttribute("rentalLocations", rentalLocations);

        String forwardAddress = "placeReservation-1.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
    }

}