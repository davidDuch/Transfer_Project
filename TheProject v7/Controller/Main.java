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
	
System.out.println(UserLogic.getAllUsersConfirm(new User("Y0005I", "P1C")));
	//launch(args);
	
	
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
