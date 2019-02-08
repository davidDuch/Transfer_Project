package Controller;

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
	
	
	public static void main(String[] args) throws JarException, ClassNotFoundException, SQLException, JRException {
		Sys.system = new Sys();
		
		ArrayList<Pay> pays = TransactionLogic.getPayTransactions();
		ArrayList<Confirm> confirms = TransactionLogic.getConfirmTransactions();
		
		ArrayList<Pay> pay2 = (ArrayList<Pay>) pays.clone();
		ArrayList<Confirm> confirms2 = (ArrayList<Confirm>) confirms.clone();

		
		
		for(Pay temp : pay2)
			if(!temp.getStatus().equals(Status.executed))
				pays.remove(temp);
		
		for(Confirm temp : confirms2)
			if(!temp.getStatus().equals(Status.executed))
				confirms.remove(temp);
		
		
		Pay first = pays.get(0);
		Confirm second = confirms.get(0);
		
		System.out.println(first);
		System.out.println(second);
		
		TransactionLogic.TransferFunds(first, second);
		
	//	launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
