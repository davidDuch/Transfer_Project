package View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Model.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class workerManagmentController {

	    @FXML
	    private JFXComboBox<String> usersCombo;

	    @FXML
	    private JFXButton addBtn;

	    @FXML
	    private JFXButton removeBtn;

	    @FXML
	    private JFXButton backBtn;

	    @FXML
	    private TableView<Worker> usersTbl;

	    @FXML
	    private TableColumn<Worker, String> publicC;

	    @FXML
	    private TableColumn<Worker, String> dsC;

	    @FXML
	    private TableColumn<Worker, String> idC;

	    @FXML
	    private TableColumn<Worker, String> passC;

	}
