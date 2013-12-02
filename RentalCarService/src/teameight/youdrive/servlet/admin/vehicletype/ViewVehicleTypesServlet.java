package teameight.youdrive.servlet.admin.vehicletype;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teameight.youdrive.dbaccess.VehicleTypeAccess;
import teameight.youdrive.entity.VehicleType;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/ViewVehicleTypes")
public class ViewVehicleTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        HttpSession session = request.getSession();
        VehicleTypeAccess dao = new VehicleTypeAccess();
        
        ArrayList<VehicleType> vehicleTypes = dao.getVehicleTypes();
        session.setAttribute("vehicleTypes", vehicleTypes);

        String forwardAddress = "/admin/viewVehicleTypes.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
	}

}