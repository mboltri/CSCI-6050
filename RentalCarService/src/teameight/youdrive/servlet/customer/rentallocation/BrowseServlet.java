package teameight.youdrive.servlet.customer.rentallocation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.LocationAccess;
import teameight.youdrive.dbaccess.VehicleAccess;
import teameight.youdrive.entity.RentalLocation;
import teameight.youdrive.entity.Vehicle;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/Browse")
public class BrowseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocationAccess locationDao = new LocationAccess();
        VehicleAccess  vehicleDao  = new VehicleAccess();

        ArrayList<RentalLocation> rentalLocations = locationDao.getRentalLocations();
        request.setAttribute("rentalLocations", rentalLocations);
        
        ArrayList<Vehicle> vehicles = vehicleDao.getVehicles();
        request.setAttribute("vehicles", vehicles);

        String forwardAddress = "/customer/browse.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
    }

}