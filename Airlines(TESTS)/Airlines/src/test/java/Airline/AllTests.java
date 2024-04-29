package Airline;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AirlineTest.class, AirportTest.class, BookingTest.class, FlightTest.class, passengerTest.class })

public class AllTests{
	
}