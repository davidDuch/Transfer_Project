package View;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashBoard {

    @FXML
    private Button ReportButton;

    @FXML
    private Button createAfviceButton;

    @FXML
    private Button logOutButton;

    @FXML
    void goToCreateAdvice(ActionEvent event) {
    	Stage stage = (Stage) logOutButton.getScene().getWindow();
    	stage.close();
    	ViewLogic.newCreateAdviseScreen();
    }

    @FXML
    void goToReport(ActionEvent event) {
    	Stage stage = (Stage) logOutButton.getScene().getWindow();
    	stage.close();
    	ViewLogic.newReportGenerator();

    }

    @FXML
    void logOut(ActionEvent event) {
    	Stage stage = (Stage) logOutButton.getScene().getWindow();
    	stage.close();


    }

}
