package Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.MinorException;
import Model.Cook;
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



public class MangOpsCookEditController implements Initializable{

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
    private Button editCookBtn;

    @FXML
    private TextField cookFName;

    @FXML
    private ComboBox<Integer> editCookComboBx;
    
    private ObservableList<Expertise> expList = FXCollections.observableArrayList(Expertise.American,Expertise.Asian,Expertise.French, Expertise.Indian, Expertise.Italien, Expertise.Mediterranean);
    
    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);

    private ObservableList<Integer> IDs = FXCollections.observableArrayList();
    
    @FXML
    void editCook(ActionEvent event) {

    	if(editCookComboBx.getValue() != null && !cookFName.getText().isEmpty() && !cookLName.getText().isEmpty() && cookBday.getValue() != null) {
			try {
				LocalDate cookAge = cookBday.getValue();
    			LocalDate today = LocalDate.now();
    			if((today.getYear() - cookAge.getYear() < 18) || (today.getYear() - cookAge.getYear() == 18 && today.getMonthValue() < cookAge.getMonthValue()) || (today.getYear() - cookAge.getYear() == 18 && today.getMonthValue() == cookAge.getMonthValue() && today.getDayOfMonth() < cookAge.getDayOfMonth())) {
    				throw new MinorException();
    			}
	    		Cook c = MainController.getRest().getRealCook(editCookComboBx.getValue());
				c.setFirstName(cookFName.getText());
				c.setLastName(cookLName.getText());
				c.setBirthDay(cookBday.getValue());
				c.setGender(cookGender.getValue());
				c.setExpert(cookExp.getValue());
				boolean isChef = true;
				if(!cookChef.isSelected()) {
					isChef = false;
				}
				c.setChef(isChef);
				MainController.getRest().serialize();
			}
			catch(MinorException e) {
				
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid input",
  				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
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

    @FXML
    void showEditCookData(ActionEvent event) {

    	if(editCookComboBx.getValue() != null) {
    		Cook cook = MainController.getRest().getRealCook(editCookComboBx.getValue());
    		cookFName.setText(cook.getFirstName());
    		cookLName.setText(cook.getLastName());
    		cookBday.setValue(cook.getBirthDay());
    		cookGender.setValue(cook.getGender());
    		if(cook.isChef()) {
    			cookChef.setSelected(true);
    		}
    		else {
    			cookNoChef.setSelected(true);
    		}
    		cookExp.setValue(cook.getExpert());
    		
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cookGender.setItems(genderList);
		cookGender.setValue(Gender.Male);
		cookExp.setItems(expList);
		cookExp.setValue(Expertise.American);
		IDs.addAll(MainController.getRest().getCooks().keySet());
		if(!IDs.isEmpty()) {
			editCookComboBx.setItems(IDs);
			editCookComboBx.setValue(null);
		}
		
	}

}
