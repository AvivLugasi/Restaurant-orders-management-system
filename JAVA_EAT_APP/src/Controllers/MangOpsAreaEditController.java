package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JOptionPane;

import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Utils.Neighberhood;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MangOpsAreaEditController implements Initializable{

    @FXML
    private Button editAreaBtn;

    @FXML
    private TextField areaName;

    @FXML
    private TextField delTimeFld;

    @FXML
    private ComboBox<Integer> IDsComboBx;

    @FXML
    private Button removeNeighBtn;

    @FXML
    private Button addNeighBtn;

    @FXML
    private Button removeDelivBtn;

    @FXML
    private Button addDelivBtn;

    @FXML
    private Button removeDelPBtn;

    @FXML
    private Button addDelPBtn;

    @FXML
    private ComboBox<Neighberhood> neighBx;
    
    private ObservableList<Neighberhood> NeighberhoodList = FXCollections.observableArrayList(Neighberhood.Neve_Shanan, Neighberhood.Kiriat_Haim, Neighberhood.DownTown, Neighberhood.Vardia, Neighberhood.Bat_Galim, Neighberhood.Merkaz_Karmel, Neighberhood.Denya, Neighberhood.Kiriat_Eliezer,
    		Neighberhood.Hadar, Neighberhood.Romema, Neighberhood.German_Colony, Neighberhood.Vadi_Nisnas, Neighberhood.Vadi_Saliv, Neighberhood.Neot_Peres, Neighberhood.Kababir, Neighberhood.Neve_David,
    		Neighberhood.Karmelia, Neighberhood.Halisa, Neighberhood.French_Karmel, Neighberhood.Ramat_Hanasi, Neighberhood.Neve_Yosef, Neighberhood.Ramat_Almogi);

    private ObservableList<Integer> areaIDs = FXCollections.observableArrayList();
    
    private ObservableList<Integer> delvIDs = FXCollections.observableArrayList();
    
    private ObservableList<Integer> delPIDs = FXCollections.observableArrayList();
    
    private ObservableList<Integer> areadelvIDs = FXCollections.observableArrayList();
    
    private ObservableList<Integer> areadelPIDs;
    
    private ObservableList<Neighberhood> areaNeighberhoodList;
    
    @FXML
    private ComboBox<Integer> delvBx;

    @FXML
    private ComboBox<Integer> delPBx;
    
    @FXML
    private ComboBox<Neighberhood> areaneighBx;
    
    @FXML
    private ComboBox<Integer> areadelvBx;

    @FXML
    private ComboBox<Integer> areadelPBx;

    @FXML
    void addDelP(ActionEvent event) {
    	
    	
    	if(delPBx.getValue() != null) {
    		DeliveryArea area = MainController.getRest().getRealDeliveryArea(IDsComboBx.getValue());
    		area.addDelPerson(MainController.getRest().getRealDeliveryPerson(delPBx.getValue()));
    		areadelPIDs.add(delPBx.getValue());
    		delPIDs.remove(delPBx.getValue());
    		areadelPBx.setItems(areadelPIDs);
    		delPBx.setItems(delPIDs);
    	}
    }

    @FXML
    void addDeliv(ActionEvent event) {

    	if(delvBx.getValue() != null) {
    		DeliveryArea area = MainController.getRest().getRealDeliveryArea(IDsComboBx.getValue());
    		MainController.getRest().getRealDelivery(delvBx.getValue()).setArea(area); 		
    		area.addDelivery(MainController.getRest().getRealDelivery(delvBx.getValue()));
    		areadelvIDs.add(delvBx.getValue());
    		delvIDs.remove(delvBx.getValue());
    		areadelvBx.setItems(areadelvIDs);
    		delvBx.setItems(delvIDs);
    	}
    }

    @FXML
    void addNeigh(ActionEvent event) {

    	if(neighBx.getValue() != null) {
    		DeliveryArea area = MainController.getRest().getRealDeliveryArea(IDsComboBx.getValue());
    		area.addNeighberhood(neighBx.getValue());
    		areaNeighberhoodList.add(neighBx.getValue());
    		NeighberhoodList.remove(neighBx.getValue());
    		neighBx.setItems(null);
    		neighBx.setItems(NeighberhoodList);
    		areaneighBx.setItems(null);
    		areaneighBx.setItems(areaNeighberhoodList);
    	}
    }

    @FXML
    void editArea(ActionEvent event) {

    	if(IDsComboBx.getValue() != null && !areaName.getText().isEmpty()) {
    		DeliveryArea area = MainController.getRest().getRealDeliveryArea(IDsComboBx.getValue());
    		area.setAreaName(areaName.getText());
    		JOptionPane.showMessageDialog(null, "Delivery area " + area.getId() + " was edited succesfully");
 			MainController.getRest().serialize();
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

    @FXML
    void removeDelP(ActionEvent event) {

    	if(areadelPBx.getValue() != null) {
    		DeliveryArea area = MainController.getRest().getRealDeliveryArea(IDsComboBx.getValue());
    		area.removeDelPerson(MainController.getRest().getRealDeliveryPerson(areadelPBx.getValue()));
    		areadelPIDs.remove(areadelPBx.getValue());
    		delPIDs.add(areadelPBx.getValue());
    		areadelPBx.setItems(areadelPIDs);
    		delPBx.setItems(delPIDs);
    	}
    }

    @FXML
    void removeDeliv(ActionEvent event) {

    	if(areadelvBx.getValue() != null) {
    		DeliveryArea area = MainController.getRest().getRealDeliveryArea(IDsComboBx.getValue());
    		area.removeDelivery(MainController.getRest().getRealDelivery(areadelvBx.getValue()));
    		areadelvIDs.remove(areadelvBx.getValue());
    		delvIDs.add(areadelvBx.getValue());
    		areadelvBx.setItems(areadelvIDs);
    		delvBx.setItems(delvIDs);
    	}
    }

    @FXML
    void removeNeigh(ActionEvent event) {

    	if(areaneighBx.getValue() != null) {
    		DeliveryArea area = MainController.getRest().getRealDeliveryArea(IDsComboBx.getValue());
    		area.removeNeighberhood(areaneighBx.getValue());
    		areaNeighberhoodList.remove(areaneighBx.getValue());
    		NeighberhoodList.add(areaneighBx.getValue());
    		neighBx.setItems(null);
    		neighBx.setItems(NeighberhoodList);
    		neighBx.setValue(null);
    		areaneighBx.setItems(null);
    		areaneighBx.setItems(areaNeighberhoodList);
    		areaneighBx.setValue(null);
    	
    	}
    }

    @FXML
    void showDetails(ActionEvent event) {

    	if(IDsComboBx.getValue() != null) {
    		DeliveryArea area = MainController.getRest().getRealDeliveryArea(IDsComboBx.getValue());
    		areaName.setText(area.getAreaName());
    		delTimeFld.setText(area.getDeliverTime() + "");
    		areaNeighberhoodList = FXCollections.observableArrayList(area.getNeighberhoods());
    		Set<DeliveryPerson> areaDelP = area.getDelPersons();
    		areadelPIDs = FXCollections.observableArrayList();
    		for(DeliveryPerson dP: areaDelP) {
    			areadelPIDs.add(dP.getId());
    		}
    		Set<Delivery> areaDels = area.getDelivers();
    		areadelvIDs = FXCollections.observableArrayList();
    		for(Delivery d: areaDels) {
    			areadelvIDs.add(d.getId());
    		}
    		areaneighBx.setItems(areaNeighberhoodList);
    		areadelvBx.setItems(areadelvIDs);
    		areadelPBx.setItems(areadelPIDs);
    		delvIDs = FXCollections.observableArrayList(MainController.getRest().getDeliveries().keySet());
    		if(!delvIDs.isEmpty()) {
    			delvIDs.removeAll(areadelvIDs);
    		}
    		delPIDs = FXCollections.observableArrayList(MainController.getRest().getDeliveryPersons().keySet());
    		if(!delPIDs.isEmpty()) {
    			delPIDs.removeAll(areadelPIDs);
    		}
    		ObservableList<Neighberhood> NeighberhoodList = FXCollections.observableArrayList(this.NeighberhoodList);
    		NeighberhoodList.removeAll(areaNeighberhoodList);
    		neighBx.setItems(NeighberhoodList);
    		delvBx.setItems(delvIDs);
    		delPBx.setItems(delPIDs);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		areaIDs.addAll(MainController.getRest().getAreas().keySet());
		IDsComboBx.setItems(areaIDs);
		IDsComboBx.setValue(null);
	}

}
