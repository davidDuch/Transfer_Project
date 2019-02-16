package View;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Controller.Sys;
import Controller.TransactionLogic;
import Controller.UserLogic;
import Model.Wallet;
import Utils.Status;
import javafx.event.ActionEvent;
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
			transId.setText("PAY "+ UserLogic.generateRandoms());
			walletCombo.getItems().addAll(UserLogic.getUserWallets(Sys.currentUser));
			yAddress.setText(Sys.currentUser.getPublicAddress());
			ySign.setText(Sys.currentUser.getDigitalSignature());
			sAddress.setText(Sys.chosenProduct.getSellerAddress());
			sSign.setText(Sys.chosenProduct.getSellerSignature());
			Date date = new Date();
			createDate.setText(date.toString());
			amountCombo.getItems().addAll(Sys.getListProduct());
		}
	    @FXML
	    public boolean addPay(ActionEvent event) {
	    	try {
	        	double a = Double.parseDouble(transSize.getText());
	        	double b = Double.parseDouble(commisionT.getText());
	    	}catch (NumberFormatException e) {
	    		errorLabel.setText("Size/Commission is Invalid");
	    		return false;
	    	}
	    	if(amountCombo.getSelectionModel().getSelectedItem() == null) {
	    		errorLabel.setText("Amount Not Selected");
	    		return false;
	    	}
	    	if(walletCombo.getSelectionModel().getSelectedItem() == null) {
	    		errorLabel.setText("Wallet Not Selected");
	    		return false;

	    	}
	    	String description = "";
	    	String id = transId.getText();
	    	if(desc.getText()!=null)
	    	description = desc.getText();
	    	Wallet w = walletCombo.getSelectionModel().getSelectedItem();
	    	String s = amountCombo.getSelectionModel().getSelectedItem();
	    	String[] c = s.split(" ");
	    	int amount = Integer.parseInt(c[0].replaceAll("[^0-9.]", ""));
	    	Double btcAmount = Double.parseDouble(c[2].replaceAll("[^0-9.]", ""));
	    	double size = Double.parseDouble(transSize.getText());
	    	double commission = Double.parseDouble(commisionT.getText());
	    	String creatorAddress = yAddress.getText();
	    	String creatorSignature = ySign.getText();
	    	String sellerAddress = sAddress.getText();
	    	String sellerSignature = sSign.getText();
	    	Date date = new Date();
	    	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	    	System.out.println(date);
	    	if(w.getFunds() >= btcAmount+commission) {
	    	TransactionLogic.addPay(id, description, size, sqlDate, sqlDate, Status.waiting, btcAmount, commission, sellerAddress, sellerSignature, creatorAddress, creatorSignature, w.getAddress());
	    	UserLogic.updateProductAmount(Sys.chosenProduct, Sys.chosenProduct.getAmountAvailable()-amount);
	    	UserLogic.updateFVoFWallet(w, -(btcAmount+commission));
	    	UserLogic.updateWalletFunds(w);
	    	productScreenController.instance.initialize(null, null);
	    	errorLabel.setText("Purchase Complete!");
	    	return true;
	    	}
	    	errorLabel.setText("Not Enoungh Funds");
	    	return false;
	    	}
	    

	}

