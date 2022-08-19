package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

public class MangOrderManagmentOrderAddController implements Initializable{

    @FXML
    private ComboBox<Integer> custIdBx;

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
    private Button addOrderBtn;
    
    private ObservableList<String> alldishesList = FXCollections.observableArrayList();
    
    private ObservableList<String> selecteddishesList = FXCollections.observableArrayList();
    
    private  ObservableList<Integer> custsIDs = FXCollections.observableArrayList();
    
    private Collection<Dish> allDishes;
    
    private ArrayList<Dish> selectedDishesList;
    
    private Dish dish;
    
    @FXML
    private Label relevant;

    @FXML
    private Label notRelevant;
    
    private AssistingMethods aM;

    @FXML
    void addDish(ActionEvent event) {
    	
    	if(dishListView.getSelectionModel().getSelectedItem() != null && custIdBx.getValue() != null) {
    		dish = extractDish(dishListView.getSelectionModel().getSelectedItem());
    		if(relevant.isVisible()) {
    			selectedDishesList.add(dish);
    			String s = dish.getId() + " " + dish.getDishName();
    			 selecteddishesList.add(s);
    			 selectedDishes.getItems().add(s);
    		}
    		else {
    			try {
    				Customer cust = MainController.getRest().getRealCustomer(custIdBx.getValue());
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
    void addOrder(ActionEvent event) {

    	if(!selectedDishesList.isEmpty() && custIdBx.getValue() != null) {
    		Order order = new Order(MainController.getRest().getRealCustomer(custIdBx.getValue()), selectedDishesList, null);
    		if(MainController.getRest().getOrders().containsKey(order.getId())) {
    			ArrayList<Integer> arr = new ArrayList<Integer>(MainController.getRest().getOrders().keySet());
    			Collections.sort(arr);
    			order.setId(arr.get(arr.size()-1)+ 1);
    		}
    		MainController.getRest().addOrder(order);
    		JOptionPane.showMessageDialog(null, "Order " + order.getId() + " was added succesfully");
 			MainController.getRest().serialize();
 			
 			//initialize when creating a new order
 			selectedDishesList.removeAll(selectedDishesList);
    		selecteddishesList.removeAll(selecteddishesList);
    		selectedDishes.setItems(null);
    		custIdBx.setValue(null);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

    @FXML
    void removeDish(ActionEvent event) {
    	
    	if(selectedDishes.getSelectionModel().getSelectedItem() != null && custIdBx.getValue() != null) {
    		dish = extractDish(selectedDishes.getSelectionModel().getSelectedItem());
    		selectedDishesList.remove(dish);
    		String s = dish.getId() + " " + dish.getDishName();
    		selecteddishesList.remove(s);
    		selectedDishesList.remove(dish);
    		selectedDishes.setItems(selecteddishesList);
    		
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
		custIdBx.setItems(custsIDs);
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
	

    @FXML
    void showDetails(MouseEvent event) {

    	if(dishListView.getSelectionModel().getSelectedItem() != null && custIdBx.getValue() != null) {
    		//update data
    		dish = extractDish(dishListView.getSelectionModel().getSelectedItem());
    		String currectpath = null;
    		aM.showImage(dish.getDishPic(), currectpath, dishImg);
    		if(MainController.getRest().getReleventDishList(MainController.getRest().getRealCustomer(custIdBx.getValue())).contains(dish)) {
    			relevant.setVisible(true);
    			notRelevant.setVisible(false);
    		}
    		else {
    			relevant.setVisible(false);
    			notRelevant.setVisible(true);
    		}
    	}
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
	
    @FXML
	void initilaizeLists(ActionEvent event) {
    	//initialize when customer select changes
    	selectedDishesList.removeAll(selectedDishesList);
    	selecteddishesList.removeAll(selecteddishesList);
    	ObservableList<String> massage = FXCollections.observableArrayList();
    	massage.add("");
    	selectedDishes.setItems(massage);
	    }
}
