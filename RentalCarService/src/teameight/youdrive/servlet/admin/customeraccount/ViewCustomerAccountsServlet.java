package teameight.youdrive.servlet.admin.customeraccount;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.dbaccess.MembershipInformationAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/ViewCustomerAccounts")
public class ViewCustomerAccountsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerAccountAccess customerAccountDao = new CustomerAccountAccess();
        MembershipInformationAccess membershipInformationDao = new MembershipInformationAccess();

        ArrayList<CustomerAccount> customerAccounts = customerAccountDao.getCustomerAccounts();
        request.setAttribute("customerAccounts", customerAccounts);
        
        double membershipPrice = membershipInformationDao.getMembershipPrice();
        request.setAttribute("membershipPrice", membershipPrice);

        String forwardAddress = "/admin/viewCustomerAccounts.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
    }
}
