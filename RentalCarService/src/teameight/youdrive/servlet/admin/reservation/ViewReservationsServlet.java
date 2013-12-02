package teameight.youdrive.servlet.admin.reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.ReservationAccess;
import teameight.youdrive.entity.Reservation;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/ViewReservations")
public class ViewReservationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ReservationAccess reservationDao = new ReservationAccess();
      
        ArrayList<Reservation> reservations = reservationDao.getReservations();
        
        request.setAttribute("reservations", reservations);
        
        String forwardAddress = "viewReservations.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
    }

}