package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Model.Cook;
import Utils.Expertise;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class MangOpsCooksViewByExpController implements Initializable{

    @FXML
    private TextArea viewResualt;

    @FXML
    private ListView<String> list;

    @FXML
    private ComboBox<Expertise> byExpComboBx;
    
    private ObservableList<String> cookList = FXCollections.observableArrayList();
    
    private ArrayList<Cook> cookByExpList;
    
    private ObservableList<Expertise> expList = FXCollections.observableArrayList(Expertise.American,Expertise.Asian,Expertise.French, Expertise.Indian, Expertise.Italien, Expertise.Mediterranean);

    @FXML
    void getRelevantCooks(ActionEvent event) {

    	list.getItems().removeAll(list.getItems());
    	String s = "";
		String ID = "";
    	if(byExpComboBx.getValue() != null) {
    		cookByExpList = MainController.getRest().GetCooksByExpertise(byExpComboBx.getValue());
    		if(cookByExpList != null) {
    			for(Cook c : cookByExpList) {
    				s += c.getId() + " " + c.getFirstName() + " " + c.getLastName();
    				cookList.add(s);
    				s = "";
    			}
    			list.setItems(cookList);
    			list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    			if(!cookList.isEmpty()) {
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
    }

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
		 
		byExpComboBx.setItems(expList);
		byExpComboBx.setValue(null);
		
	}

}
