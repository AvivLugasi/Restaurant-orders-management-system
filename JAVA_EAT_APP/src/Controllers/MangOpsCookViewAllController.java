package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import Model.Cook;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class MangOpsCookViewAllController implements Initializable{

    @FXML
    private TextArea viewResualt;

    @FXML
    private ListView<String> list;
    
    private ObservableList<String> cookList = FXCollections.observableArrayList();

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
			Cook cook = MainController.getRest().getRealCook(Integer.parseInt(ID));
			viewResualt.setText(cook.toString());
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String s = "";
		String ID = "";
		Set<Integer> cooksIDs = MainController.getRest().getCooks().keySet();
		if(!cooksIDs.isEmpty()) {
			for(Integer id: cooksIDs) {
				s += id + " " + MainController.getRest().getRealCook(id).getFirstName() + " " + MainController.getRest().getRealCook(id).getLastName();
				cookList.add(s);
				s = "";
			}
			list.setItems(cookList);
			list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			list.getSelectionModel().select(cookList.get(0));
			s = cookList.get(0);
			
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
			Cook cook = MainController.getRest().getRealCook(Integer.parseInt(ID));
			viewResualt.setText(cook.toString());
		}
	}

}
