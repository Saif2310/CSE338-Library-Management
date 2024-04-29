package Airline;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class AirlineTest {

	 private Airline airline;
	 private Flight flight;
	 private Airport airport;

	    @BeforeEach
	    public void setUp() {
	    	// Initialize Airline
	        airline = new Airline("Test Airline", 3.5, "123456789", "Testland");

	        // Initialize Airport
	        airport = new Airport("Airport A", 100);

	        // Initialize Flight using Airport and Airline
	        flight = new Flight("F001", airport, airport, "10:00", "12:00", 150, airline, 250);

	    }

	    @Test @Order(2)
	    public void testGetName() {
	        assertEquals("Test Airline", airline.getName());
	    }

	    @Test @Order(3)
	    public void testAssignRanking_BadRating() {
	        airline.setRating(1.0);
	        assertEquals("Bad", airline.AssignRanking());
	    }

	    @Test @Order(3)
	    public void testAssignRanking_GoodRating() {
	        airline.setRating(3.0);
	        assertEquals("Good", airline.AssignRanking());
	    }

	    @Test @Order(3)
	    public void testAssignRanking_ExcellentRating() {
	        airline.setRating(4.5);
	        assertEquals("Excellent", airline.AssignRanking());
	    }

	    @Test @Order(3)
	    public void testAssignRanking_InvalidRating() {
	        airline.setRating(6.0);
	        assertEquals("Invalid Rating", airline.AssignRanking());
	    }

	    @Test @Order(3)
	    public void testChangeHotline() {
	        airline.ChangeHotline("987654321");
	        assertEquals("987654321", airline.getHotline());
	    }

	    @Test @Order(2)
	    public void testSetAndGetNationality() {
	        airline.setNationality("Newland");
	        assertEquals("Newland", airline.getNationality());
	    }

	    @Test @Order(2)
	    public void testSetName() {
	        airline.setName("New Airline");
	        assertEquals("New Airline", airline.getName());
	    }

	    @Test @Order(2)
	    public void testSetRating() {
	        airline.setRating(4.0);
	        assertEquals(4.0, airline.getRating(), 0.001); // Check with delta for double comparison
	    }

	    @Test @Order(2)
	    public void testSetHotline() {
	        airline.setHotline("987654321");
	        assertEquals("987654321", airline.getHotline());
	    }

	    @Test @Order(2)
	    public void testSetNationality() {
	        airline.setNationality("Anotherland");
	        assertEquals("Anotherland", airline.getNationality());
	    }

	    @Test @Order(2)
	    public void testSetName_NotEquals() {
	        airline.setName("New Airline");
	        assertNotEquals("Test Airline", airline.getName());
	    }

	    @Test @Order(2)
	    public void testSetRating_NotEquals() {
	        airline.setRating(4.0);
	        assertNotEquals(3.5, airline.getRating(), 0.001); // Check with delta for double comparison
	    }
	    @Test @Order(2)
	    public void testsetNationality_NotEquals() {
	    	airline.setNationality("Anotherland");
	        assertNotEquals("Testland", airline.getNationality());
	    }
	    
	    @Test @Order(4)
	    public void testIntegrationWithFlight() {
	    	assertEquals(airline, flight.getHosting_airline());
	    }

	    @Test @Order(2)
	    public void testSetHotline_NotEquals() {
	        airline.setHotline("987654321");
	        assertNotEquals("123456789", airline.getHotline());
	    }
	    
	    @ParameterizedTest
	    @Order(1)
	    @DisplayName("Repeated Constructor Initialization Test")
	    @ValueSource(strings = {
	        "New Airline 1, 2.0, 999999991, Newland 1",
	        "New Airline 2, 3.5, 999999992, Newland 2",
	        "New Airline 3, 4.0, 999999993, Newland 3",
	        "New Airline 4, 1.5, 999999994, Newland 4"
	    })
	    public void testConstructorInitialization(String params) {
	        String[] parts = params.split(",\\s*");
	        String name = parts[0];
	        double rating = Double.parseDouble(parts[1]);
	        String hotline = parts[2];
	        String nationality = parts[3];

	        Airline newAirline = new Airline(name, rating, hotline, nationality);
	        assertEquals(name, newAirline.getName());
	        assertEquals(rating, newAirline.getRating(), 0.001);
	        assertEquals(hotline, newAirline.getHotline());
	        assertEquals(nationality, newAirline.getNationality());
	    }

	    @Test @Order(3)
	    public void testAssignRanking_MarginRating() {
	        airline.setRating(1.5);
	        assertEquals("Good", airline.AssignRanking());
	    }


	    @Test @Order(3)
	    public void testChangeHotline_NullNumber() {
	        airline.ChangeHotline(null);
	        assertNull(airline.getHotline());
	    }

	    @Test @Order(4)
	    public void testIntegrationWithFlight_NotEquals() {
	        Airline anotherAirline = new Airline("Another Airline", 4.0, "888888888", "Otherland");
	        Flight anotherFlight = new Flight("F002", airport, airport, "12:00", "14:00", 200, anotherAirline, 300);
	        assertNotEquals(airline, anotherFlight.getHosting_airline());
	    }
	    
	    
}
