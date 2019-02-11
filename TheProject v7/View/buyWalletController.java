package View;

import java.net.URL;
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
    private JFXComboBox<Double> knot;

    @FXML
    private JFXComboBox<Double> space;

    @FXML
    private JFXComboBox<String> paymentCombo;
    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			autoAddress.setText("W"+Wallet.walletsCount);
			knot.getItems().addAll(Sys.getListKnots());
			space.getItems().addAll(Sys.getListSpace());
			if(UserLogic.getUserWallets(Sys.currentUser)!=null) {
				for(Wallet w : UserLogic.getUserWallets(Sys.currentUser)) {
					System.out.println(w);
					if(w!=null)
						paymentCombo.getItems().add(w.toString());
				}
			}
		
			}

    @FXML
    public void Space(ActionEvent event) {
    	UserLogic.add_wallet(pcToggle.isSelected(), tabletToggle.isSelected(), mobileToggle.isSelected(), 0, 0, Sys.currentUser.getPublicAddress(),Sys.currentUser.getDigitalSignature());
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(""));
        content.setBody(new Text());
        JFXTextField space = new JFXTextField();
        space.setPromptText("New Space");
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
        }
	
    
    @FXML
    public void Knots(ActionEvent event) {
    UserLogic.add_wallet(pcToggle.isSelected(), tabletToggle.isSelected(), mobileToggle.isSelected(), 0, 0, Sys.currentUser.getPublicAddress(),Sys.currentUser.getDigitalSignature());
    JFXDialogLayout content = new JFXDialogLayout();
    content.setHeading(new Text(""));
    content.setBody(new Text());
    JFXTextField space = new JFXTextField();
    space.setPromptText("New Space");
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
    }
    	
}
