package teameight.youdrive.servlet.admin.customeraccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.entity.CustomerAccount;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/ViewCustomerAccount")
public class ViewCustomerAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerAccountAccess dao = new CustomerAccountAccess();

        String username = request.getParameter("username");
        CustomerAccount customerAccount = dao.getCustomerAccount(username);
        request.setAttribute("customerAccount", customerAccount);

        String forwardAddress = "/admin/modifyCustomerAccount.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
    }
}
