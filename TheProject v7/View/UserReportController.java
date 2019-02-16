package View;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;

import Controller.UserLogic;
import Model.UserReport;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserReportController implements Initializable{


    @FXML
    private Label dateLabel;

    @FXML
    private TableView<UserReport> usersTable;

    @FXML
    private TableColumn<UserReport, String> user;

    @FXML
    private TableColumn<UserReport, Integer> trans;

    @FXML
    private TableColumn<UserReport, Double> avg;

    @FXML
    private TableColumn<UserReport, Double> per;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<UserReport> al = UserLogic.getUserReport();
		al.sort(new Comparator<UserReport>() {

			@Override
			public int compare(UserReport o1, UserReport o2) {
				if(o1.getPercentage() < o2.getPercentage())
				return 1;
				if(o2.getPercentage() < o1.getPercentage())
				return -1;
					return 0;
			}
		});
		for(UserReport u : al) {
			System.out.println(u);
		}
		user.setCellValueFactory(new PropertyValueFactory<>("address"));
		trans.setCellValueFactory(new PropertyValueFactory<>("transactionNum"));
		avg.setCellValueFactory(new PropertyValueFactory<>("avg"));
		per.setCellValueFactory(new PropertyValueFactory<>("percentage"));
		usersTable.getItems().addAll(al);
		Date date = new Date();
		dateLabel.setText(date.toString());
	}
    
    
}
