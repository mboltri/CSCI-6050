package teameight.youdrive.servlet.admin.vehicle;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teameight.youdrive.dbaccess.VehicleAccess;
import teameight.youdrive.entity.Vehicle;
import teameight.youdrive.util.DateFormatter;
import teameight.youdrive.util.WebPageNavigator;

@WebServlet("/admin/AddVehicle")
public class AddVehicleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        VehicleAccess dao = new VehicleAccess();
        
        try {
            int rentalLocationId = Integer.parseInt(request.getParameter("rentalLocationId"));
            int vehicleTypeId = Integer.parseInt(request.getParameter("vehicleTypeId"));
            String make = request.getParameter("make");
            String model = request.getParameter("model");
            int year = Integer.parseInt(request.getParameter("year"));
            String color = request.getParameter("color");
            String licensePlateNumber = request.getParameter("licensePlateNumber");
            int mileage = Integer.parseInt(request.getParameter("mileage"));
            Date lastServiceDate = DateFormatter.stringToDate(request.getParameter("lastServiceDate"));
            String notes = request.getParameter("vehicleCondition");
            
            Vehicle vehicle = new Vehicle(0, rentalLocationId, vehicleTypeId, make, model, year, color, 
                    licensePlateNumber, mileage, lastServiceDate, notes );
            
            dao.addVehicle(vehicle);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
        String forwardAddress = "ViewVehicles";
        WebPageNavigator.redirect(forwardAddress, response);
    }

}