package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import Model.Dish;
import Utils.AssistingMethods;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MangOpsDishViewAllController implements Initializable{

    @FXML
    private TextArea viewResualt;

    @FXML
    private ListView<String> list;

    @FXML
    private ImageView dishImage;
    
    private ObservableList<String> dishList = FXCollections.observableArrayList();
    
    private String currectpath;
    
    private AssistingMethods aM;

    @FXML
    void showDetails(MouseEvent event) {

    	String s = list.getSelectionModel().getSelectedItem();
    	if(s != null) {
    		String ID = "";
    		for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
    		Dish dish = MainController.getRest().getRealDish(Integer.parseInt(ID));
			viewResualt.setText(dish.toString());
			aM.showImage(dish.getDishPic(), currectpath, dishImage);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		String s = "";
		String ID = "";
		Set<Integer> dishesIDs = MainController.getRest().getDishes().keySet();
		if(!dishesIDs.isEmpty()) {
			for(Integer id: dishesIDs) {
				s += id + " " + MainController.getRest().getRealDish(id).getDishName();
				dishList.add(s);
				s = "";
			}
			list.setItems(dishList);
			list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			list.getSelectionModel().select(dishList.get(0));
			s = dishList.get(0);
			
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
			Dish dish = MainController.getRest().getRealDish(Integer.parseInt(ID));
			viewResualt.setText(dish.toString());
			String reletivePath = "/media/" + dish.getDishPic() + ".png";
	    	String projectPath = System.getProperty("user.dir");
	    	currectpath = projectPath.replace('\\', '/');
	    	currectpath += reletivePath;
			File dishImg = new File(currectpath);
			Image original = new Image(dishImg.toURI().toString());
    		dishImage.setImage(original);
    		dishImage.setVisible(true);
		}
		
		aM = new AssistingMethods();	
	}

}
