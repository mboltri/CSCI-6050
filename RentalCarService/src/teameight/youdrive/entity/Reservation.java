package teameight.youdrive.entity;

import java.sql.Date;

public class Reservation {
	private int id;
	private Date startDate;
	private Date endDate;
	private CustomerAccount reservationist;
	private Vehicle vehicle;
	private String status; //placed, active, or inactive
	
	public Reservation(int id, Date startDate, Date endDate, CustomerAccount reservationist,
			Vehicle vehicle) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reservationist = reservationist;
		this.vehicle = vehicle;
		setStatus();
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
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
	
	public void setStatus() {
	    Date now = new Date(System.currentTimeMillis());
	    if(now.before(startDate)) {
	        status = "placed";
	    } else if(now.before(endDate)) {
	        status = "active";
	    } else {
	        status = "inactive";
	    }
	}
}
