package View;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;

import Controller.Sys;
import Controller.UserLogic;
import Model.BitcoinKnots;
import Model.BitcoinSpace;
import Model.Wallet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class upgradeWalletController implements Initializable{
	@FXML
    private JFXComboBox<Wallet> walletBox;

    @FXML
    private JFXToggleButton mobileToggle;

    @FXML
    private JFXToggleButton pcToggle;

    @FXML
    private JFXToggleButton tabletToggle;

    @FXML
    private JFXComboBox<Wallet> paymentCombo;
    
    @FXML
    private Tab knotsTab;
    
    @FXML
    private Tab SpaceTab;
    
    @FXML
    private JFXComboBox<String> knot;

    @FXML
    private JFXButton selectWallet;
    
    @FXML
    private StackPane knotsStack;

    @FXML
    private JFXButton buyBtnK;

    @FXML
    private JFXComboBox<String> space;

    @FXML
    private StackPane spaceStack;

    @FXML
    private JFXButton buyBtnS;
    
    @FXML
    private Label errorLabel;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		knotsTab.setDisable(true);
		SpaceTab.setDisable(true);
		knot.getItems().addAll(Sys.getListKnots());
		space.getItems().addAll(Sys.getListSpace());
		if(UserLogic.getUserWallets(Sys.currentUser)!=null) {
			for(Wallet w : UserLogic.getUserWallets(Sys.currentUser)) {
				if(w!=null)
					paymentCombo.getItems().add(w);
					walletBox.getItems().add(w);
			}
		}
		
	}
	@FXML
	public void SelectWallet(ActionEvent event) {
		knotsTab.setDisable(true);
		SpaceTab.setDisable(true);
		if(walletBox.getSelectionModel().getSelectedItem()==null)
			return;
		Wallet w = walletBox.getSelectionModel().getSelectedItem();
		ArrayList<BitcoinKnots> knotsAL = UserLogic.getKnots();
		ArrayList<BitcoinSpace> spaceAL = UserLogic.getSpace();
		for(BitcoinKnots K : knotsAL) {
			System.out.println(K.getAddress().equals( w.getAddress()));
			if(K.getAddress().equals( w.getAddress())) {
			knotsTab.setDisable(false);
			return;
		}
		}
		for(BitcoinSpace S : spaceAL) {
			System.out.println(S.getAddress().equals( w.getAddress()));
			if(S.getAddress().equals( w.getAddress())) {
			SpaceTab.setDisable(false);
			return;
		}
		}
			errorLabel.setText("Can't upgrade default Wallet");
	}
		

    @FXML
    public boolean Knots(ActionEvent event) {
    	if(walletBox.getSelectionModel().getSelectedItem()==null) {
    		errorLabel.setText("Wallet wasn't chosen");
    		return false;
    	}
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
        Wallet wallet = walletBox.getSelectionModel().getSelectedItem();
    	UserLogic.upgradeWalletToSpace(wallet, size);
    	UserLogic.updateFVoFWallet(w, -fund);
    	UserLogic.updateWalletFunds(w);
    	JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Successfull Upgrade!"));
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
    public boolean Space(ActionEvent event) {
    	if(walletBox.getSelectionModel().getSelectedItem()==null) {
    		errorLabel.setText("Wallet wasn't chosen");
    		return false;
    	}
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
        Wallet wallet = walletBox.getSelectionModel().getSelectedItem();
    	UserLogic.upgradeWalletToSpace(wallet, size);
    	UserLogic.updateFVoFWallet(w, -fund);
    	UserLogic.updateWalletFunds(w);
    	JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Successfull Upgrade!"));
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

