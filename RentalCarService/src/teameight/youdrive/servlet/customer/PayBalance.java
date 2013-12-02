package teameight.youdrive.servlet.customer;

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

@WebServlet("/customer/PayBalance")
public class PayBalance extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        CustomerAccountAccess dao = new CustomerAccountAccess();
        
        CustomerAccount customerAccount = (CustomerAccount)session.getAttribute("customerAccount");
        
        double paymentAmount = Double.parseDouble(request.getParameter("paymentAmount"));
        customerAccount.pay(paymentAmount);
        
        dao.modifyCustomerAccount(customerAccount);
        
        String paymentMessage = null;
        if(customerAccount.getBalance() <= 0) {
            paymentMessage = "All fees on your account have been paid.";
        } else {
            paymentMessage = "You have an unpaid balance on your account.";
        }
        session.setAttribute("paymentMessage", paymentMessage);
        
        String forwardAddress = "accountDetails.jsp";
        WebPageNavigator.redirect(forwardAddress, response);
    }
}