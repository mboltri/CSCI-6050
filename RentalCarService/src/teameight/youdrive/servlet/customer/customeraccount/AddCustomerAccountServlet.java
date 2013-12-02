package teameight.youdrive.servlet.customer.customeraccount;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.dbaccess.MembershipInformationAccess;
import teameight.youdrive.entity.AccountCredentials;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/AddCustomerAccount")
public class AddCustomerAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        CustomerAccountAccess       customerAccountDao       = new CustomerAccountAccess();
        MembershipInformationAccess membershipInformationDao = new MembershipInformationAccess();

        AccountCredentials  account              = (AccountCredentials)session.getAttribute("accountCredentials");
        String              firstName            = request.getParameter("firstName");
        String              lastName             = request.getParameter("lastName");
        String              driversLicenseNumber = request.getParameter("driversLicenseNumber");
        Date                membershipStartDate  = new Date(System.currentTimeMillis());
        
        int  lengthOfMembership = membershipInformationDao.getMembershipLength();
        Date membershipExpirationDate = CustomerAccount.calculateExpirationDate(membershipStartDate, lengthOfMembership);
        
        double membershipPrice = (Double)session.getAttribute("membershipPrice");
        
        CustomerAccount customerAccount = new CustomerAccount(account.getUsername(), firstName, 
                lastName, driversLicenseNumber, membershipStartDate, membershipExpirationDate, 
                membershipPrice);
        
        customerAccountDao.addCustomerAccount(customerAccount);
        
        session.setAttribute("customerAccount", customerAccount);
        
        String forwardAddress = "payBalance.jsp";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}