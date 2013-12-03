package teameight.youdrive.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Reservation {
	private int id;
	private Timestamp startDate;
	private Timestamp endDate;
	private CustomerAccount reservationist;
	private Vehicle vehicle;
	private String status; //placed, active, or inactive
	private double cost;
	
	public static final int ADJUSTMENT_FACTOR = 3600000; //for converting from ms to hours
	
	public Reservation(Timestamp start, Timestamp end, CustomerAccount reservationist,
            Vehicle vehicle) {
        this(0, start, end, reservationist, vehicle);
    }
	
	public Reservation(int id, Timestamp startDate, Timestamp endDate, CustomerAccount reservationist,
			Vehicle vehicle) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reservationist = reservationist;
		this.vehicle = vehicle;
		calculateCost();
		setStatus();
	}
	
	public Timestamp getStartDate() {
		return startDate;
	}
	
	public Timestamp getEndDate() {
		return endDate;
	}
	
	public CustomerAccount getReservationist() {
		return reservationist;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public String getStatus() {
		return status;
	}
	
	public int getId(){
		return id;
	}
	
	public double getCost() {
	    return cost;
	}
	
	public boolean isWithinOneDay(Timestamp date) {
	    int totalHours = (int) ((startDate.getTime() - date.getTime()) / ADJUSTMENT_FACTOR);
	    return totalHours >= 24;
	}
	
	private void setStatus() {
	    Timestamp now = new Timestamp(System.currentTimeMillis());
	    if(now.before(startDate)) {
	        status = "placed";
	    } else if(now.before(endDate)) {
	        status = "active";
	    } else {
	        status = "inactive";
	    }
	}
	 
	private void calculateCost() {
	    double hourlyRate = vehicle.getVehicleType().getHourlyPrice();
	    double dailyRate = vehicle.getVehicleType().getDailyPrice();
	    
	    int totalHours = (int) ((endDate.getTime() - startDate.getTime()) / ADJUSTMENT_FACTOR);
        int totalDays = totalHours / 24;
        
	    if(totalDays == 0) {
	        cost = totalHours * hourlyRate;
	    } else {
	        cost = totalDays * dailyRate;
	    }
	}
}
