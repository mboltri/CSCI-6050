package teameight.youdrive.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.AccountCredentialsAccess;
import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.dbaccess.MembershipInformationAccess;
import teameight.youdrive.entity.AccountCredentials;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/LogIn")
public class LogInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountCredentialsAccess accountCredentialsDao = new AccountCredentialsAccess();
        CustomerAccountAccess customerAccountDao = new CustomerAccountAccess();
        MembershipInformationAccess membershipInformationDao = new MembershipInformationAccess();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        AccountCredentials accountCredentials = accountCredentialsDao.getAccountCredentials(username);
        
        boolean isValidLogin = accountCredentials == null? false : accountCredentials.verifyCredentials(username, password);
        String forwardAddress = null;
        if(isValidLogin) {

            double membershipPrice = membershipInformationDao.getMembershipPrice();
            session.setAttribute("membershipPrice", membershipPrice);
            
            CustomerAccount customerAccount = customerAccountDao.getCustomerAccount(username);
            
            session.setAttribute("accountCredentials", accountCredentials);
            session.setAttribute("customerAccount", customerAccount);
            
            if(accountCredentials.getRole().equals("customer")) { //user is a customer
                forwardAddress = "customer/customerHome.html";
            } else if (accountCredentials.getRole().equals("admin")) { //user is an admin
                forwardAddress = "admin/adminHome.html";
            } else { //user has an unknown role type (this shouldn't happen)
                forwardAddress = "index.html";
            }
            
        } else { //user's credentials were not correct
            //TODO bad credentials error message
            forwardAddress = "index.html";
        }
        WebPageNavigator.redirect( forwardAddress, response );
    }

}