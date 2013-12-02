package teameight.youdrive.entity;

import java.sql.Date;

public class Vehicle {
	
	private int id;
	private RentalLocation rentalLocation;
	private VehicleType vehicleType;
	private String make;
	private String model;
	private int year;
	private String color;
	private String licensePlateNumber;
	private int mileage;
	private Date lastServiceDate;
	private String vehicleCondition;
	
	public Vehicle(int id, RentalLocation rentalLocation, VehicleType vehicleType, String make, String model,
			int year, String color, String licensePlateNumber, int mileage, Date lastServiceDate,
			String vehicleCondition) {
		this.id = id;
		this.rentalLocation = rentalLocation;
		this.vehicleType = vehicleType;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.licensePlateNumber = licensePlateNumber;
		this.mileage = mileage;
		this.lastServiceDate = lastServiceDate;
		this.vehicleCondition = vehicleCondition;
	}
	public Vehicle(int id, int rentalLocationId, int vehicleTypeId, String make, String model,
            int year, String color, String licensePlateNumber, int mileage, Date lastServiceDate,
            String vehicleCondition) {
	    this(id, new RentalLocation(rentalLocationId), new VehicleType(vehicleTypeId), make, model, 
	            year, color, licensePlateNumber, mileage, lastServiceDate, vehicleCondition);
	    
    }

	public RentalLocation getRentalLocation() {
		return rentalLocation;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public String getColor() {
		return color;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public int getMileage() {
		return mileage;
	}

	public Date getLastServiceDate() {
		return lastServiceDate;
	}

	public String getVehicleCondition() {
		return vehicleCondition;
	}

	public int getId() {
		return id;
	}

}
