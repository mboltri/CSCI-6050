package teameight.youdrive.servlet.customer.vehicle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.VehicleAccess;
import teameight.youdrive.entity.Vehicle;
import teameight.youdrive.util.SearchVehicles;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/SearchVehicles")
public class SearchVehiclesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        VehicleAccess vehicleDao = new VehicleAccess();

        List<Vehicle> vehicleList = vehicleDao.getVehicles();

        String  make        = request.getParameter("make");
        String  model       = request.getParameter("model");
        String  yearStr     = request.getParameter("year");
        String  color       = request.getParameter("color");
        String  mileageStr  = request.getParameter("mileage");
        
        int year    = -1;
        int mileage = -1;

        if (make.isEmpty())         make = null; 
        if (model.isEmpty())        model = null; 
        if (color.isEmpty())        color = null; 
        if (!yearStr.isEmpty())     year = Integer.parseInt(yearStr); 
        if (!mileageStr.isEmpty())  mileage = Integer.parseInt(mileageStr);
        
        List<Vehicle> searchList = SearchVehicles.vehicleSearch(vehicleList,
                make, model, year, color, mileage);

        request.setAttribute("vehicles", searchList);

        String forwardAddress = "vehicleSearchResults.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
	}
}
