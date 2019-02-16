package Controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarException;

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
		int MINUTES = 1; // The delay in minutes
		Timer timer = new Timer();
		 timer.schedule(new TimerTask() {
		    @Override
		    public void run() { // Function runs every MINUTES minutes.
	    		System.out.println("FUCK THE POLICE!");
		    	try {
					Sys.SendTransactions(TransactionLogic.getAllWaitingTrans());
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Sys.RecieveTransactions();
		    }
		 }, 0, 1000 * 60 * MINUTES);
		    // 1000 milliseconds in a second * 60 per minute * the MINUTES variable. 


	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
		
	}

}
