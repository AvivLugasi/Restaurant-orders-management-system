package Controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Model.Component;
import Model.Dish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemController {

    @FXML
    private Label itemDiscription;

    @FXML
    private ImageView itemPic;

    @FXML
    private Label itemPrice;
    
    private Dish d;
    
    private ItemListener itemListener;
    
    @FXML
    private void click(MouseEvent mouseEvent) {
    	
    	itemListener.onDishClicked(d);
    }

    public void setData(Dish d, ItemListener itemListener) {
    	this.d = d;
    	this.itemListener = itemListener;
    	itemPrice.setText(String.format("%.2f", this.d.getPrice()));
    	List<Component> allDishComps = this.d.getComponenets();
    	String comps = "";
    	for(Component c: allDishComps) {
    		comps += c.getComponentName() + " ";
    	}
    	itemDiscription.setText(this.d.getDishName() + "\n" + "Contains: " + comps);
    	setImage(this.d.getDishPic(), this.d);
    }
    
    private void setImage(String imageName, Dish dish) {
    	imageName = dish.getDishPic();
		String reletivePath = "/media/" + dish.getDishPic() + ".png";
    	String projectPath = System.getProperty("user.dir");
    	String currectpath = projectPath.replace('\\', '/');
    	currectpath += reletivePath;
    	File f = new File(currectpath);
		Image original = new Image(f.toURI().toString());
		itemPic.setImage(original);
		itemPic.setVisible(true);
    }
}