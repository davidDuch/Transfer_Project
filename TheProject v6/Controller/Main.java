package Controller;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.soap.SOAPBinding.Use;

import Model.Advice;
import Model.Confirm;
import Model.Pay;
import Model.User;
import Model.Wallet;
import Utils.Status;
import View.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
	
		User user =new User("E5848D", "XT7");
		
		ArrayList<Wallet> wallets = UserLogic.getUserWallets(user);
		
		System.out.println(wallets);
	

		
//		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
