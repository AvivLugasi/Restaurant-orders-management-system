package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Model.Customer;
import Utils.AssistingMethods;
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

public class MangOpsCustomerAddController implements Initializable {

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
    private ComboBox<Neighberhood> customerNeighberhood;

    @FXML
    private Button addCustomerBtn;

    @FXML
    private TextField customerFName;
    
    @FXML
    private TextField custEmail;
    
    private AssistingMethods aM;
    
    private int biggestID;
    
    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);
    private ObservableList<Neighberhood> neighberhoodList = FXCollections.observableArrayList(Neighberhood.Neve_Shanan, Neighberhood.Kiriat_Haim, Neighberhood.DownTown, Neighberhood.Vardia, Neighberhood.Bat_Galim, Neighberhood.Merkaz_Karmel, Neighberhood.Denya, Neighberhood.Kiriat_Eliezer,
    		Neighberhood.Hadar, Neighberhood.Romema, Neighberhood.German_Colony, Neighberhood.Vadi_Nisnas, Neighberhood.Vadi_Saliv, Neighberhood.Neot_Peres, Neighberhood.Kababir, Neighberhood.Neve_David,
    		Neighberhood.Karmelia, Neighberhood.Halisa, Neighberhood.French_Karmel, Neighberhood.Ramat_Hanasi, Neighberhood.Neve_Yosef, Neighberhood.Ramat_Almogi);
    

    @FXML
    void addCustomer(ActionEvent event) {
    	if(customerFName.getText().isEmpty() || customerLName.getText().isEmpty() || customerBday.getValue() == null || !custEmail.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    	else {
    		Customer cust = new Customer(customerFName.getText(), customerLName.getText(),customerBday.getValue(),customerGender.getValue(), customerNeighberhood.getValue(),sensLactose.isSelected(), sensGluten.isSelected(), "USERNAME", "pASSWORD!", null, custEmail.getText());
    		if(MainController.getRest().getCustomers().containsKey(cust.getId())) {
    			cust.setId(biggestID);
    			biggestID++;
    		}
    		MainController.getRest().addCustomer(cust);
    		JOptionPane.showMessageDialog(null, "Customer " + cust.getId() + " was added succesfully");
 			MainController.getRest().serialize();
    		
    	}
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		customerGender.setItems(genderList);
		customerGender.setValue(Gender.Male);
		customerNeighberhood.setItems(neighberhoodList);
		customerNeighberhood.setValue(Neighberhood.Neve_Shanan);
		aM = new AssistingMethods();
		biggestID = aM.getBiggestID(MainController.getRest().getCustomers().keySet()) + 1;		
	}

}
