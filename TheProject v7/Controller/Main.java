package Controller;

import Model.Category;
import Model.User;
import View.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
	Sys sys = new Sys();
	
	
	System.out.println(UserLogic.getProducts(new Category("2", "Sports")));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
