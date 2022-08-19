package Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Model.Component;
import Model.Dish;
import Utils.AssistingMethods;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MangOpsDishAddController implements Initializable{

	@FXML
    private AnchorPane addDishAPane;

	@FXML
    private TextField makeTime;

    @FXML
    private ComboBox<Utils.DishType> DishType;

    @FXML
    private Button addDishBtn;

    @FXML
    private TextField dishName;

    @FXML
    private ListView<String> compList;

    @FXML
    private Label inputError;

    @FXML
    private Button uploadImgBtn;

    @FXML
    private ImageView choosenImg;
    
    private ArrayList<Component> comps;
    
    private ObservableList<String> tempCompList = FXCollections.observableArrayList();
    
    private ObservableList<Utils.DishType> dishList = FXCollections.observableArrayList(Utils.DishType.Dessert, Utils.DishType.Main, Utils.DishType.Starter);

    private String imageName;
    
    private AssistingMethods aM;
    
    private int biggestID;
    
   private void addComps() {

    	ObservableList<String> chosenComps = compList.getSelectionModel().getSelectedItems();
    	comps = new ArrayList<Component>();
    	
    	for(int i = 0; i < chosenComps.size(); i++) {
    		String ID = "";
    		for(int j = 0; j < chosenComps.get(i).length(); j++) {
    			if(chosenComps.get(i).charAt(j) >= '0' && chosenComps.get(i).charAt(j) <= '9') {
    				ID += chosenComps.get(i).charAt(j);
    			}
    			else {
    				break;
    			}
    		}
    		Component comp = MainController.getRest().getRealComponent(Integer.parseInt(ID));
    		comps.add(comp);
 			}
    	}

   ///////remmber to add image////
    @FXML
    void addDish(ActionEvent event) {
    	
    	addComps();
    	if(inputError.getText().isEmpty() && !dishName.getText().isEmpty() && comps != null && !makeTime.getText().isEmpty()) {
    		Dish dish = new Dish (dishName.getText(), DishType.getValue(), comps, Integer.parseInt(makeTime.getText()), imageName);
    		if(MainController.getRest().getDishes().containsKey(dish.getId())) {
    			dish.setId(biggestID);
    			biggestID++;
    		}
    		MainController.getRest().addDish(dish);
    		JOptionPane.showMessageDialog(null, "Dish " + dish.getId() + " was added succesfully");
 			MainController.getRest().serialize();
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Invalid Input!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

    @FXML
    void inputValidetion(KeyEvent event) {

    	String price = makeTime.getText();
    	if(price.isEmpty()) {
    		inputError.setText("");
    		return;
    	}
    	if(price.contains(" ")) {
    		inputError.setText("Illeagal input, Contain white character(space)");
    		return;
    	}
    	for(int i=0; i<price.length();i++) {
    		 if((price.charAt(i) < '0' || price.charAt(i) > '9') ) {
    			inputError.setText("Illeagal input, price must contains only Integer");
    			break;
    		}
    		else {
    			inputError.setText("");
   
    		}
    		
    	}
    }

    @FXML
    void ulpoadImg(ActionEvent event) {
    	
    	imageName = aM.uploadImg(choosenImg, imageName);
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String s = "";
		Set<Integer> compIDs = MainController.getRest().getComponenets().keySet();
		if(!compIDs.isEmpty()) {
			for(Integer id: compIDs) {
				s += id + " " + MainController.getRest().getRealComponent(id).getComponentName();
				tempCompList.add(s);
				s = "";
			}
			compList.setItems(tempCompList);;
			compList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}
		DishType.setItems(dishList);
		DishType.setValue(Utils.DishType.Starter);		
		aM = new AssistingMethods();
		biggestID = aM.getBiggestID(MainController.getRest().getDishes().keySet()) + 1;
	}
	

}
