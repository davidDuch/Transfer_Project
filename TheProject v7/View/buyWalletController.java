package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import Controller.Sys;
import Controller.UserLogic;
import Model.Wallet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


public class buyWalletController implements Initializable{

    @FXML
    private JFXToggleButton pcToggle;

    @FXML
    private JFXToggleButton tabletToggle;

    @FXML
    private JFXToggleButton mobileToggle;

    @FXML
    private JFXTextField autoAddress;

    @FXML
    private Label totalPriceLabelK;

    @FXML
    private StackPane knotsStack;

    @FXML
    private JFXButton buyBtnK;

    @FXML
    private Label totalPriceLabelS;

    @FXML
    private StackPane spaceStack;

    @FXML
    private JFXButton buyBtnS;
    
    @FXML
    private JFXComboBox<String> knot;

    @FXML
    private JFXComboBox<String> space;

    @FXML
    private JFXComboBox<Wallet> paymentCombo;

    @FXML
    private Label errorLabel;

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			autoAddress.setText("W"+Wallet.walletsCount);
			knot.getItems().addAll(Sys.getListKnots());
			space.getItems().addAll(Sys.getListSpace());
			if(UserLogic.getUserWallets(Sys.currentUser)!=null) {
				for(Wallet w : UserLogic.getUserWallets(Sys.currentUser)) {
					if(w!=null)
						paymentCombo.getItems().add(w);
				}
			}
		}

    @FXML
    public boolean Space(ActionEvent event) {
    	if(paymentCombo.getSelectionModel().getSelectedItem()==null) {
    		errorLabel.setText("Paying wallet wasn't chosen");
    		return false;
    	}
    	if(space.getSelectionModel().getSelectedItem() == null) {
    		errorLabel.setText("Wallet Size wasn't chosen");
    		return false;
    	}
    	String s = space.getSelectionModel().getSelectedItem();
    	String[] c = s.split(" ");
    	System.out.println(c);
    	Double size = Double.parseDouble(c[0].replaceAll("[^0-9.]", ""));
    	Double fund = Double.parseDouble(c[2].replaceAll("[^0-9.]", ""));
    	Wallet w = paymentCombo.getSelectionModel().getSelectedItem();
    	if(w.getFunds() < fund) {
    		errorLabel.setText("Not enough funds in paying wallet");
    		return false;
    	}
    	if(pcToggle.isSelected()==false && mobileToggle.isSelected()==false && tabletToggle.isSelected()==false) {
    		errorLabel.setText("Choose atleast one Platform");
    		return false;
    	}
    	UserLogic.add_wallet(pcToggle.isSelected(), tabletToggle.isSelected(), mobileToggle.isSelected(), 0, 0, Sys.currentUser.getPublicAddress(),Sys.currentUser.getDigitalSignature());
        Wallet wallet = new Wallet (autoAddress.getText());
    	UserLogic.addWalletSpace(wallet, size);
    	UserLogic.updateFVoFWallet(w, -fund);
    	UserLogic.updateWalletFunds(w);
    	JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Successfull Purchase!"));
        content.setBody(new Text());
        JFXDialog dialog = new JFXDialog(spaceStack, content, JFXDialog.DialogTransition.CENTER);
        JFXButton close = new JFXButton("Close");
        close.setOnAction(new EventHandler<ActionEvent>() {

    		@Override
    		public void handle(ActionEvent event) {
    			dialog.close();
        content.setActions(close);
        }
        });
        dialog.show();
        return true;
        }
	
    
    @FXML
    public boolean Knots(ActionEvent event) {
    	if(paymentCombo.getSelectionModel().getSelectedItem()==null) {
    		errorLabel.setText("Paying wallet wasn't chosen");
    		return false;
    	}
    	if(knot.getSelectionModel().getSelectedItem() == null) {
    		errorLabel.setText("Wallet Discount wasn't chosen");
    		return false;
    	}
    	String s = knot.getSelectionModel().getSelectedItem();
    	String[] c = s.split(" ");
    	Double discount = Double.parseDouble(c[0].replaceAll("[^0-9.]", ""));
    	Double fund = Double.parseDouble(c[2].replaceAll("[^0-9.]", ""));
    	Wallet w = paymentCombo.getSelectionModel().getSelectedItem();
    	System.out.println(discount);
    	System.out.println(fund);
    	if(w.getFunds() < fund) {
    		errorLabel.setText("Not enough funds in paying wallet");
    		return false;
    	}
    	if(pcToggle.isSelected()==false && mobileToggle.isSelected()==false && tabletToggle.isSelected()==false) {
    		errorLabel.setText("Choose atleast one Platform");
    		return false;
    	}
    	UserLogic.add_wallet(pcToggle.isSelected(), tabletToggle.isSelected(), mobileToggle.isSelected(), 0, 0, Sys.currentUser.getPublicAddress(),Sys.currentUser.getDigitalSignature());
        Wallet wallet = new Wallet (autoAddress.getText());
    	UserLogic.addWalletKnots(wallet, discount);
    	UserLogic.updateFVoFWallet(w, -fund);
    	UserLogic.updateWalletFunds(w);
    JFXDialogLayout content = new JFXDialogLayout();
    content.setHeading(new Text("Successfull Purchase!"));
    content.setBody(new Text());
    JFXDialog dialog = new JFXDialog(knotsStack, content, JFXDialog.DialogTransition.CENTER);
    JFXButton close = new JFXButton("Close");
    close.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			dialog.close();
    content.setActions(close);
    }
    });
    dialog.show();
    return true;
    }
    
}
