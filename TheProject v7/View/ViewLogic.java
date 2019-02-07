package View;

import java.io.IOException;
import java.net.URL;

import com.guigarage.flatterfx.FlatterFX;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewLogic {
	
	
	
	protected static GenerateReportController GenerateReportController ;

	public static void initUI() {
		newLogin();

	}

	/**
	 * Goes to the report window
	 */
	protected static void newReportGenerator() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("GenerateReportScreen.fxml"), stage, false, "Report Window", false);
	}

	protected static void newLogin() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("SignUpLogin.fxml"), stage, false, "Welcome !", false);

	}

	/**
	 * Goes to the dash board
	 */
	
	protected static void newDashBoard() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("newDash.fxml"), stage, false, "Dash Board", false);

	}

	/**
	 * Go to window Create Advice
	 */
	protected static void newCreateAdviseScreen() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("CreateAdviceScreen.fxml"), stage, false, "Create Advise", false);

	}
	protected static void walletMenuScreen() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("walletMenu.fxml"), stage, false, "My Wallets", false);

	}

	/**
	 * this method manages the window properties
	 * 
	 * @param fxmlLocation
	 * @param stage
	 * @param prefWidth
	 * @param prefHeight
	 * @param minWidth
	 * @param minHeight
	 * @param maxWidth
	 * @param maxHeight
	 * @param resizable
	 * @param title
	 * @param waitFor
	 */
	protected static void newWindow(URL fxmlLocation, Stage stage, boolean resizable, String title, boolean waitFor) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					FXMLLoader loader = new FXMLLoader(fxmlLocation);
					Parent root = loader.load();
					Scene scene;
					scene = new Scene(root);
					stage.setScene(scene);
					stage.setResizable(resizable);
					
					if (title != null && !title.isEmpty() && !title.trim().isEmpty())
						stage.setTitle(title);

					if (waitFor)
						stage.initModality(Modality.APPLICATION_MODAL);

					stage.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
