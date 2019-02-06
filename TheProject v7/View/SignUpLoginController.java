package View;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Controller.Sys;
import Controller.UserLogic;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUpLoginController {

    @FXML
    private VBox LoginBox;

    @FXML
    private JFXTextField inputUser;

    @FXML
    private JFXPasswordField inputPass;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private Label loginError;

    @FXML
    private JFXTextField newUser;

    @FXML
    private JFXPasswordField pass1;

    @FXML
    private JFXPasswordField pass2;

    @FXML
    private JFXTextField newEmail;

    @FXML
    private JFXTextField newPhone;

    @FXML
    private JFXButton signUpBtn;

    @FXML
    private Label signUpError;



	/**
	 * Customer edits some of his own details
	 *
	 * @param event
	 * @return
	 * @throws IOException
	 */
	public boolean SignUp(ActionEvent event) throws IOException {
		if (newUser.getText() == null || newUser.getText().equals("")) {
			signUpError.setText("No UserName");
			return false;
		}
		for(User userNameCheck : UserLogic.getUsers()) {
			userNameCheck.getUserName().equals(newUser.getText());
			signUpError.setText("Please Choose a different Username");
			return false;
		}
		if (pass1.getText().isEmpty() || pass1.getText() == ("")) {
			signUpError.setText("Input Password");
			return false;
		}
		if (pass2.getText() == null || pass2.getText() == ("")) {
			signUpError.setText("Password Validation is empty");
			return false;
		}
		if (!pass1.getText().equals(pass2.getText())) {
			signUpError.setText("Not matching passwords");
			return false;
		}
		if (newEmail.getText() == null || newEmail.getText() == ("")) {
			signUpError.setText("Please provide an Email address");
			return false;
		}
		if (newPhone.getText() == null || newPhone.getText() == ("") || newPhone.getText().isEmpty()) {
			signUpError.setText("Please provide a Phone Number");
			return false;
		}

		if (emailCheck()) {
			String publicAddress = UserLogic.generateRandoms();
			String digitalSignature = UserLogic.generateRandoms();
			UserLogic.addNewUser(publicAddress, digitalSignature, newUser.getText(), pass1.getText(), newPhone.getText(), newEmail.getText());
			newUser.clear();
			pass1.clear();
			pass2.clear();
			newEmail.clear();
			newPhone.clear();
			signUpError.setText("Sign Up Successful");
			return true;
		}
		return false;
	}

	// **************************************
	/**
	 * Email VALIDATION PATTERN
	 *
	 * @return
	 */
	public boolean emailCheck() {
		String email = newEmail.getText();
		if (validate(email) == false) {
			signUpError.setText("Invalid email address");
			return false;
		} else {
			return true;
		}
	}

	public static final Pattern VALIDEMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALIDEMAIL.matcher(emailStr);
		return matcher.find();
    
}
	
	public boolean Login(ActionEvent event) throws IOException {
		if (inputUser.getText() == null || inputUser.getText().equals("")) {
			loginError.setText("No UserName");
			return false;
		}
		if (inputPass.getText() == null || inputPass.getText().equals("")) {
			loginError.setText("No Password");
			return false;
		}
		for(User fromDB :UserLogic.getUsers()){
			if(fromDB.getUserName().equals(inputUser.getText()) && fromDB.getPassword().equals(inputPass.getText())) {
			loginError.setText("Successful Login");
			Sys.currentUser = fromDB;
		    Stage stage = (Stage) loginError.getScene().getWindow();
		    stage.close();
			ViewLogic.newDashBoard();
			return true;
		}
		}
		loginError.setText("Wrong Details");
		return false;
	}
}
