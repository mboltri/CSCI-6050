package teameight.youdrive.entity;

public class RentalLocation {
	
	private int id;
	private String name;
	private String streetNumber;
	private String streetName;
	private String city;
	private String state;
	private int zipCode;
	private int capacity;
	
	
	public RentalLocation(int id, String name, String streetNumber, String streetName, String city,
            String state, int zipCode, int capacity) {
        this.id = id;
        this.name = name;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.capacity = capacity;
    }

	public RentalLocation(int id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getId() {
		return id;
	}
    
}
