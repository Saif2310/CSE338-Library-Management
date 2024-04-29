package Airline;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingTest {

	private Booking booking;

	 private Flight flight;
	    private passenger passenger;
	    private Airport fromAirport;
	    private Airport toAirport;
	    private Airline airline;




    @BeforeEach
    public void setUp() {
    	 // Initialize Airline
        airline = new Airline("Test Airline", 3.5, "123456789", "Testland");

        // Initialize Airports
        fromAirport = new Airport("Airport A", 100);
        toAirport = new Airport("Airport B", 200);

        // Initialize Flight using Airports and Airline
        flight = new Flight("F001", fromAirport, toAirport, "10:00", "12:00", 150, airline, 250);

        // Initialize Passenger
        passenger = new passenger("John Doe", 30, "987654321", "Anotherland");

        // Initialize Booking using Flight and Passenger
        booking = new Booking("B001", flight, passenger, "1A");


    }
    
    @ParameterizedTest
    @DisplayName("Repeated Constructor Initialization Test")
    @ValueSource(strings = {
        "B001, 1A",
        "B002, 1B",
        "B003, 2A",
        "B004, 3C"
    })
    @Order(1)
    public void testConstructorInitialization(String params) {
        String[] parts = params.split(",\\s*");
        String bookingId = parts[0];
        String seatNumber = parts[1];

        // Create a Booking object using the constructor
        booking = new Booking(bookingId, flight, passenger, seatNumber);

        // Verify attributes are correctly set
        assertEquals(bookingId, booking.getBooking_id());
        assertEquals(flight, booking.getHost_flight());
        assertEquals(passenger, booking.getReserving_passenger());
        assertEquals(seatNumber, booking.getSeat_number());
        assertFalse(booking.isConfirmed()); // Confirm booking should be false initially
    }

    @Test @Order(2)
    public void testGetBookingId() {
        assertEquals("B001", booking.getBooking_id());
    }

    @Test @Order(2)
    public void testGetHostFlight() {
        assertEquals(flight, booking.getHost_flight());
    }

    @Test @Order(2)
    public void testGetReservingPassenger() {
        assertEquals(passenger, booking.getReserving_passenger());
    }

    @Test @Order(2)
    public void testGetSeatNumber() {
        assertEquals("1A", booking.getSeat_number());
    }

    @Test @Order(3)
    public void testIsConfirmedInitiallyFalse() {
        assertFalse(booking.isConfirmed());
    }

    @Test @Order(3)
    public void testConfirmBooking() {
        booking.confirm();
        assertTrue(booking.isConfirmed());
    }

    @Test @Order(3)
    public void testCancelBooking() {
        booking.cancel();
        assertFalse(booking.isConfirmed());
    }

    @Test @Order(3)
    public void testCalcFare_FirstClass() {
        int fare = booking.CalcFare(flight, "1A");
        assertEquals(750, fare);
    }

    @Test @Order(3)
    public void testCalcFare_BusinessClass() {
        int fare = booking.CalcFare(flight, "1b");
        assertEquals(500, fare);
    }

    @Test @Order(3)
    public void testCalcFare_EconomyClass() {
        int fare = booking.CalcFare(flight, "1c");
        assertEquals(250, fare);
    }

    @Test @Order(3)
    public void testConfirmBookingTrue() {
        booking.ConfirmBooking(true);
        assertTrue(booking.isConfirmed());
    }

    @Test @Order(3)
    public void testConfirmBookingFalse() {
        booking.ConfirmBooking(false);
        assertFalse(booking.isConfirmed());
    }
    @Test @Order(4)
    public void testIntegrationWithFlight() {
        // Verify Booking has correct Flight information
        assertEquals(flight, booking.getHost_flight());
    }
    
 
    @Test @Order(4)
    public void testIntegrationWithPassenger() {
    	assertEquals(passenger, booking.getReserving_passenger());
    }

    @Test @Order(3)
    public void testCancelBookingTrue() {
        booking.CancelBooking(true);
        assertTrue(booking.isConfirmed());
    }

    @Test @Order(3)
    public void testCancelBookingFalse() {
        booking.CancelBooking(false);
        assertFalse(booking.isConfirmed());
    }

    @Test @Order(3)
    public void testChangeSeat() {
        booking.ChangeSeat("2A");
        assertEquals("2A", booking.getSeat_number());
    }

    @Test @Order(3)
    public void testChangeSeat_NotEquals() {
        booking.ChangeSeat("2A");
        assertNotEquals("1A", booking.getSeat_number());
    }

    @Test @Order(2)
    public void testSetBookingId() {
        booking.setBooking_id("B002");
        assertEquals("B002", booking.getBooking_id());
    }

    @Test @Order(2)
    public void testSetHostFlight() {
        Flight newFlight = new Flight("F002", new Airport("Airport C", 120), new Airport("Airport D", 180), "14:00", "16:00", 120, new Airline("Another Airline", 4.0, "987654321", "Anotherland"), 250);
        booking.setHost_flight(newFlight);
        assertEquals(newFlight, booking.getHost_flight());
    }
    
    @Test @Order(2)
    public void testSetSeatNumber() {
    	booking.setSeat_number("3A");
        assertEquals("3A", booking.getSeat_number());
    }
    @Test @Order(4)
    public void testIntegrationWithAirport() {
        // Verify Flight has correct From and To Airports
        assertEquals(fromAirport, flight.getFrom());
        assertEquals(toAirport, flight.getTo());

        // Verify Booking's Flight has correct From and To Airports
        assertEquals(fromAirport, booking.getHost_flight().getFrom());
        assertEquals(toAirport, booking.getHost_flight().getTo());

        
    }
    @Test @Order(4)
    public void testIntegrationWithAirline() {
        // Verify Flight has correct Hosting Airline
        assertEquals(airline, flight.getHosting_airline());

        // Verify Booking's Flight has correct Hosting Airline
        assertEquals(airline, booking.getHost_flight().getHosting_airline());
    }

    @Test @Order(2)
    public void testSetReservingPassenger() {
        passenger newPassenger = new passenger("Jane Doe", 25, "987654321", "Anotherland");
        booking.setReserving_passenger(newPassenger);
        assertEquals(newPassenger, booking.getReserving_passenger());
    }
   
    @Test @Order(3)
    public void testCalcFare_InvalidSeat() {
        // Test with invalid seat number
        int fare = booking.CalcFare(flight, "1D"); // Invalid seat class 'D'
        assertEquals(flight.getBasePrice(), fare); // Should default to base price
    }


    @Test @Order(2)
    public void testSetBookingId_NotEquals() {
        // Test setting booking ID to a new value
        booking.setBooking_id("B002");
        assertEquals("B002", booking.getBooking_id());
        assertNotEquals("B001", booking.getBooking_id());
    }

    @Test @Order(4)
    public void testIntegrationWithPassenger_NotEquals() {
        // Verify Passenger association
        passenger newPassenger = new passenger("Jane Doe", 25, "987654321", "Anotherland");
        booking.setReserving_passenger(newPassenger);
        assertEquals(newPassenger, booking.getReserving_passenger());
        assertNotEquals(passenger, booking.getReserving_passenger());
    }

    


}
