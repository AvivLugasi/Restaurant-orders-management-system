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

public class MangOrderManagmentOrderRemoveController implements Initializable{

    @FXML
    private ComboBox<Integer> orderIDs;

    @FXML
    private Button orderRemoveBtn;
    
    private  ObservableList<Integer> IDs;

    @FXML
    void orderRemove(ActionEvent event) {

    	if( orderIDs.getValue()!= null && MainController.getRest().removeOrder(MainController.getRest().getRealOrder(orderIDs.getValue()))) {
    		JOptionPane.showMessageDialog(null, "Order " + orderIDs.getValue() + " was deleted succesfully");
    		IDs.remove(orderIDs.getValue());
    		MainController.getRest().serialize();
    		if(!MainController.getRest().getOrders().values().isEmpty()) {
    			orderIDs.setItems(IDs);
    			orderIDs.setValue(IDs.get(0));
    			
    		}
    		else {
    			IDs.clear();
    			orderIDs.setValue(null);
    			
    		}
    		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Order remove Failed",
				      "SYSTEM ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		IDs = FXCollections.observableArrayList();
		IDs.addAll(MainController.getRest().getOrders().keySet());
		orderIDs.setItems(IDs);
		if(!IDs.isEmpty()) {
			orderIDs.setValue(IDs.get(0));
		}
	}

}
