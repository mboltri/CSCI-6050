package teameight.youdrive.servlet.customer.customeraccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.AccountCredentialsAccess;
import teameight.youdrive.dbaccess.MembershipInformationAccess;
import teameight.youdrive.entity.AccountCredentials;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/AddAccountCredentials")
public class AddAccountCredentialsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountCredentialsAccess accountCredentialsDao= new AccountCredentialsAccess();
        MembershipInformationAccess membershipInformationDao = new MembershipInformationAccess();

        String username         = request.getParameter("username").toLowerCase();
        String password         = request.getParameter("password");
        String passwordConfirm  = request.getParameter("passwordConfirm");
        String role             = request.getParameter("role");
        
        String forwardAddress = null;
        if(!password.equals(passwordConfirm)) { //user's passwords did not match
            
            session.setAttribute("accountCreationErrorMessage", "The passwords you entered did not match.");
            forwardAddress = "createAccount-1.jsp";
            
        } else { //passwords match
            
            AccountCredentials accountCredentials = new AccountCredentials(username, password, role);
            boolean addCredentialsSuccessful = accountCredentialsDao.addAccountCredentials(accountCredentials);
            
            if(addCredentialsSuccessful) {
                session.setAttribute("accountCreationErrorMessage", null);
                session.setAttribute("accountCredentials", accountCredentials);
                
                double membershipPrice = membershipInformationDao.getMembershipPrice();
                session.setAttribute("membershipPrice", membershipPrice);
                
                forwardAddress = "createAccount-2.jsp";
            } else { //user's credentials could not be added to the database

                session.setAttribute("accountCreationErrorMessage", "There was an error creating " +
                		"your account. This is probably because the username you chose has already " +
                		"been taken.");
                forwardAddress = "createAccount-1.jsp";
            }
            
        }

        WebPageNavigator.redirect(forwardAddress, response);
    }

}