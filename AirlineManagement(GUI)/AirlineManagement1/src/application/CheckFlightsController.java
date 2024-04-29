package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class CheckFlightsController {

    @FXML
    private ListView<String> FlightsList_listview;

    @FXML
    private Button back_btn;
    
    @FXML
    private Button Displaybtn;
    
    private String loggedInUser; // Store the logged-in user's name

    // Set the logged-in user's name
    public void setUsername(String username) {
        this.loggedInUser = username;
    }

    @FXML
    void Displaybtn_clicked(ActionEvent event) {
        String filePath = "F:/ASU/YEAR 3/Semester 2/CSE338 - Software testing/Labs/AirlineManagement(GUI-Tests)/AirlineManagement(GUI)/AirlineManagement1/src/application/flightss.txt";
        ObservableList<String> flightItems = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] flightData = line.split(",");
                
                // Assuming CSV format: FlightID,FromAirport,ToAirport,ArrivalTime,DepartureTime,SeatsRemaining,Airline,BasePrice
                if (flightData.length >= 8) {
                    String flightID = flightData[0];
                    String fromAirport = flightData[1];
                    String toAirport = flightData[2];
                    String arrivalTime = flightData[3];
                    String departureTime = flightData[4];
                    int seatsRemaining = Integer.parseInt(flightData[5]);
                    String airlineName = flightData[6];
                    int basePrice = Integer.parseInt(flightData[7]);

                    // Create a formatted string with commas between fields
                    String formattedFlight = String.format("%s, %s, %s, %s, %s, %d, %s, %d",
                            flightID, fromAirport, toAirport, arrivalTime, departureTime,
                            seatsRemaining, airlineName, basePrice);

                    // Add the formatted flight string to the ObservableList
                    flightItems.add(formattedFlight);
                }
            }

            // Set ObservableList as the items of the ListView
            FlightsList_listview.setItems(flightItems);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file loading error
        }
    }

    @FXML
    void Backbtn_clicked(ActionEvent event) {
        // Navigate back to homepage
        Main m = new Main();
        try {
			m.toHPscene("Homepage.fxml", loggedInUser);
            System.out.println("Back to homepage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}