package teameight.youdrive.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class CustomerAccount {
	private String username;
    private String firstName;
	private String lastName;
	private String driversLicenseNumber;
	private Date membershipStartDate;
	private Date membershipExpirationDate;
	private double balance;
	private String status; //active, inactive
    private ArrayList<Reservation> reservations;

    public CustomerAccount(String username, String firstName, String lastName,
            String driversLicenseNumber, Date membershipStartDate, Date membershipExpirationDate,
            double balance) {
        this(username, firstName, lastName, driversLicenseNumber, membershipStartDate,
                membershipExpirationDate, balance, new ArrayList<Reservation>());
    }

    public CustomerAccount(String username, String firstName, String lastName,
            String driversLicenseNumber, Date membershipStartDate, Date membershipExpirationDate,
            double balance, ArrayList<Reservation> reservations) {
        super();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.driversLicenseNumber = driversLicenseNumber;
        this.membershipStartDate = membershipStartDate;
        this.membershipExpirationDate = membershipExpirationDate;
        this.balance = balance;
        this.status = getMembershipStatus();
        this.reservations = reservations;
    }



    public String getUsername() {
        return username;
    }

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDriversLicenseNumber() {
		return driversLicenseNumber;
	}

	public Date getMembershipStartDate() {
		return membershipStartDate;
	}

	public Date getMembershipExpirationDate() {
		return membershipExpirationDate;
	}

	public double getBalance() {
    	return balance;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Reservation> getReservations() {
		return reservations;
	}
    
    public void extendMembership(double membershipPrice, int numberOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(membershipExpirationDate);
        calendar.add(Calendar.DATE, numberOfDays);  
        membershipExpirationDate = new Date(calendar.getTime().getTime());
        balance += membershipPrice;
    }

    private String getMembershipStatus() {
        String status = "";
        Date now = new Date(System.currentTimeMillis());
        
        if(now.before(membershipExpirationDate)) {
            status = "active";
        } else {
            status = "inactive";
        }
        
        return status;
    }
    
    public static Date calculateExpirationDate(Date startDate, int lengthOfMembership) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, lengthOfMembership);  
        return new Date(calendar.getTime().getTime());
    }

    public void pay(double paymentAmount) {
        balance -= paymentAmount;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

}
