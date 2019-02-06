package Controller;

import java.util.ArrayList;

import Model.Category;
import Model.User;
import Model.Wallet;
import View.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	public static void main(String[] args) {
		Sys.system = new Sys();
	
	//	UserLogic.upgradeWalletToSpace(new Wallet("W14"));
		
		//launch(args);
	
	
	//UserLogic.addNewUser("XX", "CCC", "XXX", "XXX", "XXX", "XXXX");
//	sys.SendTransactions(TransactionLogic.getAllWaitingTrans());
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
