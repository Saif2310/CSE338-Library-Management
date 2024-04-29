package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomepageController {
	
	public HomepageController() {
	}

    @FXML
    private Button Add_booking_btn;

    @FXML
    private Button Check_Booking_btn;

    @FXML
    private Button Check_Flights_btn;

    @FXML
    private Label LBL_Name;
    
    @FXML
    private Button log_out_btn;
    
    private String name;

    @FXML
    void Add_booking_btn_clicked(ActionEvent event) throws IOException {
    	Main m = new Main();
		m.toABscene("AddBooking.fxml", name);
		System.out.println("add booking");
    }

    @FXML
    void Check_Booking_btn_clicked(ActionEvent event) throws IOException {
    	Main m = new Main();
		m.toCBscene("CheckBookings.fxml", name);
		System.out.println("check bookings");
    }

    @FXML
    void Check_Flights_btn_clicked(ActionEvent event) throws IOException {
    	Main m = new Main();
		m.toCFscene("CheckFlights.fxml", name);
		System.out.println("check flights");
    }
    
    @FXML
    void log_out_btn_CLICKED(ActionEvent event) throws IOException {
    	Main m = new Main();
		m.changeScene("Login.fxml");
		System.out.println("logged out");

    }

	public void setUsername(String username) {
		name = username;
		
	}




}