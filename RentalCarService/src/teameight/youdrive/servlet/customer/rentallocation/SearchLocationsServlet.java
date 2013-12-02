package teameight.youdrive.servlet.customer.rentallocation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.LocationAccess;
import teameight.youdrive.entity.RentalLocation;
import teameight.youdrive.util.SearchLocations;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/customer/SearchLocations")
public class SearchLocationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        LocationAccess rentalLocationDao = new LocationAccess();
        List<RentalLocation> locationList = rentalLocationDao
                .getRentalLocations();

        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipCodeStr = request.getParameter("zipCode");
        String capacityStr = request.getParameter("capacity");
        
        int zipCode = -1;
        int capacity = -1;

        if (city.isEmpty()) city = null;
        if (state.isEmpty()) state = null;
        if (!zipCodeStr.isEmpty()) zipCode = Integer.parseInt(zipCodeStr);
        if (!capacityStr.isEmpty()) capacity = Integer.parseInt(zipCodeStr);
       
        List<RentalLocation> searchList = SearchLocations.locationSearch(
                locationList, city, state, zipCode, capacity);

        request.setAttribute("rentalLocations", searchList);

        String forwardAddress = "/customer/locationSearchResults.jsp";
        WebPageNavigator.forward(forwardAddress, request, response);
	}
}
