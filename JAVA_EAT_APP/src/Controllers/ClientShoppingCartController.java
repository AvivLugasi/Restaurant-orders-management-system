package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Exceptions.SensitiveException;
import Model.Customer;
import Model.Dish;
import Model.Order;
import Utils.AssistingMethods;
import Utils.DishType;
import Utils.Neighberhood;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ClientShoppingCartController implements Initializable{

    @FXML
    private Label DishFromCart;

    @FXML
    private Label fromCartPrice;

    @FXML
    private ImageView removeBtn;

    @FXML
    private Label totalCost;

    @FXML
    private Button OrderBtn;

    @FXML
    private TextField dishCartAmount;

    @FXML
    private Button addtoRemoveBtn;

    @FXML
    private Button removefromRemoveBtn;

    @FXML
    private ListView<String> cartList;

    @FXML
    private Label selectedName;

    @FXML
    private Label selectedPrice;

    @FXML
    private ImageView addBtn;

    @FXML
    private ImageView selectedDish;

    @FXML
    private Label relevantLbl;

    @FXML
    private Label notrelevantLbl;

    @FXML
    private Label totalSelectedCost;

    @FXML
    private TextField dishSelectedAmount;

    @FXML
    private Button addItemBtn;

    @FXML
    private Button removeItemBtn;

    @FXML
    private ImageView dessertsIcn;

    @FXML
    private ImageView starterIcn;

    @FXML
    private ImageView mainsIcn;

    @FXML
    private Button dessertBtn;

    @FXML
    private Button mainBtn;

    @FXML
    private Button startersBtn;
    
    @FXML
    private ScrollPane scrollPane;
   
    @FXML
    private GridPane itemsGPane;
    
    private ArrayList<Dish> starters;
    
    private ArrayList<Dish> mains;
    
    private ArrayList<Dish> desserts;
    
    private ArrayList<Dish> selectedDishes;
    
    private HashMap<String, Integer> dishesAndAmounts;
    
    private Dish selectedDishToAdd;
    
    private Dish selectedDishFromCart;    
    
    private ObservableList<String> selectedDishesNames = FXCollections.observableArrayList();
    
    private int maxAmount;
    
    @FXML
    private Pane dessPane;
   
    @FXML
    private Pane mainsPane;

    @FXML
    private ScrollPane scrollPane1;

    @FXML
    private GridPane itemsGPane1;

    @FXML
    private Pane startersPane;

    @FXML
    private ScrollPane scrollPane2;

    @FXML
    private GridPane itemsGPane2;
    
    private ItemListener itemListener;
    
    private AssistingMethods aM;
    
    private int biggestID;

    @FXML
    void addItem(ActionEvent event) {

    	addtoAmount(dishSelectedAmount);
    }

    @FXML
    void addOrder(ActionEvent event) {

		if(!selectedDishes.isEmpty()) {
			Order order = new Order(MainController.getCust(), selectedDishes, null);
			if(MainController.getRest().getOrders().containsKey(order.getId())) {
    			order.setId(biggestID);
    			biggestID++;
    		}
    		MainController.getRest().addOrder(order);
    		JOptionPane.showMessageDialog(null, "Order " + order.getId() + " was added succesfully");
 			MainController.getRest().serialize();
 			selectedDishesNames.clear();
 			cartList.setItems(null);
 			selectedDishes = new ArrayList<Dish>();
 			dishesAndAmounts.clear();
		}
    }

    @FXML
    void addtoRemove(ActionEvent event) {

    	addtoAmount(dishCartAmount);
    }

    @FXML
    void removeDish(MouseEvent event) {

    	if(selectedDishFromCart != null) {
	    	int count = Integer.parseInt(dishCartAmount.getText());
	    	if(count == maxAmount) {
	    		dishesAndAmounts.remove(selectedDishFromCart.getDishName(), maxAmount);
	    		selectedDishesNames.remove("ID: " + selectedDishFromCart.getId() + " " + selectedDishFromCart.getDishName());
	    	}
	    	else {
	    		Integer a = dishesAndAmounts.get(selectedDishFromCart.getDishName());
	    		if(a!= null) {
		    		a -= count;
		    		dishesAndAmounts.put(selectedDishFromCart.getDishName(), a);
		    		maxAmount = a;
	    		}
	    	}
	    	double total = Double.parseDouble(totalCost.getText());
	    	total -= count*selectedDishFromCart.getPrice();
	    	totalCost.setText(String.format("%.2f", total));
	    	for(int i = 0; i < selectedDishes.size(); i++) {
	    		if(selectedDishes.get(i).equals(selectedDishFromCart)) {
	    			selectedDishes.remove(i);
	    			count--;
	    		}
	    		if(count == 0) {
	    			break;
	    		}
	    	}    	
    	}
    	JOptionPane.showMessageDialog(null, "Dish " + selectedDishFromCart.getDishName() + " was removed succesfully");
	}

    @FXML
    void removeItem(ActionEvent event) {

    	removeFromAmount(dishSelectedAmount);
    }

   @FXML
    void removefromRemove(ActionEvent event) {

	   removeFromAmount(dishCartAmount);
    }

    @FXML
    void showInfoFromCart(MouseEvent event) {

    	String s = cartList.getSelectionModel().getSelectedItem();
    	String ID = "";
    	if(s != null) {
    		for(int i = 4; i < s.length(); i++) {
    			if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
    				ID += s.charAt(i);
    			}
    			else {
    				break;
    			}
    		}
    		int id = Integer.parseInt(ID);
    		selectedDishFromCart = MainController.getRest().getRealDish(id);
    		if(selectedDishFromCart != null) {
    			DishFromCart.setText(selectedDishFromCart.getDishName());
    			fromCartPrice.setText(String.format("%.2f", selectedDishFromCart.getPrice()));
    			maxAmount = dishesAndAmounts.get(selectedDishFromCart.getDishName());
    			dishCartAmount.setText(maxAmount + "");
    		}
    	}
    }
    

    @FXML
    void showMain(ActionEvent event) {
    	
    	startersPane.setVisible(false);
    	dessPane.setVisible(false);
    	mainsPane.setVisible(true);
	}

    @FXML
    void showMainsIcn(MouseEvent event) {
    
    	startersPane.setVisible(false);
    	dessPane.setVisible(false);
    	mainsPane.setVisible(true);
    }

    @FXML
    void showStarters(ActionEvent event) {

    	
    	mainsPane.setVisible(false);
    	dessPane.setVisible(false);
    	startersPane.setVisible(true);
    }

    @FXML
    void showStartersIcn(MouseEvent event) {

    
    	mainsPane.setVisible(false);
    	dessPane.setVisible(false);
    	startersPane.setVisible(true);
    }
    
    @FXML
    void showDesserts(ActionEvent event) {

    	mainsPane.setVisible(false);
    	startersPane.setVisible(false);
    	dessPane.setVisible(true);
    }

    @FXML
    void showDessertsIcn(MouseEvent event) {

    	mainsPane.setVisible(false);
    	startersPane.setVisible(false);
    	dessPane.setVisible(true);
    }
    
    @FXML
    void addDish(MouseEvent event) {
    	if(relevantLbl.isVisible()) {
    		if(!dishSelectedAmount.getText().equals("0")) {
		    	for(int i = 0; i < Integer.parseInt(dishSelectedAmount.getText()); i++) {
		    		selectedDishes.add(selectedDishToAdd);
		    	}
		    	Integer amount = dishesAndAmounts.get(selectedDishToAdd.getDishName());
				if(amount == null) {
					amount = Integer.parseInt(dishSelectedAmount.getText());
				}
				else {
					amount += Integer.parseInt(dishSelectedAmount.getText());
				}
				
				dishesAndAmounts.put(selectedDishToAdd.getDishName(), amount);
				if(!selectedDishesNames.contains("ID: " + selectedDishToAdd.getId() +  " " + selectedDishToAdd.getDishName())){
					selectedDishesNames.add("ID: " + selectedDishToAdd.getId() + " " + selectedDishToAdd.getDishName());
					cartList.setItems(selectedDishesNames);
				}
				Double total;
				if(totalCost.getText().isEmpty()) {
					total =  selectedDishToAdd.getPrice()*Integer.parseInt(dishSelectedAmount.getText());
				}
				else {
					total = Double.parseDouble(totalCost.getText()) + selectedDishToAdd.getPrice()*Integer.parseInt(dishSelectedAmount.getText());
				}
				totalCost.setText(String.format("%.2f", total));
    		}
    		JOptionPane.showMessageDialog(null, "Dish " + selectedDishToAdd.getDishName() + " was added succesfully");
		}
    	else if(selectedDishToAdd != null){
    		try {
				Customer cust = MainController.getCust();
				throw new SensitiveException(cust.getFirstName() + " " + cust.getLastName(), selectedDishToAdd.getDishName());
			}
			catch(SensitiveException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),
					      "ILEAGAL ACTION", JOptionPane.ERROR_MESSAGE);
			}
    	}
    }
    
    private void addtoAmount(TextField t) {
    	
    	int amount = Integer.parseInt(t.getText());
    	if(t.equals(dishCartAmount) && amount < maxAmount) {
    		amount++;
		}
    	else {
    		amount++;
    	}
    	if(t.equals(dishSelectedAmount)) {
    		Double total;
			if(totalSelectedCost.getText().isEmpty()) {
				total =  selectedDishToAdd.getPrice()*Integer.parseInt(dishSelectedAmount.getText());
			}
			else {
				total = Double.parseDouble(totalSelectedCost.getText()) + selectedDishToAdd.getPrice();
			totalSelectedCost.setText(String.format("%.2f", total));
			}
			t.setText(amount + "");
    	}
    }
    
    private void removeFromAmount(TextField t) {
    	
    	int amount = Integer.parseInt(t.getText());
    	if(amount > 0) {
    		amount--;
    	}
    	if(t.equals(dishSelectedAmount)) {
    		Double total;
			if(totalSelectedCost.getText().isEmpty()) {
				total =  selectedDishToAdd.getPrice()*Integer.parseInt(dishSelectedAmount.getText());
			}
			else if(!totalSelectedCost.getText().equals("0.00")) {
				total = Double.parseDouble(totalSelectedCost.getText()) - selectedDishToAdd.getPrice();
			totalSelectedCost.setText(String.format("%.2f", total));
			}
			t.setText(amount + "");
    	}
    	t.setText(amount + "");
    }
    
    private void setItemsPane(ArrayList<Dish> dishesOfSameType, GridPane itemsGPane) {
    	
    	int row = 0;
    	try {
	    	for(int i = 0; i < dishesOfSameType.size(); i++) {
	    		FXMLLoader fxmlLoader = new FXMLLoader();
	    		fxmlLoader.setLocation(getClass().getResource("/View/ItemView.fxml"));
	    		AnchorPane pane = fxmlLoader.load();
				ItemController itemsCont = fxmlLoader.getController();
				itemsCont.setData(dishesOfSameType.get(i),itemListener);
				itemsGPane.add(pane, 1, row++);
				GridPane.setMargin(pane, new Insets(10));
			}
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    private void relevantCheck(Dish d) {
    	Collection<Dish> relevants = MainController.getRest().getReleventDishList(MainController.getCust());
    	if(relevants.contains(d)) {
    		relevantLbl.setVisible(true);
    		notrelevantLbl.setVisible(false);
    	}
    	else {
    		relevantLbl.setVisible(false);
    		notrelevantLbl.setVisible(true);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		starters = new ArrayList<Dish>();
		mains = new ArrayList<Dish>();
		desserts = new ArrayList<Dish>();
		selectedDishes = new ArrayList<Dish>();
		dishesAndAmounts = new HashMap<String, Integer>();
		for(Dish d: MainController.getRest().getDishes().values()) {
			if(d.getType().equals(DishType.Starter)) {
				starters.add(d);
			}
			else if(d.getType().equals(DishType.Main)){
				mains.add(d);
			}
			else
				desserts.add(d);
		}
		dishSelectedAmount.setText("1");
		dishCartAmount.setText("1");
		
		dessPane.setVisible(false);
		startersPane.setVisible(false);
		mainsPane.setVisible(false);
		
		itemListener = new ItemListener() {

			@Override
			public void onDishClicked(Dish dish) {
				// TODO Auto-generated method stub
				selectedDishToAdd = dish;
				setImagePic(dish.getDishPic());
				relevantCheck(dish);
				selectedName.setText(dish.getDishName());
				selectedPrice.setText(String.format("%.2f", dish.getPrice()));
				totalSelectedCost.setText(selectedPrice.getText());
				dishSelectedAmount.setText("1");
				
			}
			
		};
		if(!desserts.isEmpty())
    		setItemsPane(desserts, itemsGPane);
		if(!starters.isEmpty())
    		setItemsPane(starters, itemsGPane2);
		if(!mains.isEmpty())
    		setItemsPane(mains, itemsGPane1);
		aM = new AssistingMethods();
		biggestID = aM.getBiggestID(MainController.getRest().getOrders().keySet()) + 1;
	}

	private void setImagePic(String path) {
		String imageName = path;
		String reletivePath = "/media/" + imageName + ".png";
    	String projectPath = System.getProperty("user.dir");
    	String currectpath = projectPath.replace('\\', '/');
    	currectpath += reletivePath;
    	File f = new File(currectpath);
		Image original = new Image(f.toURI().toString());
		selectedDish.setImage(original);
		selectedDish.setVisible(true);
	}
}
