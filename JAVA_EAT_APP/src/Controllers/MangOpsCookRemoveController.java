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


public class MangOpsCookRemoveController implements Initializable{

    @FXML
    private ComboBox<Integer> cookIDs;

    @FXML
    private Button cookRemoveBtn;
    
    private  ObservableList<Integer> IDs;

    @FXML
    void removeCook(ActionEvent event) {
    	
    	if( cookIDs.getValue()!= null && MainController.getRest().removeCook(MainController.getRest().getRealCook(cookIDs.getValue()))) {
    		JOptionPane.showMessageDialog(null, "Cook " + cookIDs.getValue() + " was deleted succesfully");
    		IDs.remove(cookIDs.getValue());
    		MainController.getRest().serialize();
    		if(!MainController.getRest().getCooks().values().isEmpty()) {
    			cookIDs.setItems(IDs);
    			cookIDs.setValue(IDs.get(0));
    			
    		}
    		else {
    			IDs.clear();
    			cookIDs.setValue(null);
    			
    		}
    		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Remove cook Failed",
				      "SYSTEM ERROR", JOptionPane.ERROR_MESSAGE);
    	}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		IDs = FXCollections.observableArrayList();
		IDs.addAll(MainController.getRest().getCooks().keySet());
		cookIDs.setItems(IDs);
		if(!IDs.isEmpty()) {
			cookIDs.setValue(IDs.get(0));
		}
			
	}

}