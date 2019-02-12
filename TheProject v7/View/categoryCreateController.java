package View;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Controller.WorkerLogic;
import Model.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class categoryCreateController implements Initializable{
    @FXML
    private JFXTextField nameT;

    @FXML
    private JFXTextField catNum;


    @FXML
    private StackPane createStack;

    @FXML
    private JFXButton createBtn;
 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int maxCatNum = 0;
		ArrayList<Category> al = WorkerLogic.getCategories();
		for(Category c : al) {
			if(Integer.parseInt(c.getId()) > maxCatNum) {
				maxCatNum = Integer.parseInt(c.getId());
			}
		}
		maxCatNum = maxCatNum +1;
		catNum.setText(Integer.toString(maxCatNum));
	}

    @FXML
    public boolean createCategory(ActionEvent event) {
    		if(nameT.getText()!=null) {
    		WorkerLogic.addCategory(Integer.parseInt(catNum.getText()),nameT.getText());
    		System.out.println("added category");
    		}
    	    JFXDialogLayout content = new JFXDialogLayout();
    	    content.setHeading(new Text("Category Created"));
    	    JFXDialog dialog = new JFXDialog(createStack, content, JFXDialog.DialogTransition.CENTER);
    	    JFXButton close = new JFXButton("Close");
    	    close.setOnAction(new EventHandler<ActionEvent>() {

    			@Override
    			public void handle(ActionEvent event) {
    				dialog.close();
    	    content.setActions(close);
    	    }
    	    });
    	    dialog.show();
    	    nameT.setText("");
    	    initialize(null, null);
    		return true;
    	}
    	
}
