package View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import Controller.Sys;
import Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class buyWalletController {

    @FXML
    private JFXToggleButton pcToggle;

    @FXML
    private JFXToggleButton tabletToggle;

    @FXML
    private JFXToggleButton mobileToggle;

    @FXML
    private JFXTextField autoAddress;
    
    @FXML
    private StackPane stackSpace;


    @FXML
    private StackPane stackKnots;
    @FXML
    
    private Button space;

    @FXML
    private Button knots;

    @FXML
    private JFXButton buyBtn;
    
    
    @FXML
    public void Space(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Add Amount of Space Needed: (MAX: " + Sys.maxPossibleExpansionSize + ")"));
        content.setBody(new Text());
        JFXTextField space = new JFXTextField();
        space.setPromptText("New Space");
        JFXDialog dialog = new JFXDialog(stackSpace, content, JFXDialog.DialogTransition.CENTER);
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
    	
    }

    @FXML
    public void buyWallet(ActionEvent event) {
    	
    }
}
