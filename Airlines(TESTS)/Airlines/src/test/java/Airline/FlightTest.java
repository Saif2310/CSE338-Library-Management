package Airline;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class FlightTest {

	private Flight flight;
    private Airline airline;
    private Airport fromAirport;
    private Airport toAirport;
    private Booking booking;

    @BeforeEach
    public void setUp() {
        // Initialize Airline
        airline = new Airline("Test Airline", 3.5, "123456789", "Testland");

        // Initialize Airports
        fromAirport = new Airport("Airport A", 100);
        toAirport = new Airport("Airport B", 200);

        // Initialize Flight
        flight = new Flight("F001", fromAirport, toAirport, "10:00", "12:00", 150, airline, 250);

        // Initialize Booking
        passenger passenger = new passenger("John Doe", 30, "987654321", "Anotherland");
        booking = new Booking("B001", flight, passenger, "1A");

        // Initialize List of Bookings
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        flight.setBookings(bookings);
    }
    
    @ParameterizedTest
    @DisplayName("Parameterized Flight Constructor Test")
    @ValueSource(strings = {
        "F001, 10:00, 12:00, 150, 250", // Flight 1
        "F002, 12:00, 14:00, 200, 300", // Flight 2
        "F003, 15:00, 17:00, 180, 200"  // Flight 3
    })
    
    @Order(1)
    public void testFlightConstructorInitialization(String params) {
        String[] parts = params.split(",\\s*");
        String flightId = parts[0];
        String departureTime = parts[1];
        String arrivalTime = parts[2];
        int seatsRemaining = Integer.parseInt(parts[3]);
        int basePrice = Integer.parseInt(parts[4]);

        // Create a Flight object using the constructor
        Flight flight = new Flight(flightId, fromAirport, toAirport, arrivalTime, departureTime, seatsRemaining, airline, basePrice);

        // Verify attributes are correctly set
        assertEquals(flightId, flight.getFlightID());
        assertEquals(fromAirport, flight.getFrom());
        assertEquals(toAirport, flight.getTo());
        assertEquals(arrivalTime, flight.getArrival_time());
        assertEquals(departureTime, flight.getDeparture_time());
        assertEquals(seatsRemaining, flight.getSeats_remaining());
        assertEquals(airline, flight.getHosting_airline());
        assertEquals(basePrice, flight.getBasePrice());

       
    }

    // Unit Tests
    @Test @Order(2)
    public void testGetBasePrice() {
        assertEquals(250, flight.getBasePrice());
    }

    @Test @Order(3)
    public void testIsFull() {
        assertFalse(flight.IsFull());
    }

    // Integration Tests
    @Test @Order(4)
    public void testIntegrationWithAirline() {
        assertEquals(airline, flight.getHosting_airline());
    }

    @Test @Order(4)
    public void testIntegrationWithFromAirport() {
        assertEquals(fromAirport, flight.getFrom());
    }
    @Test @Order(4)
    public void testIntegrationWithBooking() {
        assertTrue(flight.getBookings().contains(booking));
        assertEquals("1A", booking.getSeat_number());
        assertEquals("John Doe", booking.getReserving_passenger().getName());
    }

    @Test @Order(4)
    public void testIntegrationWithToAirport() {
        assertEquals(toAirport, flight.getTo());
    }
    
    @Test @Order(2)
    public void testSetBasePrice() {
        flight.setBasePrice(300);
        assertEquals(300, flight.getBasePrice());
    }
    

 

}
