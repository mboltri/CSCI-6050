package teameight.youdrive.servlet.admin.vehicletype;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.VehicleTypeAccess;
import teameight.youdrive.entity.VehicleType;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/ModifyVehicleType")
public class ModifyVehicleTypeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VehicleTypeAccess dao = new VehicleTypeAccess();

        int     id           = Integer.parseInt(request.getParameter("id"));
        String  name         = request.getParameter("name");
        double  hourlyPrice  = Double.parseDouble(request.getParameter("hourlyPrice"));
        double  dailyPrice   = Double.parseDouble(request.getParameter("dailyPrice"));
        String  notes        = request.getParameter("notes");
        
        VehicleType vehicleType = new VehicleType(id, name, hourlyPrice, dailyPrice, notes);
        
        dao.modifyVehicleType(vehicleType);

        String forwardAddress = "ViewVehicleTypes";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}