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
class AirportTest {

	private Airport airport;

    @BeforeEach
    public void setUp() {
        airport = new Airport("Airport A", 100);
    }
    
    @ParameterizedTest
    @DisplayName("Repeated Constructor Initialization Test")
    @ValueSource( strings = {
        "New Airport 1, 200",
        "New Airport 2, 150",
        "New Airport 3, 180",
        "New Airport 4, 120"
    })
    @Order(1)
    public void testConstructorInitialization(String params) {
    	String[] parts = params.split(",\\s*");
        String airportName = parts[0];
        int emptyParkingSpots = Integer.parseInt(parts[1]);
    	airport.setAirportName(airportName);
        airport.setEmptyParkingSpots(emptyParkingSpots);

        assertEquals(airportName, airport.getAirportName());
        assertEquals(emptyParkingSpots, airport.getEmptyParkingSpots());
    }

    @Test @Order(2)
    public void testGetAirportName() {
        assertEquals("Airport A", airport.getAirportName());
    }

    @Test @Order(2)
    public void testSetAirportName() {
        airport.setAirportName("New Airport");
        assertEquals("New Airport", airport.getAirportName());
    }

    @Test @Order(2)
    public void testGetEmptyParkingSpots() {
        assertEquals(100, airport.getEmptyParkingSpots());
    }

    @Test @Order(2)
    public void testSetEmptyParkingSpots() {
        airport.setEmptyParkingSpots(50);
        assertEquals(50, airport.getEmptyParkingSpots());
    }

    @Test @Order(3)
    public void testIsOccupied_EmptyParkingSpots() {
        assertFalse(airport.isOccupied());
    }

    @Test @Order(3)
    public void testIsOccupied_NonEmptyParkingSpots() {
        airport.setEmptyParkingSpots(0);
        assertTrue(airport.isOccupied());
    }

    @Test @Order(3)
    public void testIsOccupied_UpdatedEmptyParkingSpots() {
        airport.setEmptyParkingSpots(30);
        assertFalse(airport.isOccupied());
    }

    @Test @Order(3)
    public void testIsOccupied_UpdatedToZeroEmptyParkingSpots() {
        airport.setEmptyParkingSpots(0);
        assertTrue(airport.isOccupied());
    }

    @Test @Order(2)
    public void testSetAirportName_NotEquals() {
        airport.setAirportName("New Airport");
        assertNotEquals("Airport A", airport.getAirportName());
    }

    @Test @Order(2)
    public void testSetEmptyParkingSpots_NotEquals() {
        airport.setEmptyParkingSpots(50);
        assertNotEquals(100, airport.getEmptyParkingSpots());
    }

    @Test @Order(2)
    public void testSetEmptyParkingSpots_BoundaryValues() {
        // Test with boundary values
        airport.setEmptyParkingSpots(0);
        assertTrue(airport.isOccupied());

        airport.setEmptyParkingSpots(Integer.MAX_VALUE);
        assertFalse(airport.isOccupied());
    }

    @Test @Order(2)
    public void testSetEmptyParkingSpots_NegativeValue() {
        // Test handling of negative values (should not change value)
        int initialSpots = airport.getEmptyParkingSpots();
        airport.setEmptyParkingSpots(-50);
        assertEquals(initialSpots, airport.getEmptyParkingSpots());
    }
    
    
    @Test @Order(4)
    public void testIntegrationWithFlight() {
    	Airport fromAirport = new Airport("Airport B", 200);
        Airport toAirport = new Airport("Airport C", 150);
        Airline airline = new Airline("Test Airline", 3.5, "123456789", "Testland");
        Flight flight = new Flight("F001", fromAirport, toAirport, "10:00", "12:00", 150, airline, 250);

        assertEquals(fromAirport, flight.getFrom());
        assertEquals(toAirport, flight.getTo());
        assertEquals(200, fromAirport.getEmptyParkingSpots());
        assertEquals(150, toAirport.getEmptyParkingSpots());
    }

    
    @Test @Order(2)
    public void testSetEmptyParkingSpots_MinValue() {
        airport.setEmptyParkingSpots(0);
        assertTrue(airport.isOccupied());
    }

    @Test @Order(2)
    public void testSetEmptyParkingSpots_MaxValue() {
        airport.setEmptyParkingSpots(Integer.MAX_VALUE);
        assertFalse(airport.isOccupied());
    }


    @Test @Order(2)
    public void testSetAirportName_NotEquals1() {
        airport.setAirportName("New Airport");
        assertNotEquals("Airport A", airport.getAirportName());
    }

    @Test @Order(4)
    public void testIntegrationWithFlight_NotEquals() {
        Airport fromAirport = new Airport("Airport B", 200);
        Airport toAirport = new Airport("Airport C", 150);
        Airline airline = new Airline("Test Airline", 3.5, "123456789", "Testland");
        Flight flight = new Flight("F001", fromAirport, toAirport, "10:00", "12:00", 150, airline, 250);

        assertNotEquals(fromAirport, flight.getTo());
        assertNotEquals(toAirport, flight.getFrom());
    }



}
