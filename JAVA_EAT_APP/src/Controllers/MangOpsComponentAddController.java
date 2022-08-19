package Controllers;


import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import Model.Component;
import Utils.AssistingMethods;
import default_package.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MangOpsComponentAddController {

    @FXML
    private RadioButton lactose;

    @FXML
    private RadioButton gluten;

    @FXML
    private Button addCompBtn;

    @FXML
    private TextField compName;

    @FXML
    private TextField compPrice;

    @FXML
    private Label inputError;
    
    private AssistingMethods aM;
    
    private int biggestID = 0;

    @FXML
    void addComp(ActionEvent event) {
    	
    	if(compName.getText().isEmpty() || compName.getText().isEmpty() || !inputError.getText().isEmpty() || compPrice.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    	else {
    		
    		Component comp = new Component(compName.getText(),lactose.isSelected(), gluten.isSelected(), Double.parseDouble(compPrice.getText()));
    		if(MainController.getRest().getComponenets().containsKey(comp.getId())) {
    			if(aM == null) {
    				aM = new AssistingMethods();
    			}
    			if(biggestID == 0) {
    				biggestID = aM.getBiggestID(MainController.getRest().getComponenets().keySet()) + 1;
    			}
    			comp.setId(biggestID);
    			biggestID++;
    		}
    		MainController.getRest().addComponent(comp);
    		JOptionPane.showMessageDialog(null, "Component " + comp.getId() + " was added succesfully");
 			MainController.getRest().serialize();
    		
    	}
    }

    @FXML
    void inputValidation(KeyEvent event) {
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

}
