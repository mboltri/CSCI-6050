package teameight.youdrive.servlet.admin.vehicle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.VehicleAccess;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/RemoveVehicle")
public class RemoveVehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VehicleAccess dao = new VehicleAccess();
        
        int id = Integer.parseInt(request.getParameter("id"));
        dao.removeVehicle(id);

        String forwardAddress = "ViewVehicles";    
        WebPageNavigator.redirect(forwardAddress, response);
    }

}