package teameight.youdrive.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebPageNavigator {
    public static void redirect(String redirectAddress, HttpServletResponse response) 
            throws IOException {
        String url = response.encodeRedirectURL(redirectAddress);
        response.sendRedirect(url);
    }

    public static void forward(String forwardAddress, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardAddress);
        dispatcher.forward(request, response);
    }
} 
