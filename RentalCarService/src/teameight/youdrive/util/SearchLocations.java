package teameight.youdrive.util;

import java.util.ArrayList;
import java.util.List;

import teameight.youdrive.entity.RentalLocation;

public class SearchLocations {

	public static List<RentalLocation> locationSearch(List<RentalLocation> locationList, String city, String state, int zipCode, int capacity){

		List<RentalLocation> responseSet = new ArrayList<RentalLocation>();

		for (RentalLocation location : locationList) {

			boolean valid = true;

			if (city != null) {
				valid = location.getCity().equalsIgnoreCase(city);
			}
			if (state != null) {
				valid = location.getState().equalsIgnoreCase(state);
			}
			if (zipCode > 0) {
				valid = location.getZipCode() == zipCode;
			}
			if (capacity > 0) {
				valid = location.getCapacity() >= capacity;
			}

			if (valid) {
				responseSet.add(location);
			}
		}

		return responseSet;
		
	}
}
