package teameight.youdrive.servlet.customer.reservation;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.ReservationAccess;
import teameight.youdrive.dbaccess.VehicleAccess;
import teameight.youdrive.entity.Reservation;
import teameight.youdrive.entity.Vehicle;
import teameight.youdrive.util.DateFormatter;
import teameight.youdrive.util.SearchVehicles;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/FindAvailableVehicles")
public class FindAvailableVehiclesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        HttpSession session = request.getSession();
        ReservationAccess   reservationDao   = new ReservationAccess();
        VehicleAccess       vehicleDao       = new VehicleAccess();
        
        String startDateStr = request.getParameter("startDate");
        String startTimeStr = request.getParameter("startTime");
        String endDateStr = request.getParameter("endDate");
        String endTimeStr = request.getParameter("endTime");
        
        Timestamp startDate = null;
        Timestamp endDate = null;
        try {
            startDate = DateFormatter.stringToDatetime(startDateStr + " " + startTimeStr);
            endDate = DateFormatter.stringToDatetime(endDateStr + " " + endTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String forwardAddress = "viewAccountDetails.jsp";
        if(!endDate.after(startDate)) {
            forwardAddress = "ViewReservationOptions";
        } else {
            forwardAddress = "placeReservation-2.jsp";
            int rentalLocationId = Integer.parseInt(request.getParameter("rentalLocationId"));
            
            ArrayList<Vehicle> vehicles = vehicleDao.getVehicles();
            ArrayList<Reservation> reservations = reservationDao.getReservations();
            List<Vehicle> availableVehicles = SearchVehicles.findAvaialableVehicles(
                    vehicles, reservations, rentalLocationId, startDate, endDate);
            
            session.setAttribute("start", startDate);
            session.setAttribute("end", endDate);
            request.setAttribute("vehicles", availableVehicles);
        }

        WebPageNavigator.forward(forwardAddress, request, response);
	}

}