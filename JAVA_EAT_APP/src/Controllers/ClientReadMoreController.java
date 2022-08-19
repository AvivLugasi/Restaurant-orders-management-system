package Controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.Component;
import Model.Cook;
import Utils.AssistingMethods;
import Utils.Expertise;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class ClientReadMoreController implements Initializable{

    @FXML
    private Button aboutBtn;

    @FXML
    private Button galleryBtn;

    @FXML
    private Button contactUsBtn;

    @FXML
    private ImageView contactUsIcn;

    @FXML
    private ImageView galleryIcn;

    @FXML
    private ImageView aboutIcn;

    @FXML
    private Button showMapBtn;

    @FXML
    private ImageView showMapIcn;

    @FXML
    private Pane aboutPane;

    @FXML
    private Label discriptionLbl;
    
    @FXML
    private Label compsList;
    
    @FXML
    private ListView<String> chefesList;

    @FXML
    private Button allCooks;

    @FXML
    private ComboBox<Expertise> expBx;
    
    @FXML
    private WebView googleMaps;
    
    @FXML
    private Pane contactPane;

    @FXML
    private PasswordField emailPass;

    @FXML
    private TextField title;

    @FXML
    private TextField massage;

    @FXML
    private Button sendBtn;

    @FXML
    private Pane galleryPane;

    @FXML
    private Button perv;

    @FXML
    private Button next;

    @FXML
    private Label imgNum;

    @FXML
    private ImageView selectedImg;

    
    private ObservableList<Expertise> expList = FXCollections.observableArrayList(Expertise.American,Expertise.Asian,Expertise.French, Expertise.Indian, Expertise.Italien, Expertise.Mediterranean);

    private ObservableList<String> cooks = FXCollections.observableArrayList();
    
    private String URL;
    
    private WebEngine engine ;
    
    private ArrayList<String> picsNames = new ArrayList<String>();
    
    private int counter = 0;
    
    private AssistingMethods aM;
    
    @FXML
    void all(ActionEvent event) {

    	cooks.clear();
    	chefesList.setItems(null);
    	String s = "";
		for(Cook c: MainController.getRest().getCooks().values()) {
			s = c.getFirstName() + " " + c.getLastName();
			cooks.add(s);
			s = "";
		}
		chefesList.setItems(cooks);
    }
    
    @FXML
    void showByExp(ActionEvent event) {

    	if(expBx.getValue() != null) {
	    	cooks.clear();
	    	chefesList.setItems(null);
	    	ArrayList<Cook> cookByExps = MainController.getRest().GetCooksByExpertise(expBx.getValue());
	    	String s = "";
			for(Cook c: cookByExps) {
				s = c.getFirstName() + " " + c.getLastName();
				cooks.add(s);
				s = "";
			}
			chefesList.setItems(cooks);
    	}
    }
    
    @FXML
    void aboutMoveTo(MouseEvent event) {

    	galleryPane.setVisible(false);
    	contactPane.setVisible(false);
    	googleMaps.setVisible(false);
    	aboutPane.setVisible(true);
    }

    @FXML
    void contactUsMoveTo(MouseEvent event) {

    	galleryPane.setVisible(false);
    	contactPane.setVisible(true);
    	googleMaps.setVisible(false);
    	aboutPane.setVisible(false);
    }

    @FXML
    void galleryMoveTo(MouseEvent event) {

    	galleryPane.setVisible(true);
    	contactPane.setVisible(false);
    	googleMaps.setVisible(false);
    	aboutPane.setVisible(false);
    	showImage("restpic1");
    	imgNum.setText("1");
    	counter = 0;
    }

    @FXML
    void openMap(MouseEvent event) {

    	galleryPane.setVisible(false);
    	contactPane.setVisible(false);
    	aboutPane.setVisible(false);
    	engine.load(URL.toString().trim());
    	googleMaps.setVisible(true);
    }

    @FXML
    void showAbout(ActionEvent event) {

    	galleryPane.setVisible(false);
    	contactPane.setVisible(false);
    	googleMaps.setVisible(false);
    	aboutPane.setVisible(true);
    	
    }

    @FXML
    void showContactUs(ActionEvent event) {

    	galleryPane.setVisible(false);
    	contactPane.setVisible(true);
    	googleMaps.setVisible(false);
    	aboutPane.setVisible(false);
    }

    @FXML
    void showGallery(ActionEvent event) {

    	galleryPane.setVisible(true);
    	contactPane.setVisible(false);
    	googleMaps.setVisible(false);
    	aboutPane.setVisible(false);
    	showImage("restpic1");
    	imgNum.setText("1");
    	counter = 0;
    }

    @FXML
    void showMap(ActionEvent event) {

    	galleryPane.setVisible(false);
    	contactPane.setVisible(false);
    	aboutPane.setVisible(false);
    	engine.load(URL.toString().trim());
    	googleMaps.setVisible(true);
    }
    
    @FXML
    void send(ActionEvent event) {

			String uPass = emailPass.getText();
			String title = this.title.getText();
			String massage = this.massage.getText();
			if(uPass.isEmpty() || title.isEmpty() || massage.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Empty fields!",
					      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else {
				try {
					
					Utils.SendMail.send("javaeat136@gmail.com", massage, title, MainController.getCust().getEmail(), uPass);
					JOptionPane.showMessageDialog(null, "Thank you, your massage was sent successfuly, and we will reply as soon as possible :)");
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Invalid password",
						      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
					System.out.println(e.getMessage());
				}
			}
    }
    
    @FXML
    void goNext(ActionEvent event) {

    	counter++;
    	try {
    		String name = picsNames.get(counter);
    		showImage(name);
    		int num = Integer.parseInt(imgNum.getText());
        	imgNum.setText(num + 1 + "");
    	}catch(IndexOutOfBoundsException e) {
    		counter = picsNames.size() - 1;
    	}
    }

    @FXML
    void goPerv(ActionEvent event) {

    	counter--;
    	try {
    		String name = picsNames.get(counter);
    		showImage(name);
    		int num = Integer.parseInt(imgNum.getText());
        	imgNum.setText(num - 1 + "");
    	}catch(IndexOutOfBoundsException e) {
    		counter = 0;
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		discriptionLbl.setText(setDiscription());
		LinkedList<Component> compsByPup = MainController.getRest().getPopularComponents();
		compsList.setText(setCompsList(compsByPup));
		expBx.setItems(expList);
		expBx.setValue(null);
		String s = "";
		for(Cook c: MainController.getRest().getCooks().values()) {
			s = c.getFirstName() + " " + c.getLastName();
			cooks.add(s);
			s = "";
		}
		chefesList.setItems(cooks);
		URL = "https://goo.gl/maps/QYY8jiyd1eGB4rcH7";
		engine = googleMaps.getEngine();
		aM = new AssistingMethods();
		setPics();
	}

	private void setPics() {
		
		picsNames.add("restpic1");picsNames.add("restpic2");picsNames.add("restpic3");
		picsNames.add("restpic4");picsNames.add("restpic5");picsNames.add("rest6");
		picsNames.add("rest7");picsNames.add("rest8");
	}
	
	private void showImage(String imageName) {

		String currectpath = null;
		aM.showGalleryImage(imageName, currectpath, selectedImg);
	}

	private String setDiscription() {
		
		String text = "In the heart of German Colony Lies an unique restaurant, which combined a vriant of different  cuisines.\n"
				+ "Each  cuisine has an  extraordinary amount of flavors, spices and dishes, each one tells a story about people ,culture dreams\n and hopes that were developed during houndreds years. "
				+ "We have Sushi from Japan, BBQ from America, pasta from Italy\n and many more world wide dishes. "
				+ "We use only the best freshest ingredients.\n"
				+ "We hire experts Chefs that learned the cuisine in thier homeland for many years!\n"
				+ "We will give you an unforgetble Culinary experience.\n"
				+ "\n"
				+ "Our cusines: Italien, Mediterranean, American, French, Indian, Asian.";
		return text;
	}
	private String setCompsList(LinkedList<Component> compsByPup) {
		
		String list = "";
		int place = 1;
		for(Component comp: compsByPup) {
			list += place + ") " + comp.getComponentName() + ".\n";
			place++;
		}
		
		return list;
		
	}
}
