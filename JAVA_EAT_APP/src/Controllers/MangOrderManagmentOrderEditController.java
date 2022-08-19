package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.SensitiveException;
import Model.Customer;
import Model.Dish;
import Model.Order;
import Utils.AssistingMethods;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MangOrderManagmentOrderEditController implements Initializable{

    @FXML
    private ComboBox<Integer> ordersIdBx;

    @FXML
    private ListView<String> dishListView;

    @FXML
    private ImageView dishImg;

    @FXML
    private Button addDishBtn;

    @FXML
    private Button removeDishBtn;

    @FXML
    private ListView<String> selectedDishes;

    @FXML
    private Button editOrderBtn;


    @FXML
    private Label relevant;

    @FXML
    private Label notRelevant;

    @FXML
    private ComboBox<Integer> custsIdBx;
    
    private ObservableList<String> alldishesList = FXCollections.observableArrayList();
    
    private ObservableList<String> selecteddishesList = FXCollections.observableArrayList();
    
    private  ObservableList<Integer> custsIDs = FXCollections.observableArrayList();
    
    private  ObservableList<Integer> ordersIDs = FXCollections.observableArrayList();
    
    private Collection<Dish> allDishes;
    
    private ArrayList<Dish> selectedDishesList;
    
    private Dish dish;
    
    private AssistingMethods aM;

    @FXML
    void addDish(ActionEvent event) {

    	if(dishListView.getSelectionModel().getSelectedItem() != null && custsIdBx.getValue() != null && ordersIdBx.getValue() != null) {
    		dish = extractDish(dishListView.getSelectionModel().getSelectedItem());
    		if(relevant.isVisible()) {
    			selectedDishesList.add(dish);
    			String s = dish.getId() + " " + dish.getDishName();
    			 selecteddishesList.add(s);
    		}
    		else {
    			try {
    				Customer cust = MainController.getRest().getRealCustomer(custsIdBx.getValue());
    				throw new SensitiveException(cust.getFirstName() + " " + cust.getLastName(), dish.getDishName());
    			}
    			catch(SensitiveException e) {
    				JOptionPane.showMessageDialog(null, e.getMessage(),
    					      "ILEAGAL ACTION", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    	}
    }

    @FXML
    void editOrder(ActionEvent event) {

    	if(!selectedDishesList.isEmpty() && custsIdBx.getValue() != null && ordersIdBx.getValue() != null) {
    		Order order = new Order(MainController.getRest().getRealCustomer(custsIdBx.getValue()), selectedDishesList, null);
    		Order old = MainController.getRest().getRealOrder(ordersIdBx.getValue());
    		order.setId(old.getId());
    		if(MainController.getRest().getReleventDishList(MainController.getRest().getRealCustomer(custsIdBx.getValue())).containsAll(order.getDishes())) {
	    		MainController.getRest().removeOrder(old);
	    		if(MainController.getRest().addOrder(order)) {
		    		JOptionPane.showMessageDialog(null, "Order " + order.getId() + " was edited succesfully");
			 			MainController.getRest().serialize();
		 			
		 			//initialize after editing an order
		 			selectedDishesList.removeAll(selectedDishesList);
		    		selecteddishesList.removeAll(selecteddishesList);
		    		selectedDishes.setItems(null);
		    		ordersIdBx.setValue(null);
	    		}
	    	}
    		else {
    			JOptionPane.showMessageDialog(null, "Customer is sensitive to 1 or more components from the order!",
  				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

    @FXML
    void initilaizeLists(ActionEvent event) {

    	//initialize when order select changes
    	selectedDishesList.removeAll(selectedDishesList);
    	selecteddishesList.removeAll(selecteddishesList);
    	ObservableList<String> massage = FXCollections.observableArrayList();
    	massage.add("");
    	selectedDishes.setItems(massage);
    	if(ordersIdBx.getValue() != null) {
    		Order order = MainController.getRest().getRealOrder(ordersIdBx.getValue());

    		custsIdBx.setValue(order.getCustomer().getId());
    		selectedDishesList = new ArrayList<Dish>(order.getDishes());
    		String s = "";
    		if(!selectedDishesList.isEmpty()) {
    			for(Dish d: selectedDishesList) {
    				s += d.getId() + " " + d.getDishName();
    				selecteddishesList.add(s);
    				s = "";
    			}
    			selectedDishes.setItems(selecteddishesList);
    			
    		}
    		
    	}
    }

    @FXML
    void removeDish(ActionEvent event) {

    	if(selectedDishes.getSelectionModel().getSelectedItem() != null && custsIdBx.getValue() != null && ordersIdBx.getValue() != null) {
    		dish = extractDish(selectedDishes.getSelectionModel().getSelectedItem());
    		selectedDishesList.remove(dish);
    		String s = dish.getId() + " " + dish.getDishName();
    		selecteddishesList.remove(s);
    		selectedDishesList.remove(dish);
    		selectedDishes.setItems(selecteddishesList);
    		
    		}
    }

    @FXML
    void showDetails(MouseEvent event) {

    	if(dishListView.getSelectionModel().getSelectedItem() != null && custsIdBx.getValue() != null && ordersIdBx.getValue() != null) {
    		//update data
    		dish = extractDish(dishListView.getSelectionModel().getSelectedItem());
    		String currectpath = null;
    		aM.showImage(dish.getDishPic(), currectpath, dishImg);
    		if(MainController.getRest().getReleventDishList(MainController.getRest().getRealCustomer(custsIdBx.getValue())).contains(dish)) {
    			relevant.setVisible(true);
    			notRelevant.setVisible(false);
    		}
    		else {
    			relevant.setVisible(false);
    			notRelevant.setVisible(true);
    		}
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		selectedDishesList = new ArrayList<Dish>();
		Collection<Customer> allcusts = MainController.getRest().getCustomers().values();
		for(Customer c:  allcusts) {
			if(!MainController.getRest().getBlackList().contains(c)) {
				custsIDs.add(c.getId());
			}
		}
		custsIdBx.setItems(custsIDs);
		Collection<Order> allOrders = (MainController.getRest().getOrders().values());
		for(Order o:  allOrders) {
			if(o.getDelivery() == null || (o.getDelivery()!= null && o.getDelivery().isDelivered() == false)) {
				ordersIDs.add(o.getId());
			}
		}
		ordersIdBx.setItems(ordersIDs);
		allDishes = MainController.getRest().getDishes().values();
		String s = "";
		if(!allDishes.isEmpty()) {
			for(Dish d: allDishes) {
				s += d.getId() + " " + d.getDishName();
				alldishesList.add(s);
				s = "";
			}
		}
		dishListView.setItems(alldishesList);
		dishListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);	
		aM = new AssistingMethods();
	}
	
	private Dish extractDish(String name) {
    	
    	String s = "";
    	String id = "";
    	if(name != null) {
    		s = name;
    		for(int i = 0; i < s.length(); i++) {
    			if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
    				id += s.charAt(i);
    			}
    			else {
    				break;
    			}
    		}
    		
    		dish = MainController.getRest().getRealDish(Integer.parseInt(id));
    		return dish;
    	}
    	return null;
    }

}
