package teameight.youdrive.servlet.admin.vehicle;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.LocationAccess;
import teameight.youdrive.dbaccess.VehicleAccess;
import teameight.youdrive.dbaccess.VehicleTypeAccess;
import teameight.youdrive.entity.RentalLocation;
import teameight.youdrive.entity.Vehicle;
import teameight.youdrive.entity.VehicleType;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/ViewVehicles")
public class ViewVehiclesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        VehicleTypeAccess   vehicleTypeDao      = new VehicleTypeAccess();
        LocationAccess      rentalLocationDao   = new LocationAccess();
        VehicleAccess       vehicleDao          = new VehicleAccess();
        
        ArrayList<VehicleType> vehicleTypes = vehicleTypeDao.getVehicleTypes();
        request.setAttribute("vehicleTypes", vehicleTypes);
        
        ArrayList<RentalLocation> rentalLocations = rentalLocationDao.getRentalLocations();
        request.setAttribute("rentalLocations", rentalLocations);
        
        ArrayList<Vehicle> vehicles = vehicleDao.getVehicles();
        request.setAttribute("vehicles", vehicles);

        String forwardAddress = "/admin/viewVehicles.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
	}

}