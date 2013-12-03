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
                session.setAttribute("logInErrorMessage", null);
            } else if (accountCredentials.getRole().equals("admin")) { //user is an admin
                forwardAddress = "admin/adminHome.html";
                session.setAttribute("logInErrorMessage", null);
            } else { //user has an unknown role type (this shouldn't happen)
                forwardAddress = "index.jsp";
                session.setAttribute("logInErrorMessage", "Your account has an unknown role type. " +
                		"Please contact YouDrive support for help with this error.");
            }
            
        } else { //user's credentials were not correct
            session.setAttribute("logInErrorMessage", "Invalid username/password combination. Please " +
            		"try again (remember that password is case sensitive).");
            forwardAddress = "index.jsp";
        }
        WebPageNavigator.redirect( forwardAddress, response );
    }

}