package teameight.youdrive.servlet.admin.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.dbaccess.MembershipInformationAccess;
import teameight.youdrive.dbaccess.ReservationAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/CancelReservation")
public class CancelReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReservationAccess reservationDao = new ReservationAccess();
        CustomerAccountAccess customerAccountDao = new CustomerAccountAccess();
        MembershipInformationAccess membershipInformationDao = new MembershipInformationAccess();
        
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        reservationDao.removeReservation(reservationId);
        
        boolean shouldAssessFee = Boolean.parseBoolean(request.getParameter("fee"));
        if(shouldAssessFee) {
            String username = request.getParameter("customerUsername");
            CustomerAccount customerAccount = customerAccountDao.getCustomerAccount(username);
            double cancellationFee = membershipInformationDao.getReservationCancellationFee();
            customerAccount.assessFee(cancellationFee);
            
            customerAccountDao.modifyCustomerAccount(customerAccount);
        }

        String forwardAddress = "ViewReservations";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}