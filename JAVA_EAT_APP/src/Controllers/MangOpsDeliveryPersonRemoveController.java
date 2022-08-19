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

public class MangOpsDeliveryPersonRemoveController implements Initializable{

    @FXML
    private ComboBox<Integer> delPIDs;

    @FXML
    private Button delPRemoveBtn;

    private  ObservableList<Integer> IDs;
    
    @FXML
    void removeDelP(ActionEvent event) {

    	if( delPIDs.getValue()!= null && MainController.getRest().removeDeliveryPerson(MainController.getRest().getRealDeliveryPerson(delPIDs.getValue()))) {
    		JOptionPane.showMessageDialog(null, "Delivery Person " + delPIDs.getValue() + " was deleted succesfully");
    		IDs.remove(delPIDs.getValue());
    		MainController.getRest().serialize();
    		if(!MainController.getRest().getCooks().values().isEmpty()) {
    			delPIDs.setItems(IDs);
    			delPIDs.setValue(IDs.get(0));
    			
    		}
    		else {
    			IDs.clear();
    			delPIDs.setValue(null);
    			
    		}
    		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Delivery Person Failed",
				      "SYSTEM ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		IDs = FXCollections.observableArrayList();
		IDs.addAll(MainController.getRest().getDeliveryPersons().keySet());
		delPIDs.setItems(IDs);
		if(!IDs.isEmpty()) {
			delPIDs.setValue(IDs.get(0));
		}
	}

}
