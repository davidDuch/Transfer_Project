package View;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import Utils.Consts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    void GenerateTransReport(ActionEvent event) {
    	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    	try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
    	JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("../boundary/RptCustomerOrders.jasper"),conn);
    	Stage stage = (Stage) framePane.getScene().getWindow();
    	frame.getContentPane().add(new JRViewer(print));
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	frame.pack();
    	return frame;
    	} catch . . .

    }

    @FXML
    void GenerateUserReport(ActionEvent event) {

    }

    @FXML
    void createAdvice(ActionEvent event) {
    	Stage stage = (Stage) logoutButton.getScene().getWindow();
    	stage.close();
    	ViewLogic.newCreateAdviseScreen();

    }

    @FXML
    void goBack(ActionEvent event) {
    	Stage stage = (Stage) logoutButton.getScene().getWindow();
    	stage.close();
    	ViewLogic.newDashBoard();
    }
    
    @FXML
    void logOut(ActionEvent event) {

    }


}
