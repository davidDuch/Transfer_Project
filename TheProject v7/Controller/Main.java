package Controller;

import java.util.ArrayList;

import Model.Category;
import Model.User;
import View.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
	Sys.system = new Sys();
	
	WorkerLogic.setDefaultWalletSize(20);
	WorkerLogic.setDiscountExpandPrice(0);
	WorkerLogic.setExpandDiscountSize(0);
	WorkerLogic.setExpandPrice(20);
	WorkerLogic.setExpandWalletSize(0);
	WorkerLogic.setMaxPossibleExpansionSize(0);
	//launch(args);
	
	
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
