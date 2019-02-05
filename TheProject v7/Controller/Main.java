package Controller;

import Model.User;
import View.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
	Sys sys = new Sys();
	
	
	UserLogic.addNewUser("XX", "CCC", "XXX", "XXX", "XXX", "XXXX");
//	sys.SendTransactions(TransactionLogic.getAllWaitingTrans());
		//launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
