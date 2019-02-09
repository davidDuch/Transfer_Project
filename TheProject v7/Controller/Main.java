package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.jar.JarException;

import Model.Advice;
import Model.Category;
import Model.Confirm;
import Model.User;
import Model.Wallet;
import View.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

public class Main extends Application {
	
	
	public static void main(String[] args) throws JarException, ClassNotFoundException, SQLException, JRException {
		Sys.system = new Sys();
		
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
