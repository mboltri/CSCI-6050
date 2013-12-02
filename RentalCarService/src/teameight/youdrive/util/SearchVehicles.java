package teameight.youdrive.util;

import java.util.ArrayList;
import java.util.List;

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
}
