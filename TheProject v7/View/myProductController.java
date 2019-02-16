package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Controller.Sys;
import Controller.UserLogic;
import Controller.WorkerLogic;
import Model.Category;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class myProductController implements Initializable{

    @FXML
    public TableView<Product> productTbl;

    @FXML
    private TableColumn<Product, String> nameC;

    @FXML
    private TableColumn<Product, Double> priceC;

    @FXML
    private TableColumn<Product, Integer> quantityC;

    @FXML
    private TableColumn<Product, Category> categoryC;

    @FXML
    private TableColumn<Product, String> descriptionC;

    @FXML
    private TableColumn<Product, String> pictureC;

    @FXML
    private JFXButton addProductBtn;

    @FXML
    private JFXButton updateQBtn;

    @FXML
    private JFXButton deleteProductBtn;

    @FXML
    private JFXButton backBtn;
    
    public static myProductController instance;
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		productTbl.getItems().clear();
		nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityC.setCellValueFactory(new PropertyValueFactory<>("AmountAvailable"));
		categoryC.setCellValueFactory(new PropertyValueFactory<>("category"));
		descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
		pictureC.setCellValueFactory(new PropertyValueFactory<>("image"));
		ArrayList<Product> al = UserLogic.getProducts();
		for(Product p : al) {
			if(p.getSellerAddress().equals(Sys.currentUser.getPublicAddress()))
				productTbl.getItems().add(p);
		}
		instance = this;
	}
    
    @FXML
    public void addProduct(ActionEvent event) {
    	ViewLogic.addProduct();
    }
    @FXML
    public void updateQuantity(ActionEvent event) {
    	if(productTbl.getSelectionModel().getSelectedItem() == null)
    		return;
    	Sys.myProduct = productTbl.getSelectionModel().getSelectedItem();
    	ViewLogic.updateQuantity();
    }
	@FXML
	public void back(ActionEvent event) {
		Stage stage = (Stage) productTbl.getScene().getWindow();
		stage.close();
		ViewLogic.newDashBoard();
	}
}
