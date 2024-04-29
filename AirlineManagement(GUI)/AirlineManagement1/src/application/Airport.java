package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String airportName;
    private int emptyParkingSpots;

    // Constructor
    public Airport(String airportName, int emptyParkingSpots) {
        this.airportName = airportName;
        this.emptyParkingSpots = emptyParkingSpots;
    }

    // Getter for Airport_Name
    public String getAirportName() {
        return airportName;
    }

    // Setter for Airport_Name
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    // Getter for EmptyParkingSpots
    public int getEmptyParkingSpots() {
        return emptyParkingSpots;
    }

    // Setter for EmptyParkingSpots
	    public void setEmptyParkingSpots(int emptyParkingSpots) {
	    	if(emptyParkingSpots < 0 )return;
	        this.emptyParkingSpots = emptyParkingSpots;
	    }
    
	    public boolean isOccupied() {
	        return emptyParkingSpots == 0; // Return true if no empty parking spots
	    }

	
}
