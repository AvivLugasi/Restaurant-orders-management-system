package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.Component;
import Model.Cook;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryArea;
import Model.DeliveryPerson;
import Model.Dish;
import Model.Order;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class MangSearchController implements Initializable{

    @FXML
    private TextField searchId;

    @FXML
    private ComboBox<String> searchType;

    @FXML
    private Button search;

    @FXML
    private TextArea resualtTxt;

    @FXML
    private Label wrongIdSearchLbl;
    
    private ObservableList<String> typeList = FXCollections.observableArrayList("cook", "delivery person", "customer", "dish", "component", "order", "delivery", "area");

    @FXML
    void searchAct(ActionEvent event) {

    	int id = 0;
		String type = searchType.getValue();
		if(!wrongIdSearchLbl.getText().isEmpty()) {
			  JOptionPane.showMessageDialog(null, "Invalid ID!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
		else {
			id = Integer.parseInt(searchId.getText());
			
			if(type.equals("cook")) {
				
				Cook c = MainController.getRest().getRealCook(id);
				if(c == null) {
					JOptionPane.showMessageDialog(null, "No such cook!",
						      "ITEM DOES Not EXIST ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					resualtTxt.setText(c.toString());
				}
				
			}
			else if(type.equals("delivery person")) {
				
				DeliveryPerson delP = MainController.getRest().getRealDeliveryPerson(id);
				if(delP == null) {
					JOptionPane.showMessageDialog(null, "No such delivery person!",
						      "ITEM DOES Not EXIST ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					resualtTxt.setText(delP.toString());
				}
			}
			else if(type.equals("customer")) {
				
				Customer c = MainController.getRest().getRealCustomer(id);
				if(c == null) {
					JOptionPane.showMessageDialog(null, "No such customer!",
						      "ITEM DOES Not EXIST ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					resualtTxt.setText(c.toString());
				}
			}
			else if(type.equals("dish")) {
				
				Dish d = MainController.getRest().getRealDish(id);
				if(d == null) {
					JOptionPane.showMessageDialog(null, "No such dish!",
						      "ITEM DOES Not EXIST ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					resualtTxt.setText(d.toString());
				}
			}
			else if(type.equals("component")) {
				
				Component comp = MainController.getRest().getRealComponent(id);
				if(comp == null) {
					JOptionPane.showMessageDialog(null, "No such component!",
						      "ITEM DOES Not EXIST ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					resualtTxt.setText(comp.toString());
				}
			}
			else if(type.equals("order")) {
				
				Order o = MainController.getRest().getRealOrder(id);
				if(o == null) {
					JOptionPane.showMessageDialog(null, "No such order!",
						      "ITEM DOES Not EXIST ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					resualtTxt.setText(o.toString());
				}
			}
			else if(type.equals("delivery")) {
			
				Delivery d = MainController.getRest().getRealDelivery(id);
				if(d == null) {
					JOptionPane.showMessageDialog(null, "No such delivery!",
						      "ITEM DOES Not EXIST ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					resualtTxt.setText(d.toString());
				}
				
			}
			else {
				
				DeliveryArea area = MainController.getRest().getRealDeliveryArea(id);
				if(area == null) {
					JOptionPane.showMessageDialog(null, "No such area!",
						      "ITEM DOES Not EXIST ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					resualtTxt.setText(area.toString());
				}
			}
		}

    }

    @FXML
    void searchIdValidetion(KeyEvent event) {

    	boolean flag = false;
    	for(int i = 0; i < searchId.getText().length(); i++) {
    		if(searchId.getText().charAt(i) < '0' || searchId.getText().charAt(i) > '9') {
    			flag = true;
    			break;
    		}
    	}
    	
    	if(searchId.getText().isEmpty()) {
    		wrongIdSearchLbl.setText("Empty field!");
    	}
    	else if(flag){
    		wrongIdSearchLbl.setText("Only integers allowed!");
    	}
    	else {
    		wrongIdSearchLbl.setText("");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		searchType.setItems(typeList);
		searchType.setValue("customer");
		
	}

}
