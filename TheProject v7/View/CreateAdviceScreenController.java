package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import Controller.AdviseLogic;
import Controller.UserLogic;
import Model.Advice;
import Model.User;
import Utils.Commitment;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateAdviceScreenController implements Initializable {

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
	private Button backButton;

	@FXML
	private Button sendButton;

	@FXML
	private Label labelAlert;

	@FXML
	private TableView<DataForAdTable> createdAdviceTable;

	@FXML
	private TableColumn<DataForAdTable, String> addressColumn;

	@FXML
	private TableColumn<DataForAdTable, String> signatureColumn;

	@FXML
	private TableColumn<DataForAdTable, Commitment> commitmentColumn;

	@FXML
	private ComboBox<Commitment> comboLvl;

	private ArrayList<User> userAdded = new ArrayList<>();

	// class used to fill the table that adds the users to the advice
	protected class DataForAdTable {

		private Commitment lvl;
		private String address;
		private String signature;

		public DataForAdTable(Commitment lvl, String address, String signature) {
			super();
			this.lvl = lvl;
			this.address = address;
			this.signature = signature;
		}

		public Commitment getLvl() {
			return lvl;
		}

		public void setLvl(Commitment lvl) {
			this.lvl = lvl;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		userNameC.setCellValueFactory(new PropertyValueFactory<>("userName"));
		emailC.setCellValueFactory(new PropertyValueFactory<>("email"));
		publicAddressC.setCellValueFactory(new PropertyValueFactory<>("PublicAddress"));
		publicSignatureC.setCellValueFactory(new PropertyValueFactory<>("DigitalSignature"));

		adviceId.setCellValueFactory(new PropertyValueFactory<>("adviceId"));
		creationDateC.setCellValueFactory(new PropertyValueFactory<>("date"));
		prefPer.setCellValueFactory(new PropertyValueFactory<>("prefPercent"));
		commisionRate.setCellValueFactory(new PropertyValueFactory<>("adviceComission"));

		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		signatureColumn.setCellValueFactory(new PropertyValueFactory<>("signature"));
		commitmentColumn.setCellValueFactory(new PropertyValueFactory<>("lvl"));

		usersTable.setItems(FXCollections.observableArrayList(UserLogic.getUsers()));
		ArrayList<Advice> temp = AdviseLogic.getAllAdvises();
		advicesTable.setItems(FXCollections.observableArrayList(temp));
		idInput.setText(Integer.toString(temp.size() + 1));

		comboLvl.setItems(FXCollections.observableArrayList(Commitment.values()));
		comboLvl.getSelectionModel().select(0);

	}

	@FXML
	private void addUser(ActionEvent event) {

		User user = usersTable.getSelectionModel().getSelectedItem();
		if (user != null) {
			usersTable.getItems().remove(user);
			Commitment com = comboLvl.getSelectionModel().getSelectedItem();
			createdAdviceTable.getItems()
					.add(new DataForAdTable(com, user.getPublicAddress(), user.getDigitalSignature()));
			userAdded.add(user);
		}

	}

	@FXML
	private void clearForm(ActionEvent event) {
		commisionInput.clear();
		prefInput.clear();
		labelAlert.setText("");
		createdAdviceTable.getItems().clear();
		usersTable.getItems().addAll(userAdded);
		userAdded.clear();

	}

	@FXML
	private void createAdvice(ActionEvent event) {
		String strCommision = commisionInput.getText();
		String strPref = prefInput.getText();

		try {
			int com = Integer.parseInt(strCommision);
			try {
				int pref = Integer.parseInt(strPref);
				if (com >= 0) {
					if (pref >= 0) {
						addUserButton.setDisable(false);
						comboLvl.setDisable(false);
						createAdviceButton.setVisible(false);
						resetForm.setVisible(false);
						sendButton.setDisable(false);


					} else
						labelAlert.setText("invalid preference percentage");

				} else
					labelAlert.setText("invalid commission");

			} catch (Exception e) {
				labelAlert.setText("invalid preference percentage");

			}
		} catch (Exception e) {
			labelAlert.setText("invalid commission");

		}

	}

	@FXML
	void sendAdvice(ActionEvent event) {

		Calendar cal = Calendar.getInstance();
		int id = Integer.parseInt(idInput.getText());
		AdviseLogic.addAdvice(id, cal, Integer.parseInt(commisionInput.getText()),
				Integer.parseInt(prefInput.getText()));
		commisionInput.clear();
		prefInput.clear();
		labelAlert.setText("");

		Advice ad = new Advice(Integer.parseInt(idInput.getText()));

		if (!userAdded.isEmpty())
			for (int i = 0; i < userAdded.size(); i++)
				AdviseLogic.addCommitment(userAdded.get(i), ad, createdAdviceTable.getItems().get(i).lvl);

		createdAdviceTable.getItems().clear();
		usersTable.getItems().addAll(userAdded);
		userAdded.clear();
		idInput.setText(Integer.toString(++id));
		ArrayList<Advice> temp = AdviseLogic.getAllAdvises();
		advicesTable.setItems(FXCollections.observableArrayList(temp));
		idInput.setText(Integer.toString(temp.size() + 1));
	}

	@FXML
	private void goBack(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();

		if (ViewLogic.GenerateReportController != null) {
			ViewLogic.newReportGenerator();
		} else {
			ViewLogic.newDashBoard();

		}

	}

}
