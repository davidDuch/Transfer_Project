package View;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Controller.Sys;
import Controller.UserLogic;
import Model.Wallet;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class WalletController implements Initializable
{

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton upgrade;

    @FXML
    private JFXButton buy;

    @FXML
    private JFXButton back;

    @FXML
    private TableView<Wallet> walletTable;
    
    @FXML
    private TableColumn<Wallet, String> addressC;

    @FXML
    private TableColumn<Wallet, String> typeC;

    @FXML
    private TableColumn<Wallet, Double> fundsC;

    @FXML
    private TableColumn<Wallet, Double> ffundsC;

    @FXML
    private TableColumn<Wallet, Boolean> pcC;

    @FXML
    private TableColumn<Wallet, Boolean> tabletC;

    @FXML
    private TableColumn<Wallet, Boolean> mobileC;

    @FXML
    private TableColumn<Wallet, Double> discountC;

    @FXML
    private TableColumn<Wallet, Double> spaceC;

	public void initialize(URL location, ResourceBundle resources) {
		addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
		typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
		fundsC.setCellValueFactory(new PropertyValueFactory<>("funds"));
		ffundsC.setCellValueFactory(new PropertyValueFactory<>("futureValue"));
		pcC.setCellValueFactory(new PropertyValueFactory<>("PC"));
		tabletC.setCellValueFactory(new PropertyValueFactory<>("Tablet"));
		mobileC.setCellValueFactory(new PropertyValueFactory<>("Phone"));
		discountC.setCellValueFactory(new PropertyValueFactory<>("discount"));
		spaceC.setCellValueFactory(new PropertyValueFactory<>("maxTransSize"));
		System.out.println("1" + UserLogic.getUserWallets(Sys.currentUser));
		walletTable.setItems(FXCollections.observableArrayList(UserLogic.getUserWallets(Sys.currentUser)));
	}
    

	@FXML
	void buyWallet(ActionEvent event) {
		ViewLogic.buyWallet();
	}


}
