package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.Customer;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MangOpsCustomerAddToBlackListController implements Initializable{

    @FXML
    private ComboBox<Integer> customerIDs;

    @FXML
    private Button addToBlackListBtn;
    
    private  ObservableList<Integer> IDs;

    @FXML
    void addToBlackList(ActionEvent event) {
    	
    	Integer id = customerIDs.getValue();
    	if(id != null) {
    		MainController.getRest().addCustomerToBlackList(MainController.getRest().getRealCustomer(id));
    		JOptionPane.showMessageDialog(null, "Customer " + customerIDs.getValue() + " was added succesfully to the blacklist");
    		IDs.remove(id);
    		customerIDs.setItems(IDs);
    		if(!IDs.isEmpty()) {
    			customerIDs.setValue(IDs.get(0));
    		}
    		MainController.restSerialize();
    	}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		IDs = FXCollections.observableArrayList();
		for(Customer cust: MainController.getRest().getCustomers().values()) {
			if(!MainController.getRest().getBlackList().contains(cust)) {
				IDs.add(cust.getId());
			}
		}
		customerIDs.setItems(IDs);
		if(!IDs.isEmpty()) {
			customerIDs.setValue(IDs.get(0));
		}
		
	}
    
    

}

