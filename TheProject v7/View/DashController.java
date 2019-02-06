package View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import Controller.Sys;
import Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class DashController {

    @FXML
    private JFXButton pBtn;

    @FXML
    private JFXButton myPBtn;

    @FXML
    private JFXButton transBtn;

    @FXML
    private JFXButton walletBtn;

    @FXML
    private JFXButton Logout;

    @FXML
    private TableView<?> adviceTbl;

    @FXML
    private TableColumn<?, ?> adviceIdCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> commisionCol;

    @FXML
    private TableColumn<?, ?> prefCol;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXButton dialog;
    @FXML
    public void getDetails(ActionEvent event) {
    JFXDialogLayout content = new JFXDialogLayout();
    content.setHeading(new Text("Your Details are: "));
    User current = Sys.currentUser;
    content.setBody(new Text("Public Address: " + current.getPublicAddress() + "\n" + "Digital Signature: " + current.getDigitalSignature()));
    JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
    JFXButton close = new JFXButton("Close");
    close.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			dialog.close();
    content.setActions(close);
    }
    });
    dialog.show();
    }
}
