package teameight.youdrive.servlet.customer.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.dbaccess.ReservationAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.entity.Reservation;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/PlaceReservation")
public class PlaceReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ReservationAccess reservationDao = new ReservationAccess();
        CustomerAccountAccess customerAccountDao = new CustomerAccountAccess();
      
        Reservation reservation = (Reservation) session.getAttribute("reservation");
        
        reservationDao.addReservation(reservation);
        
        CustomerAccount customerAccount = (CustomerAccount) session.getAttribute("customerAccount");
        customerAccount.addToBalance(reservation.getCost());
        customerAccountDao.modifyCustomerAccount(customerAccount);
        
        session.setAttribute("paymentMessage", "Your reservation was placed successfully. " +
        		"Please pay the reservation fee now.");

        String forwardAddress = "payBalance.jsp";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}