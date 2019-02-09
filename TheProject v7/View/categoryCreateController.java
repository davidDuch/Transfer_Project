package View;

import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Controller.WorkerLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class categoryCreateController {
    @FXML
    private JFXTextField nameT;

    @FXML
    private JFXTextArea descT;

    @FXML
    private StackPane createStack;

    @FXML
    private JFXButton createBtn;
 
    @FXML
    public boolean createCategory(ActionEvent event) {
    //	try {
    		if(nameT.getText()!=null && descT.getText()!=null) {
    		WorkerLogic.addCategory(nameT.getText(), descT.getText());
    		System.out.println("added category");
    		}
  //  	}catch (SQLException e) {
//    	    JFXDialogLayout content = new JFXDialogLayout();
//    	    content.setHeading(new Text("Error Creating Category"));
//    	    JFXDialog dialog = new JFXDialog(createStack, content, JFXDialog.DialogTransition.CENTER);
//    	    JFXButton close = new JFXButton("Close");
//    	    close.setOnAction(new EventHandler<ActionEvent>() {
//
//    			@Override
//    			public void handle(ActionEvent event) {
//    				dialog.close();
//    	    content.setActions(close);
//    	    }
//    	    });
//    	    dialog.show();
//    		return false;
//    	}
//    	
    return true;
    }
}
