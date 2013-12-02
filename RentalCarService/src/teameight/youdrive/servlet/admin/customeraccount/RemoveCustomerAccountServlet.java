package teameight.youdrive.servlet.admin.customeraccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.CustomerAccountAccess;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/RemoveCustomerAccount")
public class RemoveCustomerAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerAccountAccess dao = new CustomerAccountAccess();

        String username = request.getParameter("username");
        dao.removeCustomerAccount(username);

        String forwardAddress = "ViewCustomerAccounts";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}