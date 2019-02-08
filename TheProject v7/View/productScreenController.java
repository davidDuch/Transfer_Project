package View;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Controller.Sys;
import Controller.UserLogic;
import Model.Category;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
public class productScreenController implements Initializable{

    @FXML
    private JFXTextField searchBy;

    @FXML
    private JFXComboBox<Category> catCombo;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityC.setCellValueFactory(new PropertyValueFactory<>("AmountAvailable"));
		categoryC.setCellValueFactory(new PropertyValueFactory<>("category"));
		descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
		pictureC.setCellValueFactory(new PropertyValueFactory<>("image"));
		productTbl.setItems(FXCollections.observableArrayList(UserLogic.getProducts()));
	}

    
}
