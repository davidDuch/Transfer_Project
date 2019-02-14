package View;

import java.io.IOException;
import java.net.URL;

import com.guigarage.flatterfx.FlatterFX;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewLogic {
	
	
	
	protected static GenerateReportController GenerateReportController ;

	public static void initUI() {
		newLogin();

	}

	/**
	 * Goes to the report window
	 */
	protected static void newReportGenerator() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("GenerateReportScreen.fxml"), stage, false, "Report Window", false);
	}

	protected static void newLogin() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("SignUpLogin.fxml"), stage, false, "Welcome !", false);

	}
	protected static void createCategory() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("CategoryCreateScreen.fxml"), stage, false, "Create Category", false);

	}

	/**
	 * Goes to the dash board
	 */
	
	protected static void newDashBoard() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("NewDash.fxml"), stage, false, "Dash Board", false);

	}
	protected static void workerMenu() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("WorkerMenu.fxml"), stage, false, "Worker Menu", false);

	}

	/**
	 * Go to window Create Advice
	 */
	protected static void newCreateAdviseScreen() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("CreateAdviceScreen.fxml"), stage, false, "Create Advise", false);

	}
	protected static void walletMenuScreen() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("walletMenu.fxml"), stage, false, "My Wallets", false);

	}
	protected static void buyProducts() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("ProductScreen.fxml"), stage, false, "Buy Products", false);
	}
	protected static void myProducts() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("MyProductsScreen.fxml"), stage, false, "My Products", false);

	}
	protected static void myTransactions() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("ManageTransactionsScreen.fxml"), stage, false, "My Transactions", false);

	}

	protected static void buyWallet() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("BuyWalletScreen.fxml"), stage, false, "Buy Wallets", false);

	}
	protected static void upgradeWallet() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("UpgradeWallet.fxml"), stage, false, "Upgrade Wallet", false);
	}

	protected static void buyProduct() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("BuyProductTransactionPay.fxml"), stage, false, "Buy Product", false);

	}
	protected static void addProduct() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("AddProductScreen.fxml"), stage, false, "Add Product", false);

	}
	protected static void updateQuantity() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("UpdateQuantityScreen.fxml"), stage, false, "Update Quantity", false);
	}
	protected static void confirm() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("ConfrimScreen.fxml"), stage, false, "Confirm Transaction", false);
	}


	/**
	 * this method manages the window properties
	 * 
	 * @param fxmlLocation
	 * @param stage
	 * @param prefWidth
	 * @param prefHeight
	 * @param minWidth
	 * @param minHeight
	 * @param maxWidth
	 * @param maxHeight
	 * @param resizable
	 * @param title
	 * @param waitFor
	 */
	protected static void newWindow(URL fxmlLocation, Stage stage, boolean resizable, String title, boolean waitFor) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					FXMLLoader loader = new FXMLLoader(fxmlLocation);
					Parent root = loader.load();
					Scene scene;
					scene = new Scene(root);
					stage.setScene(scene);
					stage.setResizable(resizable);
					
					if (title != null && !title.isEmpty() && !title.trim().isEmpty())
						stage.setTitle(title);

					if (waitFor)
						stage.initModality(Modality.APPLICATION_MODAL);

					stage.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
