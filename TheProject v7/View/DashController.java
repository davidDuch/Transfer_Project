package View;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import Controller.Sys;
import Controller.UserLogic;
import Model.Advice;
import Model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashController implements Initializable{

    @FXML
    private JFXButton pBtn;

    @FXML
    private JFXButton myPBtn;

    @FXML
    private JFXButton transBtn;

    @FXML
    private JFXButton walletBtn;

    @FXML
    private JFXButton Logout;

    @FXML
    private TableView<Advice> adviceTbl;

    @FXML
    private TableColumn<Advice, String> adviceIdCol;

    @FXML
    private TableColumn<Advice, Date> dateCol;

    @FXML
    private TableColumn<Advice, Double> commisionCol;

    @FXML
    private TableColumn<Advice, Double> prefCol;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton dialog;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	adviceIdCol.setCellValueFactory(new PropertyValueFactory<>("adviceId"));
    	dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    	commisionCol.setCellValueFactory(new PropertyValueFactory<>("adviceComission"));
    	prefCol.setCellValueFactory(new PropertyValueFactory<>("prefPercent"));
    	adviceTbl.setItems(FXCollections.observableArrayList(UserLogic.getUsersAdvice(Sys.currentUser)));


		
	}

    @FXML
    public void getDetails(ActionEvent event) {
    JFXDialogLayout content = new JFXDialogLayout();
    content.setHeading(new Text("Your Details are: "));
    User current = Sys.currentUser;
    content.setBody(new Text("Public Address: " + current.getPublicAddress() + "\n" + "Digital Signature: " + current.getDigitalSignature()));
    JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
    JFXButton close = new JFXButton("Close");
    close.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			dialog.close();
    content.setActions(close);
    }
    });
    dialog.show();
    }
   // getUsersAdvice
	@FXML
	void myWallets(ActionEvent event) {
		ViewLogic.walletMenuScreen();
	}
	@FXML
	void buyProducts(ActionEvent event) {
		ViewLogic.buyProducts();
	}

	@FXML
	void myProducts(ActionEvent event) {
		ViewLogic.myProducts();
	}
	@FXML
	void myTransactions(ActionEvent event) {
		ViewLogic.myTransactions();
	}
	@FXML
	void Logout(ActionEvent event) {
		Stage stage = (Stage) Logout.getScene().getWindow();
		stage.close();

	}


}
