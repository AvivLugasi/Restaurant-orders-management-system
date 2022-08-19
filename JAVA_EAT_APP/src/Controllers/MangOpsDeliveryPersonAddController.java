package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.MinorException;
import Model.DeliveryPerson;
import Utils.AssistingMethods;
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

public class MangOpsDeliveryPersonAddController implements Initializable{

    @FXML
    private TextField delPLName;

    @FXML
    private DatePicker delPBday;

    @FXML
    private ComboBox<Vehicle> vehicle;

    @FXML
    private ComboBox<Integer> areasIDs;
    
    @FXML
    private ComboBox<Gender> delPGender;
    
    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);

    private ObservableList<Integer> areasList = FXCollections.observableArrayList();
    
    private ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList(Vehicle.Bicycle, Vehicle.Car, Vehicle.Motorcycle);;
    
    @FXML
    private Button addDelPBtn;

    @FXML
    private TextField delPFName;
    
    private AssistingMethods aM;
    
    private int biggestID;

    @FXML
    void addDelP(ActionEvent event) {

    	if(!delPFName.getText().isEmpty() && !delPLName.getText().isEmpty() && delPBday.getValue() != null && areasIDs.getValue() != null) {
	    	try {	
	    		LocalDate delPAge = delPBday.getValue();
    			LocalDate today = LocalDate.now();
    			if((today.getYear() - delPAge.getYear() < 18) || (today.getYear() - delPAge.getYear() == 18 && today.getMonthValue() < delPAge.getMonthValue()) || (today.getYear() - delPAge.getYear() == 18 && today.getMonthValue() == delPAge.getMonthValue() && today.getDayOfMonth() < delPAge.getDayOfMonth())) {
    				throw new MinorException();
    			}
	    		DeliveryPerson dP = new DeliveryPerson(delPFName.getText(), delPLName.getText(), delPBday.getValue(),delPGender.getValue(),vehicle.getValue(),MainController.getRest().getRealDeliveryArea(areasIDs.getValue()));
	    		if(MainController.getRest().getDeliveryPersons().containsKey(dP.getId())) {
	    			dP.setId(biggestID);
	    			biggestID++;
	    		}
	    		MainController.getRest().addDeliveryPerson(dP, MainController.getRest().getRealDeliveryArea(areasIDs.getValue()));
	    		JOptionPane.showMessageDialog(null, "Delivery Person " + dP.getId() + " was added succesfully");
	 			MainController.getRest().serialize();
	    	}catch(MinorException e) {
	    		
	    	}
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		delPGender.setItems(genderList);
		delPGender.setValue(Gender.Male);
		vehicle.setItems(vehicleList);
		vehicle.setValue(Vehicle.Motorcycle);
		areasList.addAll(MainController.getRest().getAreas().keySet());
		areasIDs.setItems(areasList);
		if(!areasList.isEmpty()) {
			areasIDs.setValue(areasList.get(0));
			aM = new AssistingMethods();
			biggestID = aM.getBiggestID(MainController.getRest().getDeliveryPersons().keySet()) + 1;
		}
		
	}

}
