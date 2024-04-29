package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckBookingsController {

    @FXML
    private Button Backbtn;

    @FXML
    private ListView<String> BookingsList_listview;

    @FXML
    private Button Displaybtn;

    private String loggedInUser; // Store the logged-in user's name

    // Set the logged-in user's name
    public void setUsername(String username) {
        this.loggedInUser = username;
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

    @FXML
    void Displaybtn_clicked(ActionEvent event) {
        String filePath = "F:/ASU/YEAR 3/Semester 2/CSE338 - Software testing/Labs/AirlineManagement(GUI-Tests)/AirlineManagement(GUI)/AirlineManagement1/src/application/bookings.txt";
        ObservableList<String> bookingItems = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookingData = line.split(",");

                // Assuming CSV format: FlightID,FromAirport,ToAirport,ArrivalTime,DepartureTime,SeatsRemaining,Airline,BasePrice,User
                if (bookingData.length >= 10) {
                    String user = bookingData[9].trim(); // User is the last element in the CSV
                    System.out.println(loggedInUser);
                    if (user.equals(loggedInUser)) {
                        // Match found for the logged-in user, add the booking line to the list view
                        bookingItems.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file reading error
        }

        // Set ObservableList as the items of the ListView
        BookingsList_listview.setItems(bookingItems);
    }
}
