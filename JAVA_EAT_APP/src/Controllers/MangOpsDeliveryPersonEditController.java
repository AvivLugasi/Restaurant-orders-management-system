package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.MinorException;
import Model.DeliveryPerson;
import Utils.Gender;
import Utils.Vehicle;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class MangOpsDeliveryPersonEditController implements Initializable{

    @FXML
    private TextField delPLName;

    @FXML
    private DatePicker delPBday;

    @FXML
    private ComboBox<Vehicle> vehicle;

    @FXML
    private ComboBox<Integer> areasIDs;

    @FXML
    private Button editDelPBtn;

    @FXML
    private TextField delPFName;

    @FXML
    private ComboBox<Gender> delPGender;

    @FXML
    private ComboBox<Integer> delPIDs;
    
    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);

    private ObservableList<Integer> areasList = FXCollections.observableArrayList();
    
    private ObservableList<Integer> delPList = FXCollections.observableArrayList();
    
    private ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList(Vehicle.Bicycle, Vehicle.Car, Vehicle.Motorcycle);;

    @FXML
    void editDelP(ActionEvent event) {

    	if(!delPFName.getText().isEmpty() && !delPLName.getText().isEmpty() && delPBday.getValue() != null && areasIDs.getValue() != null && delPIDs.getValue() != null) {
    		try {
    			LocalDate delPAge = delPBday.getValue();
    			LocalDate today = LocalDate.now();
    			if((today.getYear() - delPAge.getYear() < 18) || (today.getYear() - delPAge.getYear() == 18 && today.getMonthValue() < delPAge.getMonthValue()) || (today.getYear() - delPAge.getYear() == 18 && today.getMonthValue() == delPAge.getMonthValue() && today.getDayOfMonth() < delPAge.getDayOfMonth())) {
    				throw new MinorException();
    			}
    			DeliveryPerson dP = MainController.getRest().getRealDeliveryPerson(delPIDs.getValue());
	    		dP.setFirstName(delPFName.getText()); dP.setLastName(delPLName.getText());
	    		dP.setGender(delPGender.getValue());dP.setBirthDay(delPBday.getValue());
	    		dP.setVehicle(vehicle.getValue());dP.setArea(MainController.getRest().getRealDeliveryArea(areasIDs.getValue()));
	    		JOptionPane.showMessageDialog(null, "Delivery Person " + dP.getId() + " was edited succesfully");
	 			MainController.getRest().serialize();
    		}catch(MinorException e) {
    			
    		}
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

    @FXML
    void showDetails(ActionEvent event) {

    	if(delPIDs.getValue() != null) {
    		DeliveryPerson dP = MainController.getRest().getRealDeliveryPerson(delPIDs.getValue());
    		delPLName.setText(dP.getLastName());
    		delPFName.setText(dP.getFirstName());
    		delPBday.setValue(dP.getBirthDay());
    		delPGender.setValue(dP.getGender());
    		if(dP.getArea() != null)
    			areasIDs.setValue(dP.getArea().getId());
    		vehicle.setValue(dP.getVehicle());
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		delPGender.setItems(genderList);
		vehicle.setItems(vehicleList);
		areasList.addAll(MainController.getRest().getAreas().keySet());
		areasIDs.setItems(areasList);
		delPList.addAll(MainController.getRest().getDeliveryPersons().keySet());
		delPIDs.setItems(delPList);
		
	}

}
