package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import Model.DeliveryArea;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class MangOpsAreaViewAllController implements Initializable{

    @FXML
    private TextArea viewResualt;

    @FXML
    private ListView<String> list;
    
    private ObservableList<String> areasList = FXCollections.observableArrayList();

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
    		DeliveryArea area = MainController.getRest().getRealDeliveryArea(Integer.parseInt(ID));
			viewResualt.setText(area.toString());
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String s = "";
		String ID = "";
		Set<Integer> areaIDs = MainController.getRest().getAreas().keySet();
		if(!areaIDs.isEmpty()) {
			for(Integer id: areaIDs) {
				s += id + " " + MainController.getRest().getRealDeliveryArea(id).getAreaName();
				areasList.add(s);
				s = "";
			}
			list.setItems(areasList);
			list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			list.getSelectionModel().select(areasList.get(0));
			s = areasList.get(0);
			
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
			 DeliveryArea area = MainController.getRest().getRealDeliveryArea(Integer.parseInt(ID));
			viewResualt.setText(area.toString());
		}
	}
    
    

}
