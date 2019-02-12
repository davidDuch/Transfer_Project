package View;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
public class BuyProductTransactionPayController implements Initializable{

	    @FXML
	    private JFXTextField transId;

	    @FXML
	    private JFXTextArea desc;

	    @FXML
	    private JFXComboBox<?> walletCombo;

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
			// TODO Auto-generated method stub
			
		}
	    
	    

	}

