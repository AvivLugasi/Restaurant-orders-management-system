package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.Customer;
import Utils.Gender;
import Utils.Neighberhood;
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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MangOpsCustomerEditController implements Initializable{

    @FXML
    private TextField customerLName;

    @FXML
    private DatePicker customerBday;

    @FXML
    private ComboBox<Gender> customerGender;

    @FXML
    private RadioButton sensLactose;

    @FXML
    private RadioButton sensGluten;

    @FXML
    private ComboBox<Neighberhood> neighberhood;

    @FXML
    private Button editCustomerBtn;

    @FXML
    private TextField customerFName;

    @FXML
    private ComboBox<Integer> editCustomerComboBx;
    
    @FXML
    private TextField custEmail;

    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);
    private ObservableList<Neighberhood> neighberhoodList = FXCollections.observableArrayList(Neighberhood.Neve_Shanan, Neighberhood.Kiriat_Haim, Neighberhood.DownTown, Neighberhood.Vardia, Neighberhood.Bat_Galim, Neighberhood.Merkaz_Karmel, Neighberhood.Denya, Neighberhood.Kiriat_Eliezer,
    		Neighberhood.Hadar, Neighberhood.Romema, Neighberhood.German_Colony, Neighberhood.Vadi_Nisnas, Neighberhood.Vadi_Saliv, Neighberhood.Neot_Peres, Neighberhood.Kababir, Neighberhood.Neve_David,
    		Neighberhood.Karmelia, Neighberhood.Halisa, Neighberhood.French_Karmel, Neighberhood.Ramat_Hanasi, Neighberhood.Neve_Yosef, Neighberhood.Ramat_Almogi);
    
    private ObservableList<Integer> IDs = FXCollections.observableArrayList();
    
    
    @FXML
    void editCustomer(ActionEvent event) {
    	

    	if(editCustomerComboBx.getValue() != null && !customerFName.getText().isEmpty() && !customerLName.getText().isEmpty() && customerBday.getValue() != null && !custEmail.getText().isEmpty()) {
			Customer c = MainController.getRest().getRealCustomer(editCustomerComboBx.getValue());
			c.setFirstName(customerFName.getText());
			c.setLastName(customerLName.getText());
			c.setBirthDay(customerBday.getValue());
			c.setGender(customerGender.getValue());
			c.setNeighberhood(neighberhood.getValue());
			c.setSensitiveToGluten(sensGluten.isSelected());
			c.setSensitiveToLactose(sensLactose.isSelected());
			c.setEmail(custEmail.getText());
			MainController.getRest().serialize();
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid input",
  				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
		}
    }
    	
    

    @FXML
    void showEditCustomerData(ActionEvent event) {
    	if(editCustomerComboBx.getValue() != null) {
    		Customer cust = MainController.getRest().getRealCustomer(editCustomerComboBx.getValue());
    		customerFName.setText(cust.getFirstName());
    		customerLName.setText(cust.getLastName());
    		customerBday.setValue(cust.getBirthDay());
    		customerGender.setValue(cust.getGender());
    		custEmail.setText(cust.getEmail());
    		if(cust.isSensitiveToLactose()) {
    			sensLactose.setSelected(true);
    		}
    		else {
    			sensLactose.setSelected(false);
    		}
    		if(cust.isSensitiveToGluten()){
    			sensGluten.setSelected(true);
    		}
    		else {
    			sensGluten.setSelected(false);
    		}
    		neighberhood.setValue(cust.getNeighberhood());
    		
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		customerGender.setItems(genderList);
		customerGender.setValue(Gender.Male);
		neighberhood.setItems(neighberhoodList);
		neighberhood.setValue(Neighberhood.Neve_Shanan);
		IDs.addAll(MainController.getRest().getCustomers().keySet());
		if(!IDs.isEmpty()) {
			editCustomerComboBx.setItems(IDs);
			editCustomerComboBx.setValue(null);
		}
		
	}
    
   

}
