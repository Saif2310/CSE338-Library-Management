package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	private static Stage stage ;
    @Override
    public void start(Stage primaryStage) throws IOException {
       
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        System.out.println("1");
        Scene scene = new Scene(root);
       
    
      primaryStage.setTitle("Login");
      primaryStage.setScene(scene);
      primaryStage.show();
  }

  public void changeScene(String fxml) throws IOException {
    Parent pane = FXMLLoader.load(getClass().getResource(fxml));
    stage.getScene().setRoot(pane);
  }
  
  public void toHPscene(String fxml, String username) throws IOException {
	  	
	  FXMLLoader loader = new FXMLLoader(Main.class.getResource("Homepage.fxml"));
      Parent root = loader.load();
      HomepageController controller = loader.getController();
      controller.setUsername(username);
      stage.setScene(new Scene(root));
	  
	  }
  public void toCBscene(String fxml, String username) throws IOException {
	  FXMLLoader loader = new FXMLLoader(Main.class.getResource("CheckBookings.fxml"));
      Parent root = loader.load();
      CheckBookingsController controller = loader.getController();
      controller.setUsername(username);
      stage.setScene(new Scene(root));
	  }
  
  public void toABscene(String fxml, String username) throws IOException {
	  FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddBooking.fxml"));
      Parent root = loader.load();
      AddBookingController controller = loader.getController();
      controller.setUsername(username);
      stage.setScene(new Scene(root));
	  }
  
  public void toCFscene(String fxml, String username) throws IOException {
	  FXMLLoader loader = new FXMLLoader(Main.class.getResource("CheckFlights.fxml"));
      Parent root = loader.load();
      CheckFlightsController controller = loader.getController();
      controller.setUsername(username);
      stage.setScene(new Scene(root));
	  }
  
	
	public static void main(String[] args) {
		launch(args);
	}
}