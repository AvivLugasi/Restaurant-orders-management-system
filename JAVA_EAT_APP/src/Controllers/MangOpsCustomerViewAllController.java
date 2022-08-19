package Controllers;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import Model.Customer;
import Utils.AssistingMethods;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MangOpsCustomerViewAllController {

    @FXML
    private TextArea viewResualt;

    @FXML
    private ListView<String> list;

    @FXML
    private Button viewAllBtn;

    @FXML
    private Button BlackListBtn;
    
    @FXML
    private ImageView choosenImg;
    
    private String currectpath;

    private ObservableList<String> customersList = FXCollections.observableArrayList();
    
    private AssistingMethods aM = new AssistingMethods();;
    
    @FXML
    void showDetails(MouseEvent event) {

    	String s = list.getSelectionModel().getSelectedItem();
    	if(s != null) {
    		String ID = "";
    		for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
			Customer cust = MainController.getRest().getRealCustomer(Integer.parseInt(ID));
			viewResualt.setText(cust.toString());
			if(cust.getCustPicName() != null) {
				aM.showImage(cust.getCustPicName(), currectpath, choosenImg);
			}
    	}
    }

    @FXML
    void viewAll(ActionEvent event) {
    	
    	list.getItems().removeAll(list.getItems());
    	Set<Integer> custsIDs = MainController.getRest().getCustomers().keySet();
		if(!custsIDs.isEmpty()) {
			settingtheListView(custsIDs);
		}

    }

    @FXML
    void viewBlackList(ActionEvent event) {

    	list.getItems().removeAll(list.getItems());
    	HashSet<Customer> custs = MainController.getRest().getBlackList();
    	HashSet<Integer> custsIDs = new HashSet<Integer>();
		if(custs != null) {
			for(Customer cust: custs) {
				custsIDs.add(cust.getId());
			}
			settingtheListView(custsIDs);
		}
    }
    
    private void settingtheListView(Set<Integer> custsIDs) {
    	
    	String s = "";
		String ID = "";
    	for(Integer id: custsIDs) {
			s += id + " " + MainController.getRest().getRealCustomer(id).getFirstName() + " " + MainController.getRest().getRealCustomer(id).getLastName();
			customersList.add(s);
			s = "";
		}
		list.setItems(customersList);
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		if(!customersList.isEmpty()) {
			list.getSelectionModel().select(customersList.get(0));
			s = customersList.get(0);
			
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
			Customer cust = MainController.getRest().getRealCustomer(Integer.parseInt(ID));
			viewResualt.setText(cust.toString());
		}
    }

}
