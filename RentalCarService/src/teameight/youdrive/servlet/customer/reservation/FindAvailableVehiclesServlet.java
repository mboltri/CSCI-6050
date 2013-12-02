package teameight.youdrive.servlet.customer.reservation;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Date;
import teameight.youdrive.dbaccess.LocationAccess;
import teameight.youdrive.dbaccess.VehicleAccess;
import teameight.youdrive.dbaccess.VehicleTypeAccess;
import teameight.youdrive.entity.RentalLocation;
import teameight.youdrive.entity.Vehicle;
import teameight.youdrive.entity.VehicleType;
import teameight.youdrive.util.DateFormatter;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/FindAvailableVehicles")
public class FindAvailableVehiclesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        VehicleTypeAccess   vehicleTypeDao      = new VehicleTypeAccess();
        LocationAccess      rentalLocationDao   = new LocationAccess();
        VehicleAccess       vehicleDao          = new VehicleAccess();
        
        String startDateStr = request.getParameter("startDate");
        String startTimeStr = request.getParameter("startTime");
        String endDateStr = request.getParameter("endDate");
        String endTimeStr = request.getParameter("endTime");
        
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = DateFormatter.stringToDatetime(startDateStr + " " + startTimeStr);
            endDate = DateFormatter.stringToDatetime(endDateStr + " " + endTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        ArrayList<VehicleType> vehicleTypes = vehicleTypeDao.getVehicleTypes();
        request.setAttribute("vehicleTypes", vehicleTypes);
        
        ArrayList<RentalLocation> rentalLocations = rentalLocationDao.getRentalLocations();
        request.setAttribute("rentalLocations", rentalLocations);
        
        ArrayList<Vehicle> vehicles = vehicleDao.getVehicles();
        request.setAttribute("vehicles", vehicles);

        String forwardAddress = "placeReservation-2.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
	}

}