package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import Model.DeliveryPerson;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class MangOpsDeliveryPersonViewAllController implements Initializable{

    @FXML
    private TextArea viewResualt;

    @FXML
    private ListView<String> list;
    
    private ObservableList<String> delPList = FXCollections.observableArrayList();

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
    		DeliveryPerson dP = MainController.getRest().getRealDeliveryPerson(Integer.parseInt(ID));
			viewResualt.setText(dP.toString());
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String s = "";
		String ID = "";
		Set<Integer> DelPIDs = MainController.getRest().getDeliveryPersons().keySet();
		if(!DelPIDs.isEmpty()) {
			for(Integer id: DelPIDs) {
				s += id + " " + MainController.getRest().getRealDeliveryPerson(id).getFirstName() + " " + MainController.getRest().getRealDeliveryPerson(id).getLastName();
				delPList.add(s);
				s = "";
			}
			list.setItems(delPList);
			list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			list.getSelectionModel().select(delPList.get(0));
			s = delPList.get(0);
			
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
			DeliveryPerson dP = MainController.getRest().getRealDeliveryPerson(Integer.parseInt(ID));
			viewResualt.setText(dP.toString());
		}
	}
    
    

}
