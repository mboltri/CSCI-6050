package teameight.youdrive.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/LogOut")
public class LogOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        session.setAttribute("accountCredentials", null);
        session.setAttribute("customerAccount", null);
        session.setAttribute("logInErrorMessage", null);
            
        String forwardAddress = "index.jsp";
        WebPageNavigator.redirect( forwardAddress, response );
    }

}