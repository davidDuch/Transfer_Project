package View;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Controller.Sys;
import Controller.UserLogic;
import Controller.WorkerLogic;
import Model.Category;
import Model.Product;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
public class addProductController implements Initializable{

    @FXML
    private JFXTextField autoAddress;

    @FXML
    private JFXTextField nameF;

    @FXML
    private JFXTextField priceF;

    @FXML
    private JFXTextField quantityF;

    @FXML
    private JFXComboBox<Category> catCombo;

    @FXML
    private JFXTextField imageF;

    @FXML
    private JFXTextArea descF;

    @FXML
    private Label errorLabel;
    
    @FXML
    private JFXButton addProduct;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		catCombo.getItems().clear();
		int i = 0;
		ArrayList<Product> al = UserLogic.getProducts();
		for(Product p : al) {
			if(Integer.parseInt(p.getId()) > i)
				i = Integer.parseInt(p.getId());
		}
		i = i + 1;
		autoAddress.setText(Integer.toString(i));
		catCombo.getItems().addAll(WorkerLogic.getCategories());
	}
    @FXML
    public boolean addProduct(ActionEvent event) {
    	if(nameF.getText() == null) {
    		errorLabel.setText("Input Name");
    		return false;
    	}
    	if(priceF.getText() == null) {
    		errorLabel.setText("Input Price");
    		return false;
    	}
    	if(quantityF.getText() == null) {
    		errorLabel.setText("Input Quantity");
    		return false;
    	}
    	if(catCombo.getSelectionModel().getSelectedItem() == null) {
    		errorLabel.setText("Input Category");
    		return false;
    	}
    	if(imageF.getText() == null) {
    		errorLabel.setText("Input Image");
    		return false;
    	}
    	if(descF.getText() == null) {
    		errorLabel.setText("Input Description");
    		return false;
    	}
    	try {
        	double price = Double.parseDouble(priceF.getText());
    	}catch (NumberFormatException e) {
    		errorLabel.setText("Only numbers allowed in price field");
    		return false;
    	}
    	try {
        	int quantity = Integer.parseInt(quantityF.getText());
    	}catch (NumberFormatException e) {
    		errorLabel.setText("Only numbers allowed in quantity field");
    		return false;
    	}
    	double price = Double.parseDouble(priceF.getText());
    	int quantity = Integer.parseInt(quantityF.getText());
    	String name = nameF.getText();
    	Category c = catCombo.getSelectionModel().getSelectedItem();
    	String desc = descF.getText();
    	String image = imageF.getText();
    	int num  = Integer.parseInt(autoAddress.getText());
    	User u = Sys.currentUser;
    	UserLogic.addProduct(num, name, desc, image, price, Integer.parseInt(c.getId()), quantity, u.getPublicAddress(), u.getDigitalSignature());
    	errorLabel.setText("Product is up for sale!");
    	initialize(null, null);
    	myProductController.instance.initialize(null, null);
    	nameF.setText("");
    	descF.setText("");
    	imageF.setText("");
    	priceF.setText("");
    	quantityF.setText("");
    	catCombo.getSelectionModel().clearSelection();
    	return true;
    }
}
