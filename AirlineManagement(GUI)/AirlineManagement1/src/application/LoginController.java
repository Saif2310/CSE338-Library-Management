package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button Login_btn;

    @FXML
    private PasswordField Password_passwordfield;

    @FXML
    private Button printbtn;

    @FXML
    private TextField username_textedit;

    @FXML
    void login_btn_clicked(ActionEvent event) throws IOException {
    	String name = username_textedit.getText();
    	String pass = Password_passwordfield.getText();
    	System.out.println(name);
    	System.out.println(pass);
    	passenger p = new passenger (name,pass);
    	boolean validuser = p.AuthenticateUser();
    	System.out.println(validuser);
    	if(validuser) {
    		Main m = new Main();
    		m.toHPscene("Homepage.fxml", name);
    		System.out.println("validuser");
    	}
    	else {
    		System.out.println("invaliduser");
    	}
    	
    	
    }

    @FXML
    void printbtnclicked(ActionEvent event) {
    	System.out.println("1");
    }

}