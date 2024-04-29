package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Flight {
	
	public Flight(String flightID, Airport from, Airport to, String arrival_time, String departure_time,
			int seats_remaining, Airline hosting_airline, int BasePrice) {
		super();
		FlightID = flightID;
		this.from = from;
		this.to = to;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.Seat_Capacity = seats_remaining;
		Hosting_airline = hosting_airline;
		this.BasePrice =BasePrice;
	}
	private String FlightID;
	private Airport from;
	private Airport to;
	private String arrival_time;
	private String departure_time;
	private int Seat_Capacity;
	private Airline Hosting_airline;
	private int BasePrice;//economy price
	private List<Booking> Bookings;

	
	
	
	public List<Booking> getBookings() {
		return Bookings;
	}
	public void setBookings(List<Booking> bookings) {
		Bookings = bookings;
	}
	public int getBasePrice() {
		return BasePrice;
	}
	public void setBasePrice(int basePrice) {
		BasePrice = basePrice;
	}
	public String getFlightID() {
		return FlightID;
	}
	public void setFlightID(String flightID) {
		FlightID = flightID;
	}
	public Airport getFrom() {
		return from;
	}
	public void setFrom(Airport from) {
		this.from = from;
	}
	public Airport getTo() {
		return to;
	}
	public void setTo(Airport to) {
		this.to = to;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public int getSeats_remaining() {
		return Seat_Capacity;
	}
	public void setSeats_remaining(int seats_remaining) {
		this.Seat_Capacity = seats_remaining;
	}
	public Airline getHosting_airline() {
		return Hosting_airline;
	}
	public void setHosting_airline(Airline hosting_airline) {
		Hosting_airline = hosting_airline;
	}
	public boolean IsFull(){
		if (Seat_Capacity == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public void AddBooking(Booking B) {
		this.Bookings.add(B);
		this.Seat_Capacity--;
	}
	
	
}