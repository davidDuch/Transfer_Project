package View;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import Controller.UserLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


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
	    private Label totalPriceLabelS;

	    @FXML
	    private StackPane spaceStack;

	    @FXML
	    private JFXButton buyBtnS;

	    @FXML
	    private Label totalPriceLabelK;

	    @FXML
	    private StackPane knotsStack;

	    @FXML
	    private JFXButton buyBtnK;
    

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			autoAddress.setText(UserLogic.generateWallets());
		}

//    @FXML
//    public void Space(ActionEvent event) {
//    	
//        JFXDialogLayout content = new JFXDialogLayout();
//        content.setHeading(new Text(""));
//        content.setBody(new Text());
//        JFXTextField space = new JFXTextField();
//        space.setPromptText("New Space");
//        JFXDialog dialog = new JFXDialog(stackSpace, content, JFXDialog.DialogTransition.CENTER);
//        JFXButton close = new JFXButton("Close");
//        close.setOnAction(new EventHandler<ActionEvent>() {
//
//    		@Override
//    		public void handle(ActionEvent event) {
//    			dialog.close();
//        content.setActions(close);
//        }
//        });
//        dialog.show();
//        }
//	
    
    @FXML
    public void Knots(ActionEvent event) {
    	
    }

    @FXML
    public void buyWallet(ActionEvent event) {
    	
    }

}
