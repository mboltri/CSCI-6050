package teameight.youdrive.entity;

public class VehicleType {
	private int id;
	private String name;
	private double hourlyPrice;
	private double dailyPrice;
	private String notes;
	
	public VehicleType(int id, String name, double hourlyPrice, double dailyPrice, String notes) {
		this.id = id;
		this.name = name;
		this.hourlyPrice = hourlyPrice;
		this.dailyPrice = dailyPrice;
		this.notes = notes;
	}

	public VehicleType(int id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public double getHourlyPrice() {
		return hourlyPrice;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public String getNotes() {
		return notes;
	}

	public int getId() {
		return id;
	}
	
	
}
