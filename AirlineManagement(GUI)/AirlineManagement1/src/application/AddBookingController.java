package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class AddBookingController {

    @FXML private Button Backbtn;
    @FXML private Button Checkpricebtn;
    @FXML private ComboBox<String> Flight_combobox;
    @FXML private Label Price_lbl;
    @FXML private Button Reserve_btn;
    @FXML private ComboBox<String> Seating_combobox;
    @FXML private DatePicker date_datepicker;
    @FXML private ComboBox<String> from_COMBOBOX;
    @FXML private ComboBox<String> to_COMBOBOX;
    
    @FXML
    private Button update_btn;

    private ObservableList<String> availableFlights = FXCollections.observableArrayList();
    private ObservableList<String> availableSeatingClasses = FXCollections.observableArrayList();
    private String loggedInUser;

    @FXML
    void initialize() {
        loadLocations(); // Populate departure and arrival locations

        from_COMBOBOX.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateArrivalLocations(newValue); // Update arrival locations based on departure selection
            }
        });

        // For demonstration purposes, populate seating classes
        availableSeatingClasses.addAll("Economy", "Business", "First Class");
        Seating_combobox.setItems(availableSeatingClasses);
    }

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
    void Checkpricebtn_clicked(ActionEvent event) {
        // Calculate and display the price based on selected flight and seating class
        String selectedFlightFull = Flight_combobox.getValue();
        String selectedSeatingClass = Seating_combobox.getValue();
        String selectedFlight = selectedFlightFull.split(" ")[0].trim();
        System.out.println(selectedFlight);


        if (selectedFlight != null && selectedSeatingClass != null) {
            // Retrieve base price of the selected flight
            String filePath = "F:\\ASU\\YEAR 3\\Semester 2\\CSE338 - Software testing\\Labs\\AirlineManagement(GUI-Tests)\\AirlineManagement(GUI)\\AirlineManagement1\\src\\application\\flightss.txt";
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
                    String[] flightData = line.split(",");
                    if (flightData.length >= 8 && flightData[0].equals(selectedFlight)) {
                        int basePrice = Integer.parseInt(flightData[7]);
                        // Calculate price based on base price and seating class
                        int totalPrice = calculateTotalPrice(basePrice, selectedSeatingClass);
                        Price_lbl.setText("Price: $" + totalPrice);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int calculateTotalPrice(int basePrice, String seatingClass) {
        // Simplified price calculation based on base fare and seating class
        switch (seatingClass) {
            case "Economy":
                return basePrice;
            case "Business":
                return basePrice * 2;
            case "First Class":
                return basePrice * 3;
            default:
                return basePrice;
        }
    }

    @FXML
    void reserve_btn_clicked(ActionEvent event) {
        String selectedFlightFull = Flight_combobox.getValue();
        String selectedSeatingClass = Seating_combobox.getValue();
        String selectedFlight = selectedFlightFull.split(" ")[0].trim();
        System.out.println(selectedFlight);

        if (selectedFlight != null && selectedSeatingClass != null) {
            // Retrieve complete flight information
            String filePath = "F:/ASU/YEAR 3/Semester 2/CSE338 - Software testing/Labs/AirlineManagement(GUI-Tests)/AirlineManagement(GUI)/AirlineManagement1/src/application/flightss.txt";
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                String bookingData = null;
                List<String> updatedLines = new ArrayList<>();
                boolean flightUpdated = false;

                for (String line : lines) {
                    String[] flightData = line.split(",");
                    if (flightData.length >= 8 && flightData[0].equals(selectedFlight)) {
                        // Update the number of seats remaining
                        int remainingSeats = Integer.parseInt(flightData[5].trim());
                        if (remainingSeats > 0) {
                            remainingSeats--; // Decrement the remaining seats
                            flightData[5] = String.valueOf(remainingSeats); // Update the seats in the array
                            line = String.join(",", flightData);
                            flightUpdated = true;
                        } else {
                            System.out.println("No seats available for this flight.");
                            return; // Exit if no seats are available
                        }
                    }
                    updatedLines.add(line); // Add the updated line to the list
                }
                
                if (!flightUpdated) {
                    System.out.println("Flight details not found!");
                    return; // Exit if flight details were not found
                }

                // Rewrite the updated flight data back to the file
                Files.write(Paths.get(filePath), updatedLines);

                for (String line : lines) {
                    String[] flightData = line.split(",");
                    if (flightData.length >= 8 && flightData[0].equals(selectedFlight)) {
                        String flightInfo = String.join(",", flightData);
                        System.out.println(loggedInUser);
                        bookingData = String.format("%s,%s,%s", flightInfo, selectedSeatingClass, loggedInUser);
                        break;
                    }
                }

                if (bookingData != null) {
                    // Write booking data to bookings.txt
                    String bookingsFilePath = "F:\\ASU\\YEAR 3\\Semester 2\\CSE338 - Software testing\\Labs\\AirlineManagement(GUI-Tests)\\AirlineManagement(GUI)\\AirlineManagement1\\src\\application\\bookings.txt";
                    try (FileWriter writer = new FileWriter(bookingsFilePath, true)) {
                        writer.write(bookingData + System.lineSeparator());
                        writer.flush();
                        System.out.println("Booking added successfully!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Flight details not found!");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLocations() {
        String flightsFilePath = "F:/ASU/YEAR 3/Semester 2/CSE338 - Software testing/Labs/AirlineManagement(GUI-Tests)/AirlineManagement(GUI)/AirlineManagement1/src/application/flightss.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(flightsFilePath))) {
            List<String> departureLocations = new ArrayList<>();
            List<String> arrivalLocations = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(",");
                if (flightData.length >= 3) {
                    departureLocations.add(flightData[1]); // Departure place
                    arrivalLocations.add(flightData[2]);   // Arrival place
                }
            }

            // Populate departure locations in combo box
            from_COMBOBOX.setItems(FXCollections.observableArrayList(departureLocations));
           

            // Populate arrival locations based on default departure location
          

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateArrivalLocations(String departureLocation) {
        String flightsFilePath = "F:/ASU/YEAR 3/Semester 2/CSE338 - Software testing/Labs/AirlineManagement(GUI-Tests)/AirlineManagement(GUI)/AirlineManagement1/src/application/flightss.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(flightsFilePath))) {
            List<String> arrivalLocations = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(",");
                if (flightData.length >= 3 && flightData[1].equals(departureLocation)) {
                    arrivalLocations.add(flightData[2]); // Arrival place for matching departure location
                }
            }

            // Populate arrival locations in combo box
            to_COMBOBOX.setItems(FXCollections.observableArrayList(arrivalLocations));
            to_COMBOBOX.setValue(arrivalLocations.get(0)); // Default selection
            

          String arrivalLocation = to_COMBOBOX.getValue();
          System.out.println(arrivalLocation);
            // Update flights based on selected departure and arrival locations
           // updateFlights(departureLocation, arrivalLocation);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFlights(String departureLocation, String arrivalLocation) {
        String flightsFilePath = "F:/ASU/YEAR 3/Semester 2/CSE338 - Software testing/Labs/AirlineManagement(GUI-Tests)/AirlineManagement(GUI)/AirlineManagement1/src/application/flightss.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(flightsFilePath))) {
            List<String> matchingFlights = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(",");
                if (flightData.length >= 3) {
                    String flightDeparture = flightData[1].trim();
                    String flightArrival = flightData[2].trim();

                    if (flightDeparture.equalsIgnoreCase(departureLocation) && flightArrival.equalsIgnoreCase(arrivalLocation)) {
                        matchingFlights.add(String.format("%s - Departure: %s, Arrival: %s", flightData[0], flightData[3], flightData[4]));
                    }
                }
            }

            // Populate matching flights in combo box
            Flight_combobox.setItems(FXCollections.observableArrayList(matchingFlights));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void update_btn_CLICKED(ActionEvent event) {
    	String departureLocation = from_COMBOBOX.getValue();
    	String arrivalLocation = to_COMBOBOX.getValue();
    	
    	updateFlights(departureLocation, arrivalLocation);

    }

}
