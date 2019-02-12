package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Controller.Sys;
import Controller.UserLogic;
import Model.BitcoinKnots;
import Model.BitcoinSpace;
import Model.Wallet;
import Model.WalletType;
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
    private TableView<WalletType> walletTable;
    
    @FXML
    private TableColumn<WalletType, String> addressC;

    @FXML
    private TableColumn<WalletType, String> typeC;

    @FXML
    private TableColumn<WalletType, Double> fundsC;

    @FXML
    private TableColumn<WalletType, Double> ffundsC;

    @FXML
    private TableColumn<WalletType, Boolean> pcC;

    @FXML
    private TableColumn<WalletType, Boolean> tabletC;

    @FXML
    private TableColumn<WalletType, Boolean> mobileC;

    @FXML
    private TableColumn<WalletType, Double> discountC;

    @FXML
    private TableColumn<WalletType, Double> spaceC;

	public void initialize(URL location, ResourceBundle resources) {
		addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
		typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
		fundsC.setCellValueFactory(new PropertyValueFactory<>("funds"));
		ffundsC.setCellValueFactory(new PropertyValueFactory<>("futureValue"));
		pcC.setCellValueFactory(new PropertyValueFactory<>("PC"));
		tabletC.setCellValueFactory(new PropertyValueFactory<>("Tablet"));
		mobileC.setCellValueFactory(new PropertyValueFactory<>("Phone"));
		typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
		discountC.setCellValueFactory(new PropertyValueFactory<>("discount"));
		spaceC.setCellValueFactory(new PropertyValueFactory<>("maxTransSize"));
		System.out.println("1" + UserLogic.getUserWallets(Sys.currentUser));
		ArrayList<BitcoinKnots> knotsAL = UserLogic.getKnots();
		ArrayList<BitcoinSpace> spaceAL = UserLogic.getSpace();
		ArrayList<Wallet> wallets = UserLogic.getUserWallets(Sys.currentUser);
		ArrayList<WalletType> walletList = new ArrayList<WalletType>();
		HashMap<String, BitcoinKnots> hmK = new HashMap<String, BitcoinKnots>();
		for(BitcoinKnots bck : knotsAL) {
			hmK.put(bck.getAddress(), bck);
		}
		HashMap<String, BitcoinSpace> hmS = new HashMap<String, BitcoinSpace>();
		for(BitcoinSpace bks : spaceAL) {
			hmS.put(bks.getAddress(), bks);
		}
		for(Wallet w : wallets) {
			if(hmK.get(w.getAddress())!=null) {
				walletList.add(new WalletType(w.getAddress(), w.getFunds(),w.getFutureValue(), w.getPC(), w.getTablet(), w.getPhone(),"Knots",hmK.get(w.getAddress()).getDiscount(),Sys.defualtWalletSize));
			}
			if(hmS.get(w.getAddress())!=null) {
				walletList.add(new WalletType(w.getAddress(), w.getFunds(),w.getFutureValue(), w.getPC(), w.getTablet(), w.getPhone(),"Space", 0,hmS.get(w.getAddress()).getMaxTransSize()));

			}
			else {
				walletList.add(new WalletType(w.getAddress(), w.getFunds(), w.getFutureValue(), w.getPC(),w.getTablet(),w.getPhone(), "Default", 0, Sys.defualtWalletSize));
			}
		}
		walletTable.setItems(FXCollections.observableArrayList(walletList));
	}
    

	@FXML
	void buyWallet(ActionEvent event) {
		ViewLogic.buyWallet();
	}
    @FXML 
    void upgradeWallet(ActionEvent event){
    ViewLogic.upgradeWallet();
    }
	@FXML
	void addFunds(ActionEvent event){
		int first =(int)(Math.random() *15);
		double random = 0;
		if(first == 1) {
		random = Math.random() * 25;
		}
		else { 
		random = 0;
		}
		System.out.println(random);
		ArrayList<Wallet> wallets = UserLogic.getUserWallets(Sys.currentUser);
		if(wallets!=null) {
			for(Wallet w : wallets) {
				w.setFunds(w.getFunds() + random);
				UserLogic.updateWalletFunds(w);
			}
		}
		}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressC == null) ? 0 : addressC.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WalletController other = (WalletController) obj;
		if (addressC == null) {
			if (other.addressC != null)
				return false;
		} else if (!addressC.equals(other.addressC))
			return false;
		return true;
	}
	
}
