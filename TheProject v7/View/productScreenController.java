package View;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

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
public class productScreenController implements Initializable{

    @FXML
    private JFXTextField searchBy;

    @FXML
    private JFXComboBox<Category> catCombo;
    
    @FXML
    private JFXTextField min;

    @FXML
    private JFXTextField max;
    
    @FXML
    private JFXButton searchBtn;

    @FXML
    private JFXButton buyProductBtn;

    @FXML
    private JFXButton backBtn;
    
    @FXML
    private TableView<Product> productTbl;

    @FXML
    private TableColumn<Product, String> nameC;

    @FXML
    private TableColumn<Product, Double> priceC;

    @FXML
    private TableColumn<Product, Integer> quantityC;

    @FXML
    private TableColumn<Product, String> categoryC;

    @FXML
    private TableColumn<Product, String> descriptionC;

    @FXML
    private TableColumn<Product, String> pictureC;

    public static productScreenController instance;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		productTbl.getItems().clear();
		catCombo.getItems().clear();
		nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityC.setCellValueFactory(new PropertyValueFactory<>("AmountAvailable"));
		categoryC.setCellValueFactory(new PropertyValueFactory<>("category"));
		descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
		pictureC.setCellValueFactory(new PropertyValueFactory<>("image"));
		ArrayList<Product> al = UserLogic.getProducts();
		for(Product p : al) {
			if(!p.getSellerAddress().equals(Sys.currentUser.getPublicAddress()) && p.getAmountAvailable() > 0)
				productTbl.getItems().add(p);
			//(FXCollections.observableArrayList(UserLogic.getProducts()));
		}
		catCombo.getItems().addAll(WorkerLogic.getCategories());
		instance = this;
	}
	
	@FXML
    public void SearchProduct(ActionEvent event) {
	productTbl.getItems().clear();
	ArrayList<Product> products = new ArrayList<Product>();
		products =UserLogic.getProducts();
		ArrayList<Product> remove = new ArrayList<Product>();
	for(Product p : products) {
		if(p.getSellerAddress().equals(Sys.currentUser.getPublicAddress()) || p.getAmountAvailable() < 1)
			remove.add(p);
	}
	products.removeAll(remove);
	remove.clear();
	if(searchBy.getText()!=null) {
		String s = searchBy.getText();
		for(Product p : products) {
			if(!p.getName().contains(s)){
				remove.add(p);
			}
		}
	}
	System.out.println(products);
	System.out.println(remove);
	products.removeAll(remove);
	if(catCombo.getSelectionModel().getSelectedItem()!=null) {
		Category c = catCombo.getSelectionModel().getSelectedItem();
		for(Product p : products) {
			if(!p.getCategory().equals(c)) {
				remove.add(p);
			}
		}
	}
	System.out.println(products);
	System.out.println(remove);
	products.removeAll(remove);
		double maxPrice = 0;
		double minPrice = 0;
	if(max.getText()!=null || min.getText()!=null) {
		if(min.getText()!=null) {
	    	try {
	    		minPrice = Double.parseDouble(min.getText());
	    	}catch (NumberFormatException e) {
	    		minPrice = 0;
	    	}
	}
		if(max.getText()!=null) {
	    	try {
	    		maxPrice = Double.parseDouble(max.getText());
	    	}catch (NumberFormatException e) {
	    		maxPrice = Double.MAX_VALUE;
		}
	    	
	}
	}
	for(Product p : products) {
		if(maxPrice < minPrice) {
			double m = maxPrice;
			maxPrice = minPrice;
			minPrice = m;
			max.setText(Double.toString(maxPrice));
			min.setText(Double.toString(minPrice));
		}
		if(maxPrice > minPrice && minPrice >= 0) {
			if(p.getPrice() < minPrice || p.getPrice()> maxPrice)
			remove.add(p);
	}
	}
	System.out.println(products);
	System.out.println(remove);
	products.removeAll(remove);
	productTbl.setItems(FXCollections.observableArrayList(products));
	productTbl.refresh();
}
	
	@FXML 
	public void buyProduct(ActionEvent event) {
		if(productTbl.getSelectionModel().getSelectedItem()==null) {
			return;
		}
		Sys.chosenProduct = productTbl.getSelectionModel().getSelectedItem();
		System.out.println(Sys.chosenProduct);
		ViewLogic.buyProduct();
	}
	@FXML
	public void back(ActionEvent event) {
		Stage stage = (Stage) productTbl.getScene().getWindow();
		stage.close();
		ViewLogic.newDashBoard();
	}
}