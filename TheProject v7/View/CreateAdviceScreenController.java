package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import Controller.AdviseLogic;
import Controller.UserLogic;
import Model.Advice;
import Model.Commitment;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private TableView<Advice> advicesTable;

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

	@FXML
	private TableView<DataForAdTable> adviceTable;

	@FXML
	private TableColumn<?, ?> adIdColumn;

	@FXML
	private TableColumn<?, ?> commitmentIdColumn;

	ArrayList<User> users = new ArrayList<>();

	private class DataForAdTable {
		
		Commitment lvl;
		int id;
		public DataForAdTable(Commitment lvl, int id) {
			super();
			this.lvl = lvl;
			this.id = id;
		}
		
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		users.addAll(UserLogic.getUsers());

		System.out.println(users);

		userNameC.setCellValueFactory(new PropertyValueFactory<>("userName"));
		emailC.setCellValueFactory(new PropertyValueFactory<>("email"));
		publicAddressC.setCellValueFactory(new PropertyValueFactory<>("PublicAddress"));
		publicSignatureC.setCellValueFactory(new PropertyValueFactory<>("DigitalSignature"));

		adviceId.setCellValueFactory(new PropertyValueFactory<>("adviceId"));
		creationDateC.setCellValueFactory(new PropertyValueFactory<>("date"));
		prefPer.setCellValueFactory(new PropertyValueFactory<>("prefPercent"));
		commisionRate.setCellValueFactory(new PropertyValueFactory<>("adviceComission"));

		setUsersTable();
		setAdviceTable();

	}

	private void setAdviceTable() {
		ObservableList<Advice> ad = FXCollections.observableArrayList();
		ad.addAll(AdviseLogic.getAllAdvises());
		advicesTable.setItems(ad);
	}

	private void setUsersTable() {
		ObservableList<User> tempUsers = FXCollections.observableArrayList();
		tempUsers.addAll(UserLogic.getUsers());
		usersTable.setItems(tempUsers);

	}

}
