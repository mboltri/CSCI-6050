package teameight.youdrive.servlet.customer.reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.ReservationAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.entity.Reservation;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/ViewCustomerReservations")
public class ViewCustomerReservationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ReservationAccess reservationDao = new ReservationAccess();
        
        CustomerAccount customerAccount = (CustomerAccount) session.getAttribute("customerAccount");
      
        ArrayList<Reservation> reservations = reservationDao.getReservationsByCustomer(customerAccount);
        
        request.setAttribute("reservations", reservations);
        
        String forwardAddress = "viewReservations.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
    }

}