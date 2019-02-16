package Controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.jar.JarException;

import Model.Advice;
import Model.Category;
import Model.Confirm;
import Model.Pay;
import Model.User;
import Model.Wallet;
import Utils.Status;
import View.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

public class Main extends Application {
	
	
	public static void main(String[] args) throws JarException, ClassNotFoundException, SQLException, JRException, UnsupportedEncodingException {
		Sys.system = new Sys();
		Sys.SendTransactions(TransactionLogic.getAllWaitingTrans());
		launch(args);
//		TransactionLogic.getInstance().UserReport();		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
