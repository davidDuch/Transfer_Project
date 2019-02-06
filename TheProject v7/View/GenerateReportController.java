package View;

import java.sql.SQLException;
import java.util.jar.JarException;

import javax.swing.JFrame;

import Controller.TransactionLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import net.sf.jasperreports.engine.JRException;

public class GenerateReportController {

	@FXML
	private AnchorPane framePane;

	@FXML
	private Button logoutButton;

	@FXML
	private Button gTransButton;

	@FXML
	private Button gUsersButton;

	@FXML
	private Label timeGeneratedLabel;

	@FXML
	private TabPane reportsTabPane;

	@FXML
	private Tab transTab;

	@FXML
	private Tab usersTab;

	@FXML
	private Button createAdviceButton;

	@FXML
	private Button backButton;

	@FXML
	private void GenerateTransReport() {//throws JarException, ClassNotFoundException, SQLException, JRException {
//		JFrame report = new JFrame();
//		report = TransactionLogic.getInstance().createReport();
//		report.setVisible(true);
	}

	@FXML
	void GenerateUserReport(ActionEvent event) {

	}

	@FXML
	void createAdvice(ActionEvent event) {
		
		ViewLogic.GenerateReportController = this;
		Stage stage = (Stage) logoutButton.getScene().getWindow();
		stage.close();
		ViewLogic.newCreateAdviseScreen();

	}

	@FXML
	void goBack(ActionEvent event) {
		ViewLogic.GenerateReportController = null;
		Stage stage = (Stage) logoutButton.getScene().getWindow();
		stage.close();
		ViewLogic.newDashBoard();
	}

	@FXML
	void logOut(ActionEvent event) {

	}

}
