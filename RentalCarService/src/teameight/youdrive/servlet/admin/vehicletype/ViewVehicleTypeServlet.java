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

@WebServlet("/admin/ViewVehicleType")
public class ViewVehicleTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		VehicleTypeAccess dao = new VehicleTypeAccess();
		
		int id = Integer.parseInt(request.getParameter("id"));
		VehicleType vehicleType = dao.getVehicleType(id);
		request.setAttribute("vehicleType", vehicleType);
			
		String forwardAddress = "/admin/modifyVehicleType.jsp";
		WebPageNavigator.forward(forwardAddress, request, response);
	}
}
