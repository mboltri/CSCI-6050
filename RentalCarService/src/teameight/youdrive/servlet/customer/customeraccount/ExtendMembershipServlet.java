package teameight.youdrive.servlet.customer.customeraccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.dbaccess.MembershipInformationAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/ExtendMembership")
public class ExtendMembershipServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MembershipInformationAccess membershipInformationDao = new MembershipInformationAccess();
        CustomerAccountAccess customerAccountDao = new CustomerAccountAccess();

        int membershipLength = membershipInformationDao.getMembershipLength();
        double membershipPrice = membershipInformationDao.getMembershipPrice();
        
        CustomerAccount customerAccount = (CustomerAccount) session.getAttribute("customerAccount");
        customerAccount.extendMembership(membershipPrice, membershipLength);
        customerAccountDao.modifyCustomerAccount(customerAccount);
        
        session.setAttribute("customerAccount", customerAccount);

        String forwardAddress = "accountDetails.jsp";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}