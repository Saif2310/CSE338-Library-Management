 package application;

public class Booking {
	private String booking_id;
    private Flight host_flight;
    private passenger reserving_passenger;
    private String seat_number;//A first class b business c economy
    private boolean confirmed;

    // Constructor
    public Booking(String booking_id, Flight host_flight, passenger reserving_passenger, String seat_number) {
        this.booking_id = booking_id;
        this.host_flight = host_flight;
        this.reserving_passenger = reserving_passenger;
        this.seat_number = seat_number;
        this.confirmed = false; // By default, booking is not confirmed
    }

    // Getters and Setters
    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public Flight getHost_flight() {
        return host_flight;
    }

    public void setHost_flight(Flight host_flight) {
        this.host_flight = host_flight;
    }

    public passenger getReserving_passenger() {
        return reserving_passenger;
    }

    public void setReserving_passenger(passenger reserving_passenger) {
        this.reserving_passenger = reserving_passenger;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    // Confirm booking
    public void confirm() {
        this.confirmed = true;
        System.out.println("Booking confirmed for passenger: " + reserving_passenger.getName());
    }

    // Cancel booking
    public void cancel() {
        this.confirmed = false;
        System.out.println("Booking canceled for passenger: " + reserving_passenger.getName());
    }

    // Check if booking is confirmed
    public boolean isConfirmed() {
    	System.out.println("Confirmation Done");
        return confirmed;
    }
    
    
    
    public int CalcFare(Flight HostFlight , String Seatnumber) {
    	
    	int fare = HostFlight.getBasePrice();
    	if(Seatnumber.charAt(1) == 'A')fare *= 3;
    	else if(Seatnumber.charAt(1) == 'b')fare *= 2;
    	else if (Seatnumber.charAt(1) == 'c') fare = fare ;
		return fare;
    }
    
public void ConfirmBooking(boolean Confirm) {
		
		this.confirmed = Confirm;
	}
	
    public void CancelBooking(boolean Cancelbooking) {
		
		this.confirmed = Cancelbooking;
	}
    
    void ChangeSeat(String NewSeatNumber) {
    	
    	this.seat_number = NewSeatNumber;
    
    }
    
    
}