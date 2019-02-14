package View;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Controller.Sys;
import Controller.UserLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class updateQuantityController implements Initializable{
	    @FXML
	    private JFXTextField nameT;

	    @FXML
	    private JFXTextField quantityUpdate;

	    @FXML
	    private Label errorLabel;

	    @FXML
	    private JFXButton updateBtn;

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			nameT.setText(Sys.myProduct.toString());
		}
		@FXML
		public void updateQuantity() {
	    	try {
	        	int q = Integer.parseInt(quantityUpdate.getText());
	    	}catch (NumberFormatException e) {
	    		errorLabel.setText("Quantity Error");
	    		return;
	    	}
        	int q = Integer.parseInt(quantityUpdate.getText());
        	if(q<=0) {
	    		errorLabel.setText("Quantity Error");
	    		return;
		}
        	UserLogic.updateProductAmount(Sys.myProduct, q);
        	errorLabel.setText("Quantity Updated");
        	return;
	}

}
