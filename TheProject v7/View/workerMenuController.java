package View;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.jar.JarException;

import javax.swing.JFrame;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import Controller.Sys;
import Controller.TransactionLogic;
import Controller.WorkerLogic;
import Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

public class workerMenuController implements Initializable{

    @FXML
    private JFXButton newWorkerBtn;

    @FXML
    private JFXButton createAdviceBtn;

    @FXML
    private JFXButton categoryBtn;
    
    @FXML
    private JFXButton Logout;

    @FXML
    private StackPane updateStack;
    
    @FXML
    private JFXTextField discountExpandPriceF;

    @FXML
    private JFXTextField sizeExpandPriceF;

    @FXML
    private JFXTextField defaultWalletSizeF;

    @FXML
    private JFXTextField expandWalletSizeF;

    @FXML
    private JFXTextField expandDiscountSizeF;

    @FXML
    private JFXTextField maxPossibleExpansionSizeF;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton transReportBtn;

    @FXML
    private JFXButton userReportBtn;
    
    @FXML
    private JFXButton jasperBtn;
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		discountExpandPriceF.setText(Double.toString(Sys.discountExpandPrice));
		sizeExpandPriceF.setText(Double.toString(Sys.sizeExpandPrice));
		defaultWalletSizeF.setText(Double.toString(Sys.defualtWalletSize));
		expandWalletSizeF.setText(Double.toString(Sys.expendWalletSize));
		expandDiscountSizeF.setText(Double.toString(Sys.expendDiscountSize));
		maxPossibleExpansionSizeF.setText(Double.toString(Sys.maxPossibleExpansionSize));
		
	}
    
    @FXML
    public boolean updateSystemDetails(ActionEvent event) {
    	try {
        	double DEP = Double.parseDouble(discountExpandPriceF.getText());
        	double SEP = Double.parseDouble(sizeExpandPriceF.getText());
        	double DWS = Double.parseDouble(defaultWalletSizeF.getText());
        	double EWS = Double.parseDouble(expandWalletSizeF.getText());
        	double EDS = Double.parseDouble(expandDiscountSizeF.getText());
        	double PES = Double.parseDouble(maxPossibleExpansionSizeF.getText());
    	}catch (NumberFormatException e) {
    	    JFXDialogLayout content = new JFXDialogLayout();
    	    content.setHeading(new Text("Only Numbers are Allowed."));
    	    content.setBody(new Text("No changes have been made"+ "\n" +"Click anywhere to exit."));
    	    JFXDialog dialog = new JFXDialog(updateStack, content, JFXDialog.DialogTransition.CENTER);
    	    JFXButton close = new JFXButton("Close");
    	    close.setOnAction(new EventHandler<ActionEvent>() {

    			@Override
    			public void handle(ActionEvent event) {
    				dialog.close();
    	    content.setActions(close);
    	    }
    	    });
    	    dialog.show();
    		return false;
    	}
    	double DEP = Double.parseDouble(discountExpandPriceF.getText());
    	double SEP = Double.parseDouble(sizeExpandPriceF.getText());
    	double DWS = Double.parseDouble(defaultWalletSizeF.getText());
    	double EWS = Double.parseDouble(expandWalletSizeF.getText());
    	double EDS = Double.parseDouble(expandDiscountSizeF.getText());
    	double PES = Double.parseDouble(maxPossibleExpansionSizeF.getText());
    	WorkerLogic.setDiscountExpandPrice(DEP);
    	WorkerLogic.setExpandPrice(SEP);
    	WorkerLogic.setDefaultWalletSize(DWS);
    	WorkerLogic.setExpandWalletSize(EWS);
    	WorkerLogic.setExpandDiscountSize(EDS);
    	WorkerLogic.setMaxPossibleExpansionSize(PES);
    	Date date = new Date();
    	WorkerLogic.setDate(date);
    	
    JFXDialogLayout content = new JFXDialogLayout();
    content.setHeading(new Text("Updated Successfully."));
    content.setBody(new Text("Click anywhere to exit."));
    JFXDialog dialog = new JFXDialog(updateStack, content, JFXDialog.DialogTransition.CENTER);
    JFXButton close = new JFXButton("Close");
    close.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			dialog.close();
    content.setActions(close);
    }
    });
    dialog.show();
	return true;
    }
	@FXML
	void createCategory(ActionEvent event) {
		ViewLogic.createCategory();
	}
	@FXML
	void createAdvice(ActionEvent event) {
		Stage stage = (Stage) Logout.getScene().getWindow();
		stage.close();
		ViewLogic.newCreateAdviseScreen();
	}
	@FXML
	private void generateTransactionReport() throws JarException, ClassNotFoundException, SQLException, JRException {
		JFrame report = new JFrame();
		report = TransactionLogic.getInstance().createReport();
		report.setVisible(true);
	}
	@FXML
	public void generateUserReport(ActionEvent event) {
		ViewLogic.UserReport();
	}
	@FXML
	private void jasperUser() throws JarException, ClassNotFoundException, SQLException, JRException {
		JFrame report = new JFrame();
		report = TransactionLogic.getInstance().createUsersReport();
		report.setVisible(true);
	}

	@FXML
	void Logout(ActionEvent event) {
		Stage stage = (Stage) Logout.getScene().getWindow();
		stage.close();
		ViewLogic.newLogin();

	}
}
