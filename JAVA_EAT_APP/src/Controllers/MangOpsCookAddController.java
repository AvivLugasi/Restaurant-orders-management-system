package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.MinorException;
import Model.Cook;
import Utils.AssistingMethods;
import Utils.Expertise;
import Utils.Gender;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class MangOpsCookAddController implements Initializable{

    @FXML
    private TextField cookLName;

    @FXML
    private DatePicker cookBday;

    @FXML
    private ComboBox<Gender> cookGender;

    @FXML
    private RadioButton cookChef;

    @FXML
    private RadioButton cookNoChef;

    @FXML
    private ComboBox<Expertise> cookExp;

    @FXML
    private Button addCookBtn;

    @FXML
    private TextField cookFName;
    
    private ObservableList<Expertise> expList = FXCollections.observableArrayList(Expertise.American,Expertise.Asian,Expertise.French, Expertise.Indian, Expertise.Italien, Expertise.Mediterranean);
    
    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);

    private AssistingMethods aM;
    
    private int biggestID;
    
    @FXML
    void addCook(ActionEvent event) {

    	if(cookFName.getText().isEmpty() || cookLName.getText().isEmpty() || cookBday.getValue() == null ) {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    	else {
    		try {
    			LocalDate cookAge = cookBday.getValue();
    			LocalDate today = LocalDate.now();
    			if((today.getYear() - cookAge.getYear() < 18) || (today.getYear() - cookAge.getYear() == 18 && today.getMonthValue() < cookAge.getMonthValue()) || (today.getYear() - cookAge.getYear() == 18 && today.getMonthValue() == cookAge.getMonthValue() && today.getDayOfMonth() < cookAge.getDayOfMonth())) {
    				throw new MinorException();
    			}
	    		boolean isChef = false;
	    		if(cookChef.isSelected()) {
	    			isChef = true;
	    		}
	    		Cook cook = new Cook(cookFName.getText(), cookLName.getText(),cookBday.getValue(),cookGender.getValue(),cookExp.getValue(),isChef);
	    		if(MainController.getRest().getCooks().containsKey(cook.getId())) {
	    			cook.setId(biggestID);
	    			biggestID++;
	    		}
	    		MainController.getRest().addCook(cook);
	    		JOptionPane.showMessageDialog(null, "Cook " + cook.getId() + " was added succesfully");
	 			MainController.getRest().serialize();
    		}
    		catch(MinorException e) {
    			
    		}
    	}
    }

    @FXML
    void ischef(ActionEvent event) {

    	if(cookChef.isSelected()) {
    		cookNoChef.setSelected(false);
    	}
    }

    @FXML
    void isnotChef(ActionEvent event) {

    	if(cookNoChef.isSelected()) {
    		cookChef.setSelected(false);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cookGender.setItems(genderList);
		cookGender.setValue(Gender.Male);
		cookExp.setItems(expList);
		cookExp.setValue(Expertise.American);
		aM = new AssistingMethods();
		biggestID = aM.getBiggestID(MainController.getRest().getCooks().keySet()) + 1;
		
	}

}
