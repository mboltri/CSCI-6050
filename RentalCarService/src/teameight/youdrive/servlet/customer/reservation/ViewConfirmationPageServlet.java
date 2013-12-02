package teameight.youdrive.servlet.customer.reservation;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.VehicleAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.entity.Reservation;
import teameight.youdrive.entity.Vehicle;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/ViewConfirmationPage")
public class ViewConfirmationPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        VehicleAccess vehicleDao = new VehicleAccess();

        Timestamp start  = (Timestamp) session.getAttribute("start");
        Timestamp end    = (Timestamp) session.getAttribute("end");
        
        CustomerAccount customerAccount = (CustomerAccount) session.getAttribute("customerAccount");
        
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        Vehicle vehicle = vehicleDao.getVehicle(vehicleId);
      
        Reservation reservation = new Reservation(start, end, customerAccount, vehicle);
        
        session.setAttribute("reservation", reservation);

        String forwardAddress = "confirmReservation.jsp";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}