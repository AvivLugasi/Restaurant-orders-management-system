package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import Model.DeliveryArea;
import Utils.AssistingMethods;
import Utils.Neighberhood;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MangOpsAreaAddController implements Initializable{

    @FXML
    private Button addAreaBtn;

    @FXML
    private TextField areaName;

    @FXML
    private TextField delTimeFld;

    @FXML
    private Label inputError;

    @FXML
    private ListView<Neighberhood> neibgList;
    
    private ObservableList<Neighberhood> tempNeighberhoodList = FXCollections.observableArrayList(Neighberhood.Neve_Shanan, Neighberhood.Kiriat_Haim, Neighberhood.DownTown, Neighberhood.Vardia, Neighberhood.Bat_Galim, Neighberhood.Merkaz_Karmel, Neighberhood.Denya, Neighberhood.Kiriat_Eliezer,
    		Neighberhood.Hadar, Neighberhood.Romema, Neighberhood.German_Colony, Neighberhood.Vadi_Nisnas, Neighberhood.Vadi_Saliv, Neighberhood.Neot_Peres, Neighberhood.Kababir, Neighberhood.Neve_David,
    		Neighberhood.Karmelia, Neighberhood.Halisa, Neighberhood.French_Karmel, Neighberhood.Ramat_Hanasi, Neighberhood.Neve_Yosef, Neighberhood.Ramat_Almogi);
   
    private HashSet<Neighberhood> neighbSet;
    
    private AssistingMethods aM;
    
    private int biggestID;
    
    @FXML
    void addArea(ActionEvent event) {
    	
    	getNeighberhoods();
    	if(!areaName.getText().isEmpty() && inputError.getText().isEmpty() && !neighbSet.isEmpty() && !delTimeFld.getText().isEmpty()) {
    		DeliveryArea area = new DeliveryArea(areaName.getText(), neighbSet, Integer.parseInt(delTimeFld.getText()));
    		if(MainController.getRest().getAreas().containsKey(area.getId())) {
    			area.setId(biggestID);
    			biggestID++;
    		}
    		MainController.getRest().addDeliveryArea(area);
    		JOptionPane.showMessageDialog(null, "Delivery area " + area.getId() + " was added succesfully");
 			MainController.getRest().serialize();
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void getNeighberhoods() {
    	
    	neighbSet = new HashSet<Neighberhood>();
    	neighbSet.addAll(neibgList.getSelectionModel().getSelectedItems());
    }

    @FXML
    void inputValidation(KeyEvent event) {

    	String delTime = delTimeFld.getText();
    	if(delTime.isEmpty()) {
    		inputError.setText("");
    		return;
    	}
    	if(delTime.contains(" ")) {
    		inputError.setText("Illeagal input, Contain white character(space)");
    		return;
    	}
    	for(int i=0; i<delTime.length();i++) {
    		 if((delTime.charAt(i) < '0' || delTime.charAt(i) > '9') ) {
    			inputError.setText("Illeagal input, price must contains only Integer");
    			break;
    		}
    		else {
    			inputError.setText("");
   
    		}
    		
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		neibgList.setItems(tempNeighberhoodList);
		neibgList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		aM = new AssistingMethods();
		biggestID = aM.getBiggestID(MainController.getRest().getAreas().keySet()) + 1;
	}

}
