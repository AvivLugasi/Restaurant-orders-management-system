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

public class MangOpsDishRemoveController implements Initializable {

    @FXML
    private ComboBox<Integer> dishIDs;

    @FXML
    private Button dishRemoveBtn;
    
    private  ObservableList<Integer> IDs;

    @FXML
    void removeDish(ActionEvent event) {
    	
    	if( dishIDs.getValue()!= null && MainController.getRest().removeDish(MainController.getRest().getRealDish(dishIDs.getValue()))) {
    		JOptionPane.showMessageDialog(null, "Dish " + dishIDs.getValue() + " was deleted succesfully");
    		IDs.remove(dishIDs.getValue());
    		MainController.getRest().serialize();
    		if(!MainController.getRest().getDishes().values().isEmpty()) {
    			dishIDs.setItems(IDs);
    			dishIDs.setValue(IDs.get(0));
    			
    		}
    		else {
    			IDs.clear();
    			dishIDs.setValue(null);
    			
    		}
    		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Remove dish Failed",
				      "SYSTEM ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		IDs = FXCollections.observableArrayList();
		IDs.addAll(MainController.getRest().getDishes().keySet());
		dishIDs.setItems(IDs);
		if(!IDs.isEmpty()) {
			dishIDs.setValue(IDs.get(0));
		}
			
	}


}
