package teameight.youdrive.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import teameight.youdrive.entity.Reservation;
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
	
	public static List<Vehicle> findAvaialableVehicles(List<Vehicle> vehicles, 
	        List<Reservation> reservations, int rentalLocationId, Timestamp startDate, Timestamp endDate) {
	    
	    List<Vehicle> matches = new ArrayList<Vehicle>();
	    
	    Map<Integer, List<Reservation>> reservationsMap = buildVehicleReservationMap(reservations); 
	    
	    for(Vehicle vehicle : vehicles) {
	        
	        List<Reservation> vehicleReservations = reservationsMap.get(vehicle.getId());
	        if(isVehicleAtLocation(vehicle, rentalLocationId) && !isConflict(startDate, endDate, vehicleReservations)) {
	            matches.add(vehicle);
	        }
	    }
	    
	    return matches;
	}
	
	private static boolean isVehicleAtLocation(Vehicle vehicle, int rentalLocationId) {
	    return vehicle.getRentalLocation().getId() == rentalLocationId;
	}
	
	private static boolean isConflict(Timestamp startDate, Timestamp endDate, List<Reservation> reservations) {
	    if(reservations == null || reservations.isEmpty()) {
	        return false;
	    }
	    
	    for(Reservation reservation : reservations) {
	        Timestamp reservationStart  = reservation.getStartDate();
	        Timestamp reservationEnd    = reservation.getEndDate();
	        if(startDate.before(reservationStart) && endDate.after(reservationEnd)) {
                return true;
            }
	        if(startDate.after(reservationStart) && startDate.before(reservationEnd)) {
	            return true;
	        }
	        if(endDate.before(reservationEnd) && endDate.after(reservationStart)) {
                return true;
            }
	    }
	    return false;
	}
	
	private static Map<Integer, List<Reservation>> buildVehicleReservationMap(List<Reservation> reservations) {
	    
	    Map<Integer, List<Reservation>> map = new HashMap<Integer, List<Reservation>>();
	    
	    for(Reservation reservation : reservations) {
	        Vehicle vehicle = reservation.getVehicle();

	        List<Reservation> vehicleReservations = map.get(vehicle);
	        if(vehicleReservations == null) { 
	            vehicleReservations = new ArrayList<Reservation>(); 
	        }
	        vehicleReservations.add(reservation);
	        map.put(vehicle.getId(), vehicleReservations);
	    }
	    
	    return map;
	}
}
