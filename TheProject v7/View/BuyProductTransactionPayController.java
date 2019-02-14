package View;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Controller.Sys;
import Controller.UserLogic;
import Model.Wallet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
public class BuyProductTransactionPayController implements Initializable{

	    @FXML
	    private JFXTextField transId;

	    @FXML
	    private JFXTextArea desc;

	    @FXML
	    private JFXComboBox<Wallet> walletCombo;

	    @FXML
	    private JFXComboBox<String> amountCombo;
	    
	    @FXML
	    private JFXTextField transSize;

	    @FXML
	    private JFXTextField commisionT;

	    @FXML
	    private JFXTextField yAddress;

	    @FXML
	    private JFXTextField ySign;

	    @FXML
	    private JFXTextField sAddress;

	    @FXML
	    private JFXTextField sSign;

	    @FXML
	    private JFXTextField createDate;

	    @FXML
	    private Label errorLabel;

	    @FXML
	    private JFXButton Create;

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			transId.setText("PAY"+ UserLogic.generateRandoms());
			walletCombo.getItems().addAll(UserLogic.getUserWallets(Sys.currentUser));
			yAddress.setText(Sys.currentUser.getPublicAddress());
			ySign.setText(Sys.currentUser.getDigitalSignature());
			sAddress.setText(Sys.chosenProduct.getSellerAddress());
			sSign.setText(Sys.chosenProduct.getSellerSignature());
			Date date = new Date();
			createDate.setText(date.toString());
			amountCombo.getItems().addAll(Sys.getListProduct());
		}
	    
	    

	}

