package View;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Controller.Sys;
import Controller.TransactionLogic;
import Model.Confirm;
import Model.Pay;
import Model.Transaction;
import Model.Wallet;
import Utils.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManageTransactionsController implements Initializable{

	 @FXML
	    private JFXButton acceptTransaction;

	    @FXML
	    private JFXButton cancelTransaction;

	    @FXML
	    private JFXButton backBtn;

	    @FXML
	    private TableView<Pay> payIncomeT;

	    @FXML
	    private TableColumn<Pay,String> id1;

	    @FXML
	    private TableColumn<Pay,String> desc1;

	    @FXML
	    private TableColumn<Pay,Double> size1;

	    @FXML
	    private TableColumn<Pay,java.sql.Date> cDate1;

	    @FXML
	    private TableColumn<Pay,java.sql.Date> aDate1;

	    @FXML
	    private TableColumn<Pay, Status> status1;

	    @FXML
	    private TableColumn<Pay, Double> commission1;

	    @FXML
	    private TableColumn<Pay, Double> bitcoin1;

	    @FXML
	    private TableColumn<Pay, String> ba1;

	    @FXML
	    private TableColumn<Pay, String> bs1;

	    @FXML
	    private TableView<Pay> myPayT;

	    @FXML
	    private TableColumn<Pay, String> id2;

	    @FXML
	    private TableColumn<Pay, String> desc2;

	    @FXML
	    private TableColumn<Pay, Double> size2;

	    @FXML
	    private TableColumn<Pay, java.sql.Date> cDate2;

	    @FXML
	    private TableColumn<Pay, java.sql.Date> aDate2;

	    @FXML
	    private TableColumn<Pay, Status> status2;

	    @FXML
	    private TableColumn<Pay, Double> commission2;

	    @FXML
	    private TableColumn<Pay, Double> bitcoin2;

	    @FXML
	    private TableColumn<Pay, String> sa2;

	    @FXML
	    private TableColumn<Pay, String> ss2;

	    @FXML
	    private TableColumn<Pay, Wallet> w2;

	    @FXML
	    private TableView<Confirm> outgoingConfimT;

	    @FXML
	    private TableColumn<Confirm, String> id3;

	    @FXML
	    private TableColumn<Confirm, String> desc3;

	    @FXML
	    private TableColumn<Confirm, Double> size3;

	    @FXML
	    private TableColumn<Confirm, java.sql.Date> cDate3;

	    @FXML
	    private TableColumn<Confirm, java.sql.Date> aDate3;

	    @FXML
	    private TableColumn<Confirm, Status> status3;

	    @FXML
	    private TableColumn<Confirm, Double> commision3;

	    @FXML
	    private TableColumn<Confirm, Boolean> app3;

	    @FXML
	    private TableColumn<Confirm, String> ba3;

	    @FXML
	    private TableColumn<Confirm, String> bs3;

	    @FXML
	    private TableColumn<Confirm, Wallet> w3;

	    @FXML
	    private TableColumn<Confirm, java.sql.Date> sd3;
	    
	    @FXML
	    private TableView<Confirm> incomingConfirmT;

	    @FXML
	    private TableColumn<Confirm, String> id4;

	    @FXML
	    private TableColumn<Confirm, String> desc4;

	    @FXML
	    private TableColumn<Confirm, Double> size4;

	    @FXML
	    private TableColumn<Confirm, java.sql.Date> cDate4;

	    @FXML
	    private TableColumn<Confirm, java.sql.Date> aDate4;

	    @FXML
	    private TableColumn<Confirm, Status> status4;

	    @FXML
	    private TableColumn<Confirm, Double> commssion4;

	    @FXML
	    private TableColumn<Confirm, Boolean> app4;

	    @FXML
	    private TableColumn<Confirm, String> sa4;

	    @FXML
	    private TableColumn<Confirm, String> ss4;

	    @FXML
	    private TableColumn<Confirm, java.sql.Date> sd4;
	    @FXML
	    private TableColumn<Confirm, Wallet> w4;
	    
	    public static ManageTransactionsController instance;
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			payIncomeT.getItems().clear();
			myPayT.getItems().clear();
			outgoingConfimT.getItems().clear();
			incomingConfirmT.getItems().clear();
			ArrayList<Pay> pay = TransactionLogic.getPayTransactions();
			ArrayList<Confirm> confirm = TransactionLogic.getConfirmTransactions();
//			TableView> payIncomeT
			if(pay!=null) {
		      id1.setCellValueFactory(new PropertyValueFactory<>("id"));
		      desc1.setCellValueFactory(new PropertyValueFactory<>("description"));
		      size1.setCellValueFactory(new PropertyValueFactory<>("size"));	    
		      cDate1.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
		      aDate1.setCellValueFactory(new PropertyValueFactory<>("dateApproved"));
		      status1.setCellValueFactory(new PropertyValueFactory<>("status"));
		      commission1.setCellValueFactory(new PropertyValueFactory<>("commission"));
		      bitcoin1.setCellValueFactory(new PropertyValueFactory<>("btcAmount"));
		      ba1.setCellValueFactory(new PropertyValueFactory<>("creatorAddress"));
		      bs1.setCellValueFactory(new PropertyValueFactory<>("creatorSignature"));
		      for(Pay p : pay) {
		    	  System.out.println(p);
		    	  if(p.getSellerAddress().equals(Sys.currentUser.getPublicAddress()))
		    		  payIncomeT.getItems().add(p);
		      }
//		     TableView> myPayT
		       id2.setCellValueFactory(new PropertyValueFactory<>("id"));
		       desc2.setCellValueFactory(new PropertyValueFactory<>("description"));
		       size2.setCellValueFactory(new PropertyValueFactory<>("size"));
		       cDate2.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
			   aDate2.setCellValueFactory(new PropertyValueFactory<>("dateApproved"));
		       status2.setCellValueFactory(new PropertyValueFactory<>("status"));
		       commission2.setCellValueFactory(new PropertyValueFactory<>("commission"));
		       bitcoin2.setCellValueFactory(new PropertyValueFactory<>("btcAmount"));
		       sa2.setCellValueFactory(new PropertyValueFactory<>("sellerAddress"));
		       ss2.setCellValueFactory(new PropertyValueFactory<>("sellerSignature"));
		       w2.setCellValueFactory(new PropertyValueFactory<>("wallet"));
			      for(Pay p : pay) {
			    	  System.out.println(p);
			    	  if(p.getCreatorAddress().equals(Sys.currentUser.getPublicAddress()))
			    		  myPayT.getItems().add(p);
			      }
		}
//		     TableView> outgoingConfimT	
			    if(confirm!=null) {
		       id3.setCellValueFactory(new PropertyValueFactory<>("id"));
		       desc3.setCellValueFactory(new PropertyValueFactory<>("description"));
		       size3.setCellValueFactory(new PropertyValueFactory<>("size"));
		       cDate3.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
		       aDate3.setCellValueFactory(new PropertyValueFactory<>("dateApproved"));
		       status3.setCellValueFactory(new PropertyValueFactory<>("status"));
		       app3.setCellValueFactory(new PropertyValueFactory<>("approved"));
		       commision3.setCellValueFactory(new PropertyValueFactory<>("commission"));
		       ba3.setCellValueFactory(new PropertyValueFactory<>("buyerAddress"));
		       bs3.setCellValueFactory(new PropertyValueFactory<>("buyerSignature"));
		       w3.setCellValueFactory(new PropertyValueFactory<>("wallet"));
		       sd3.setCellValueFactory(new PropertyValueFactory<>("dateOfSupply"));
			      for(Confirm m : confirm) {
			    	  System.out.println(m);
			    	  if(m.getCreatorAddress().equals(Sys.currentUser.getPublicAddress()))
			    		  outgoingConfimT.getItems().add(m);
			      }
//		     TableView> incomingConfirmT	    
		       id4.setCellValueFactory(new PropertyValueFactory<>("id"));
		       desc4.setCellValueFactory(new PropertyValueFactory<>("description"));
		       size4.setCellValueFactory(new PropertyValueFactory<>("size"));
		       cDate4.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
		       aDate4.setCellValueFactory(new PropertyValueFactory<>("dateApproved"));
		       status4.setCellValueFactory(new PropertyValueFactory<>("status"));
		       app4.setCellValueFactory(new PropertyValueFactory<>("approved"));
		       commssion4.setCellValueFactory(new PropertyValueFactory<>("name"));
		       sa4.setCellValueFactory(new PropertyValueFactory<>("creatorAddress"));
		       ss4.setCellValueFactory(new PropertyValueFactory<>("creatorSignature"));
		       sd4.setCellValueFactory(new PropertyValueFactory<>("dateOfSupply"));
		       w4.setCellValueFactory(new PropertyValueFactory<>("wallet"));
			      for(Confirm m : confirm) {
			    	  System.out.println(m);
			    	  if(m.getBuyerAddress().equals(Sys.currentUser.getPublicAddress()))
			    		  incomingConfirmT.getItems().add(m);
			      }
			    }
			    instance = this;
		}
		
		@FXML
		public void confirmTransaction(ActionEvent event) {
			if(payIncomeT.getSelectionModel().getSelectedItem()==null)
				return;
			Sys.currentPay = payIncomeT.getSelectionModel().getSelectedItem();
			ViewLogic.confirm();
		}
		@FXML
		public void rejectTransaction(ActionEvent event) {
			if(payIncomeT.getSelectionModel().getSelectedItem()!=null) {
				Pay p = payIncomeT.getSelectionModel().getSelectedItem();
				if(p.getStatus()==(Status.waiting)) {
				TransactionLogic.updateTransactionStatus(p, Status.irelevant);
				payIncomeT.refresh();
			}
			}
			if(outgoingConfimT.getSelectionModel().getSelectedItem()!=null) {
				Confirm c = outgoingConfimT.getSelectionModel().getSelectedItem();
				System.out.println("a");
				if(c.getStatus()==(Status.waiting)) {
				TransactionLogic.updateTransactionStatus(c, Status.irelevant);
				System.out.println("b");
				outgoingConfimT.refresh();
				}
			}
			initialize(null, null);
		}
		@FXML
		public void back(ActionEvent event) {
			Stage stage = (Stage) backBtn.getScene().getWindow();
			stage.close();
			ViewLogic.newDashBoard();
		}
}
