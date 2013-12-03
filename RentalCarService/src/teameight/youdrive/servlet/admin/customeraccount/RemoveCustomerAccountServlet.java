package teameight.youdrive.servlet.admin.customeraccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.AccountCredentialsAccess;
import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.dbaccess.ReservationAccess;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/RemoveCustomerAccount")
public class RemoveCustomerAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerAccountAccess customerAccountDao = new CustomerAccountAccess();
        AccountCredentialsAccess accountCredentialsDao= new AccountCredentialsAccess();
        ReservationAccess reservationDao = new ReservationAccess();

        String username = request.getParameter("username");
        customerAccountDao.removeCustomerAccount(username);
        accountCredentialsDao.removeAccountCredentials(username);
        reservationDao.removeReservationsByCustomer(username);

        String forwardAddress = "ViewCustomerAccounts";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}