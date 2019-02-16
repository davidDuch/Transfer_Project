package View;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
public class createConfirmController implements Initializable{
    @FXML
    private JFXTextField transId;

    @FXML
    private JFXTextArea desc;

    @FXML
    private JFXComboBox<Wallet> walletCombo;

    @FXML
    private JFXTextField transSize;

    @FXML
    private JFXTextField commisionT;

    @FXML
    private JFXTextField yAddress;

    @FXML
    private JFXTextField ySign;

    @FXML
    private JFXTextField bAddress;

    @FXML
    private JFXTextField bSign;

    @FXML
    private JFXTextField createDate;

    @FXML
    private JFXCheckBox approveBox;

    @FXML
    private JFXDatePicker supplyDate;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXButton Create;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		transId.setText("CON "+ UserLogic.generateRandoms());
		transSize.setText(Double.toString(Sys.currentPay.getSize()));
		walletCombo.getItems().addAll(UserLogic.getUserWallets(Sys.currentUser));
		yAddress.setText(Sys.currentUser.getPublicAddress());
		ySign.setText(Sys.currentUser.getDigitalSignature());
		bAddress.setText(Sys.currentPay.getCreatorAddress());
		bSign.setText(Sys.currentPay.getCreatorSignature());
		Date date = new Date();
		createDate.setText(date.toString());
	}
	@FXML
	public boolean createConfirm(ActionEvent event) {
    	try {
        	double a = Double.parseDouble(transSize.getText());
        	double b = Double.parseDouble(commisionT.getText());
    	}catch (NumberFormatException e) {
    		errorLabel.setText("Size/Commission is Invalid");
    		return false;
    	}
    	if(supplyDate.getValue() == null) {
    		errorLabel.setText("Supply Date Not Selected");
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
    	double size = Double.parseDouble(transSize.getText());
    	double commission = Double.parseDouble(commisionT.getText());
    	String creatorAddress = yAddress.getText();
    	String creatorSignature = ySign.getText();
    	String sellerAddress = bAddress.getText();
    	String sellerSignature = bSign.getText();
    	Date date = new Date();
    	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    	LocalDate supply = supplyDate.getValue();
    	Date sDate = Date.from(supply.atStartOfDay(ZoneId.systemDefault()).toInstant());
    	java.sql.Date supplySql = new java.sql.Date(sDate.getTime());
    	boolean approve = approveBox.isSelected();
    	if(w.getFunds() >= commission) {
    	TransactionLogic.addConfirm(id, description, size, sqlDate, sqlDate, Status.waiting, commission, supplySql, approve, sellerAddress, sellerSignature, creatorAddress, creatorSignature, w.getAddress());
    	UserLogic.updateFVoFWallet(w, -commission);
    	UserLogic.updateWalletFunds(w);
    	errorLabel.setText("Confirm Created!");
    	ManageTransactionsController.instance.initialize(null, null);
    	return true;
    	}
    	errorLabel.setText("Not Enoungh Funds");
    	return false;
    	}

	}
    

