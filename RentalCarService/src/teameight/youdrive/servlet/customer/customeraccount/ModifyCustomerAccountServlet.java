package teameight.youdrive.servlet.customer.customeraccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/ModifyCustomerAccount")
public class ModifyCustomerAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerAccountAccess dao = new CustomerAccountAccess();

        String firstName                = request.getParameter("firstName");
        String lastName                 = request.getParameter("lastName");
        String driversLicenseNumber     = request.getParameter("driversLicenseNumber");
        
        CustomerAccount customerAccount  = (CustomerAccount) session.getAttribute("customerAccount");
        customerAccount.setFirstName(firstName);
        customerAccount.setLastName(lastName);
        customerAccount.setDriversLicenseNumber(driversLicenseNumber);
        
        dao.modifyCustomerAccount(customerAccount);
        
        session.setAttribute("customerAccount", customerAccount);

        String forwardAddress = "accountDetails.jsp";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}