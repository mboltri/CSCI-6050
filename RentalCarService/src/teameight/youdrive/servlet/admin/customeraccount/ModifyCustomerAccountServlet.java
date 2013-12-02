package teameight.youdrive.servlet.admin.customeraccount;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.util.DateFormatter;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/ModifyCustomerAccount")
public class ModifyCustomerAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerAccountAccess dao = new CustomerAccountAccess();

        try {
            String username                 = request.getParameter("username");
            String firstName                = request.getParameter("firstName");
            String lastName                 = request.getParameter("lastName");
            String driversLicenseNumber     = request.getParameter("driversLicenseNumber");
            Date   membershipStartDate      = DateFormatter.stringToDate(request.getParameter("membershipStartDate"));
            Date   membershipExpirationDate = DateFormatter.stringToDate(request.getParameter("membershipExpirationDate"));
            double balance                  = Double.parseDouble(request.getParameter("balance"));
            CustomerAccount customerAccount = new CustomerAccount(username, firstName, lastName,
                    driversLicenseNumber, membershipStartDate, membershipExpirationDate, balance);
            
            dao.modifyCustomerAccount(customerAccount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String forwardAddress = "ViewCustomerAccounts";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}