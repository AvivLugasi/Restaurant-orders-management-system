package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MangOpsCustomerRemoveController implements Initializable {

    @FXML
    private ComboBox<Integer> customerIDs;

    @FXML
    private Button customerRemoveBtn;
    
    private  ObservableList<Integer> IDs;

    @FXML
    void removeCustomer(ActionEvent event) {
    	
    	if( customerIDs.getValue()!= null && MainController.getRest().removeCustomer(MainController.getRest().getRealCustomer(customerIDs.getValue()))) {
    		JOptionPane.showMessageDialog(null, "Customer " + customerIDs.getValue() + " was deleted succesfully");
    		IDs.remove(customerIDs.getValue());
    		MainController.getRest().serialize();
    		if(!MainController.getRest().getCustomers().values().isEmpty()) {
    			customerIDs.setItems(IDs);
    			customerIDs.setValue(IDs.get(0));
    			
    		}
    		else {
    			IDs.clear();
    			customerIDs.setValue(null);
    			
    		}
    		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Remove customer Failed",
				      "SYSTEM ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		IDs = FXCollections.observableArrayList();
		IDs.addAll(MainController.getRest().getCustomers().keySet());
		customerIDs.setItems(IDs);
		if(!IDs.isEmpty()) {
			customerIDs.setValue(IDs.get(0));
		}
			
	}

}
