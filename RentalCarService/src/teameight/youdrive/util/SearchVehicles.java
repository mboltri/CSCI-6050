package teameight.youdrive.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import teameight.youdrive.dbaccess.VehicleAccess;
import teameight.youdrive.entity.RentalLocation;
import teameight.youdrive.entity.Vehicle;

public class SearchVehicles {

	public static List<Vehicle> vehicleSearch(List<Vehicle> vehicleList, String make, String model, int year, String color, int mileage){
		
		List<Vehicle> responseSet = new ArrayList<Vehicle>();
		
		for (Vehicle vehicle : vehicleList) {

			boolean valid = true;

			if (make != null) {
				valid = vehicle.getMake().equalsIgnoreCase(make);
			}
			if (model != null) {
				valid = vehicle.getModel().equalsIgnoreCase(model);
			}
			if (year > 0) {
				valid = vehicle.getYear() == year;
			}
			if (color != null) {
				valid = vehicle.getColor().equalsIgnoreCase(color);
			}
			if (mileage >= 0) {
				valid = vehicle.getMileage() <= mileage;
			}
			
			if (valid) {
				responseSet.add(vehicle);
			}
		}

		return responseSet;
	
	}
	
	public static List<Vehicle> findAvaialableVehicles(int locationId, Date start, Date end) {
	    List<Vehicle> matches = new ArrayList<Vehicle>();
	    VehicleAccess dao = new VehicleAccess();
	    
	    List<Vehicle> vehicles = dao.getVehiclesByLocation(locationId);
	    for(Vehicle vehicle : vehicles) {
	        //TODO look at each vehicle's reservations and compare to start and end date to determine if it is available
	    }
	    
	    return matches;
	}
}
