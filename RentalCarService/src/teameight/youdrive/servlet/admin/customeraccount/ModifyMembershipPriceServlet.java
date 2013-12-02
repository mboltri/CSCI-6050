package teameight.youdrive.servlet.admin.customeraccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.MembershipInformationAccess;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/ModifyMembershipPrice")
public class ModifyMembershipPriceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MembershipInformationAccess dao = new MembershipInformationAccess();

        double membershipPrice = Double.parseDouble(request.getParameter("membershipPrice"));
        dao.modifyMembershipPrice(membershipPrice);

        String forwardAddress = "ViewCustomerAccounts";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}