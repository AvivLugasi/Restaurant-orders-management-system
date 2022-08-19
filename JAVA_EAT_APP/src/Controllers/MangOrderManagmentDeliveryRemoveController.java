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

public class MangOrderManagmentDeliveryRemoveController implements Initializable{

    @FXML
    private ComboBox<Integer> delsIDs;

    @FXML
    private Button delsRemoveBtn;
    
    private  ObservableList<Integer> IDs;

    @FXML
    void removeDel(ActionEvent event) {

    	if( delsIDs.getValue()!= null && MainController.getRest().removeDelivery(MainController.getRest().getRealDelivery(delsIDs.getValue()))) {
    		JOptionPane.showMessageDialog(null, "Delivery " + delsIDs.getValue() + " was deleted succesfully");
    		IDs.remove(delsIDs.getValue());
    		MainController.getRest().serialize();
    		if(!MainController.getRest().getDeliveries().values().isEmpty()) {
    			delsIDs.setItems(IDs);
    			delsIDs.setValue(IDs.get(0));
    			
    		}
    		else {
    			IDs.clear();
    			delsIDs.setValue(null);
    			
    		}
    		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Delivery remove Failed",
				      "SYSTEM ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		IDs = FXCollections.observableArrayList();
		IDs.addAll(MainController.getRest().getDeliveries().keySet());
		delsIDs.setItems(IDs);
		if(!IDs.isEmpty()) {
			delsIDs.setValue(IDs.get(0));
		}
	}

}
