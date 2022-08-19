package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.Component;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MangOpsComponentChangePriceController implements Initializable{

    @FXML
    private ComboBox<Integer> compsIDs;

    @FXML
    private Button changePriceBtn;

    @FXML
    private TextField compPrice;
    
    @FXML
    private Label inputError;
    
    private  ObservableList<Integer> IDs;

    @FXML
    void changePrice(ActionEvent event) {
    	
    	if(!IDs.isEmpty() && inputError.getText().isEmpty() && !compPrice.getText().isEmpty()) {
    		Component comp = MainController.getRest().getRealComponent(compsIDs.getValue());
    		comp.setPrice(Double.parseDouble(compPrice.getText()));
    		JOptionPane.showMessageDialog(null, "Component " + comp.getId() + " price was changed succesfully");
 			MainController.getRest().serialize();		
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Failed to change component!",
				      "ERROR", JOptionPane.ERROR_MESSAGE);
    	}

    }

    @FXML
    void inputValidetion(KeyEvent event) {

    	String price = compPrice.getText();
    	int dotCounter = 0;
    	if(price.isEmpty()) {
    		inputError.setText("");
    		return;
    	}
    	if(price.contains(" ")) {
    		inputError.setText("Illeagal input, Contain white character(space)");
    		return;
    	}
    	for(int i=0; i<price.length();i++) {
    		if(price.charAt(i) == '.')
    			dotCounter++;
    		if(price.charAt(0) == '.') {
    			inputError.setText("Illeagal input, decimal dot cannot be the first character");
    			break;
    		}	
    		else if(dotCounter > 1) {
    			inputError.setText("Illeagal input, price must contains only Integer and one decimal dot");
    			break;
    		}
    		else if((price.charAt(i) < '0' || price.charAt(i) > '9') && price.charAt(i) != '.') {
    			inputError.setText("Illeagal input, price must contains only Integer and one decimal dot");
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
		IDs = FXCollections.observableArrayList();
		IDs.addAll(MainController.getRest().getComponenets().keySet());
		compsIDs.setItems(IDs);
		if(!IDs.isEmpty()) {
			compsIDs.setValue(IDs.get(0));
			compPrice.setText(MainController.getRest().getRealComponent(compsIDs.getValue()).getPrice() + "");
		}
	}
	
	@FXML
    void ChangeViewPrice(ActionEvent event) {

		Integer ID = compsIDs.getValue();
		if(ID != null) {
			compPrice.setText(MainController.getRest().getRealComponent(ID).getPrice() + "");
		}
    }

}
