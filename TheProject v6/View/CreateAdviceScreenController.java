package View;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import Controller.UserLogic;
import Model.Advice;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CreateAdviceScreenController implements Initializable {

    @FXML
    private AnchorPane framePane;

    @FXML
    private AnchorPane adviceCreationPane;

    @FXML
    private Label header;

    @FXML
    private Button createAdviceButton;

    @FXML
    private Button resetForm;

    @FXML
    private TextField idInput;

    @FXML
    private TextField commisionInput;

    @FXML
    private TextField prefInput;

    @FXML
    private AnchorPane advicesTablePane;

    @FXML
    private TableView<?> advicesTable;

    @FXML
    private TableColumn<Advice, String> adviceId;

    @FXML
    private TableColumn<Advice, Double> commisionRate;

    @FXML
    private TableColumn<Advice, Double> prefPer;

    @FXML
    private TableColumn<Advice, Calendar> creationDateC;

    @FXML
    private AnchorPane usersTablePane;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, String> userNameC;

    @FXML
    private TableColumn<User, String> emailC;

    @FXML
    private TableColumn<User, String> publicAddressC;

    @FXML
    private TableColumn<User, String> publicSignatureC;

    @FXML
    private Button addUserButton;

    @FXML
    private Button removeUserButton;

    @FXML
    private Button backButton;

    @FXML
    private Button logoutButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ArrayList<User> users = UserLogic.getUsers();
		
		userNameC.setCellValueFactory(new PropertyValueFactory<>("userName"));
		emailC.setCellValueFactory(new PropertyValueFactory<>("email"));
		publicAddressC.setCellValueFactory(new PropertyValueFactory<>("PublicAddress"));
		publicSignatureC.setCellValueFactory(new PropertyValueFactory<>("DigitalSignature"));
		
		setUsersTable();

		
	}

	private void setUsersTable() {
		ArrayList<User> users = UserLogic.getUsers();
		
		
	}

}
