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

public class MangOpsAreaRemoveController implements Initializable{

   @FXML
   private ComboBox<Integer> oldAreasIDs;
	   
    @FXML
    private ComboBox<Integer> newAreasIDs;
    
    @FXML
    private Button areaRemoveBtn;

    private  ObservableList<Integer> oldIDs;
    
    private  ObservableList<Integer> newIDs;
    
    @FXML
    void removeArea(ActionEvent event) {

    	if( oldAreasIDs.getValue()!= null && newAreasIDs.getValue() != null && MainController.getRest().removeDeliveryArea(MainController.getRest().getRealDeliveryArea(oldAreasIDs.getValue()),MainController.getRest().getRealDeliveryArea(newAreasIDs.getValue()))) {
    		JOptionPane.showMessageDialog(null, "Area " + oldAreasIDs.getValue() + " was deleted succesfully");
    		oldIDs.remove(oldAreasIDs.getValue());
    		//newIDs.remove(newAreasIDs.getValue());
    		MainController.getRest().serialize();
    		if(!MainController.getRest().getAreas().values().isEmpty()) {
    			oldAreasIDs.setItems(oldIDs);
    			oldAreasIDs.setValue(oldIDs.get(0));
    			//newAreasIDs.setItems(newIDs);
    			//newAreasIDs.setValue(newIDs.get(0));
    		}
    		else {
    			oldIDs.clear();
    			newIDs.clear();
    			oldAreasIDs.setValue(null);
    			newAreasIDs.setValue(null);
    		}
    		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Area remove Failed",
				      "SYSTEM ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

    @FXML
    void setNewAreas(ActionEvent event) {
    	
    	if(oldAreasIDs.getValue() != null) {
	    	newIDs = FXCollections.observableArrayList();
			newIDs.addAll(MainController.getRest().getAreas().keySet());
			newIDs.remove(oldAreasIDs.getValue());
			newAreasIDs.setItems(newIDs);
			if(!newIDs.isEmpty()) {
				newAreasIDs.setValue(newIDs.get(0));
			}
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		oldIDs = FXCollections.observableArrayList();
		oldIDs.addAll(MainController.getRest().getAreas().keySet());
		oldAreasIDs.setItems(oldIDs);
		if(!oldIDs.isEmpty()) {
			oldAreasIDs.setValue(oldIDs.get(0));
		}
	}

}
