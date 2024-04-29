package Airline;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class passengerTest {

    private passenger passenger;
    private Booking booking;

    @BeforeEach
    public void setUp() {
        // Initialize Passenger
        passenger = new passenger("John Doe", 30, "987654321", "Anotherland");

        // Initialize Booking
        Airline airline = new Airline("Test Airline", 3.5, "123456789", "Testland");
        Airport fromAirport = new Airport("Airport A", 100);
        Airport toAirport = new Airport("Airport B", 200);
        Flight flight = new Flight("F001", fromAirport, toAirport, "10:00", "12:00", 150, airline, 250);
        booking = new Booking("B001", flight, passenger, "1A");

        // Initialize List of Bookings
        List<Booking> historyOfBookings = new ArrayList<>();
        historyOfBookings.add(booking);
        passenger.setHistory_Of_Bookings(historyOfBookings);
    }
    
    
    @ParameterizedTest
    @DisplayName("Parameterized Passenger Constructor Test")
    @ValueSource(strings = {
        "John Doe, 30, 987654321, Anotherland", // Passenger 1
        "Jane Smith, 25, 123456789, Testland",   // Passenger 2
        "Alice Johnson, 45, 555555555, Wonderland" // Passenger 3
    })
    @Order(1)
    public void testPassengerConstructorInitialization(String params) {
        String[] parts = params.split(",\\s*");
        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        String teleNumber = parts[2];
        String nationality = parts[3];

        // Create a Passenger object using the constructor
        passenger = new passenger(name, age, teleNumber, nationality);

        // Verify attributes are correctly set
        assertEquals(name, passenger.getName());
        assertEquals(age, passenger.getAge());
        assertEquals(teleNumber, passenger.getTelenumber());
        assertEquals(nationality, passenger.getNationality());
        assertFalse(passenger.isSenior()); // By default, not a senior
    }


    // Test for getName() method
    @Test @Order(2)
    public void testGetName() {
        assertEquals("John Doe", passenger.getName());
    }

    // Test for getAge() method
    @Test @Order(2)
    public void testGetAge() {
        assertEquals(30, passenger.getAge());
    }

    // Test for getTelenumber() method
    @Test @Order(2)
    public void testGetTelenumber() {
        assertEquals("987654321", passenger.getTelenumber());
    }

    // Test for getNationality() method
    @Test @Order(2) 
    public void testGetNationality() {
        assertEquals("Anotherland", passenger.getNationality());
    }

    // Test for isSenior() method
    @Test @Order(3)
    public void testIsSenior() {
        assertFalse(passenger.isSenior());
    }

    // Test for UpdateTeleNumber() method
    @Test @Order(3) 
    public void testUpdateTeleNumber() {
        passenger.UpdateTeleNumber("123456789");
        assertEquals("123456789", passenger.getTelenumber());
    }

    // Test for AddBooking() method
    @Test @Order(3) 
    public void testAddBooking() {
        Booking newBooking = new Booking("B002", booking.getHost_flight(), passenger, "1B");
        passenger.AddBooking(newBooking);

        assertEquals(2, passenger.getHistory_Of_Bookings().size());
        assertTrue(passenger.getHistory_Of_Bookings().contains(newBooking));
    }


    // Test for AuthenticateUser() method
    @Test @Order(3)
    public void testAuthenticateUser() {
        // Set valid credentials
        passenger.setPass("john@doe123");
        
        // Debug: Print out credentials for verification
        System.out.println("Testing with valid credentials: " + passenger.getName() + ", " + passenger.getPass());

        // Perform authentication check
        boolean authenticated = passenger.AuthenticateUser();

        // Debug: Print authentication result
        System.out.println("Authentication result: " + authenticated);

        // Assert authentication with valid credentials
        assertTrue(authenticated, "Valid credentials should authenticate successfully");

        // Set invalid credentials
        passenger.setPass("incorrectPass");

        // Debug: Print out credentials for verification
        System.out.println("Testing with invalid credentials: " + passenger.getName() + ", " + passenger.getPass());

        // Perform authentication check with invalid credentials
        authenticated = passenger.AuthenticateUser();

        // Debug: Print authentication result
        System.out.println("Authentication result: " + authenticated);

        // Assert authentication with invalid credentials
        assertFalse(authenticated, "Invalid credentials should not authenticate");
    }


    @Test @Order(4)
    public void testPassengerIntegration() {
        // Ensure that passenger and booking are correctly associated
        assertNotNull(passenger.getHistory_Of_Bookings());
        assertEquals(1, passenger.getHistory_Of_Bookings().size());
        assertTrue(passenger.getHistory_Of_Bookings().contains(booking));
    }
}
