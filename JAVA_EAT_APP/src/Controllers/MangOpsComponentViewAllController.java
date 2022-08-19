package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import Model.Component;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class MangOpsComponentViewAllController implements Initializable{

    @FXML
    private TextArea viewResualt;

    @FXML
    private ListView<String> list;

    private ObservableList<String> compList = FXCollections.observableArrayList();
    
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
    		Component comp = MainController.getRest().getRealComponent(Integer.parseInt(ID));
			viewResualt.setText(comp.toString());
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		String s = "";
		String ID = "";
		Set<Integer> compIDs = MainController.getRest().getComponenets().keySet();
		if(!compIDs.isEmpty()) {
			for(Integer id: compIDs) {
				s += id + " " + MainController.getRest().getRealComponent(id).getComponentName();
				compList.add(s);
				s = "";
			}
			list.setItems(compList);
			list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			list.getSelectionModel().select(compList.get(0));
			s = compList.get(0);
			
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
			 Component comp = MainController.getRest().getRealComponent(Integer.parseInt(ID));
			viewResualt.setText(comp.toString());
		}
	}

}
