package teameight.youdrive.servlet.customer.reservation;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.dbaccess.MembershipInformationAccess;
import teameight.youdrive.dbaccess.ReservationAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.entity.Reservation;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/CancelReservation")
public class CancelReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ReservationAccess reservationDao = new ReservationAccess();
        CustomerAccountAccess customerAccountDao = new CustomerAccountAccess();
        MembershipInformationAccess membershipInformationDao = new MembershipInformationAccess();
        
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        Reservation reservation = reservationDao.getReservation(reservationId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        boolean shouldAssessFee = reservation.isWithinOneDay(now);
        
        reservationDao.removeReservation(reservation);
        
        if(shouldAssessFee) {
            CustomerAccount customerAccount = (CustomerAccount) session.getAttribute("customerAccount");
            double cancellationFee = membershipInformationDao.getReservationCancellationFee();
            customerAccount.assessFee(cancellationFee);
            
            customerAccountDao.modifyCustomerAccount(customerAccount);
        }

        String forwardAddress = "ViewCustomerReservations";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}