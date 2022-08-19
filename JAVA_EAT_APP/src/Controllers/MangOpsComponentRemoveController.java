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

public class MangOpsComponentRemoveController implements Initializable{

    @FXML
    private ComboBox<Integer> compsIDs;

    @FXML
    private Button compRemoveBtn;
    
    private  ObservableList<Integer> IDs;

    @FXML
    void removeComp(ActionEvent event) {

    	if( compsIDs.getValue()!= null && MainController.getRest().removeComponent(MainController.getRest().getRealComponent(compsIDs.getValue()))) {
    		JOptionPane.showMessageDialog(null, "Component " + compsIDs.getValue() + " was deleted succesfully");
    		IDs.remove(compsIDs.getValue());
    		MainController.getRest().serialize();
    		if(!MainController.getRest().getComponenets().values().isEmpty()) {
    			compsIDs.setItems(IDs);
    			compsIDs.setValue(IDs.get(0));
    			
    		}
    		else {
    			IDs.clear();
    			compsIDs.setValue(null);
    			
    		}
    		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Remove component Failed",
				      "SYSTEM ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		IDs = FXCollections.observableArrayList();
		IDs.addAll(MainController.getRest().getComponenets().keySet());
		compsIDs.setItems(IDs);
		if(!IDs.isEmpty()) {
			compsIDs.setValue(IDs.get(0));
		}
	}

}
