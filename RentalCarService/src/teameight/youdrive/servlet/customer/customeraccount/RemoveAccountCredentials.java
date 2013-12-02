package teameight.youdrive.servlet.customer.customeraccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.AccountCredentialsAccess;
import teameight.youdrive.entity.AccountCredentials;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/RemoveAccountCredentials")
public class RemoveAccountCredentials extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        AccountCredentialsAccess dao= new AccountCredentialsAccess();

        AccountCredentials account = (AccountCredentials)session.getAttribute("accountCredentials");
        dao.removeAccountCredentials(account);
        
        String forwardAddress = "../index.html";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}