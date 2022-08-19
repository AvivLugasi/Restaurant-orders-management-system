package Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Model.Customer;
import Utils.AssistingMethods;
import Utils.ChangeScene;
import Utils.Gender;
import Utils.Neighberhood;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ClientProfileController implements Initializable{

    @FXML
    private TextField customerLName;

    @FXML
    private DatePicker customerBday;

    @FXML
    private ComboBox<Gender> customerGender;

    @FXML
    private RadioButton sensLactose;

    @FXML
    private RadioButton sensGluten;

    @FXML
    private ComboBox<Neighberhood> neighberhood;

    @FXML
    private Button editCustomerBtn;

    @FXML
    private TextField customerFName;

    @FXML
    private TextField custEmail;

    @FXML
    private TextField custUName;

    @FXML
    private TextField custPassword;

    @FXML
    private Label infoLbl;

    @FXML
    private ProgressBar lowpassbar;

    @FXML
    private ProgressBar mediumpassbar;

    @FXML
    private ProgressBar strongpassbar;

    @FXML
    private Button uploadImg;

    @FXML
    private ImageView choosenImg;

    @FXML
    private Label passstatusLbl;
    
    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);
    private ObservableList<Neighberhood> neighberhoodList = FXCollections.observableArrayList(Neighberhood.Neve_Shanan, Neighberhood.Kiriat_Haim, Neighberhood.DownTown, Neighberhood.Vardia, Neighberhood.Bat_Galim, Neighberhood.Merkaz_Karmel, Neighberhood.Denya, Neighberhood.Kiriat_Eliezer,
    		Neighberhood.Hadar, Neighberhood.Romema, Neighberhood.German_Colony, Neighberhood.Vadi_Nisnas, Neighberhood.Vadi_Saliv, Neighberhood.Neot_Peres, Neighberhood.Kababir, Neighberhood.Neve_David,
    		Neighberhood.Karmelia, Neighberhood.Halisa, Neighberhood.French_Karmel, Neighberhood.Ramat_Hanasi, Neighberhood.Neve_Yosef, Neighberhood.Ramat_Almogi);

    String imageName;
    
    @FXML
    private Label wrongP;
    
    @FXML
    private Label wrongUname;
    
    private String currectpath;
    
    private AssistingMethods aM;
    
    @FXML
    void editCustomer(ActionEvent event) {

    	if( !customerFName.getText().isEmpty() && !customerLName.getText().isEmpty() && customerBday.getValue() != null && !custEmail.getText().isEmpty() && !custUName.getText().isEmpty() && !custPassword.getText().isEmpty() && wrongP.getText().isEmpty() && wrongUname.getText().isEmpty()) {
			Customer c = MainController.getCust();
			c.setFirstName(customerFName.getText());
			c.setLastName(customerLName.getText());
			c.setBirthDay(customerBday.getValue());
			c.setGender(customerGender.getValue());
			c.setNeighberhood(neighberhood.getValue());
			c.setSensitiveToGluten(sensGluten.isSelected());
			c.setSensitiveToLactose(sensLactose.isSelected());
			c.setEmail(custEmail.getText());
			c.setCustPicName(imageName);
			c.setUserName(c.getId() + custUName.getText());
			c.setPassword(c.getId() + custPassword.getText());
			MainController.getRest().serialize();
			ChangeScene s = new ChangeScene();
			s.changeScene("/View/HomePageClientView.fxml", 4);
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid input",
  				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
		}
    }

    @FXML
    void setUNamestatus(KeyEvent event) {

    	boolean containInt = false;
    	
    	for(int i = 0; i < custUName.getText().length(); i++) {
    		if(custUName.getText().charAt(i) >= '0' && custUName.getText().charAt(i) <= '9') {
    			containInt = true;
    			break;
    		}
    	}
    	if(containInt) {
    		wrongUname.setText("Integers not allowed!");
    	}
    	else {
    		wrongUname.setText("");
    	}
    }

    @FXML
    void setpassstatus(KeyEvent event) {

    	int uppercaseCnt = 0;
    	int lowercaseCnt = 0;
    	int specialCnt = 0;
    	int intCnt = 0;
    	String massage = "";
    	String pass = custPassword.getText();
    	for(int i = 0; i < pass.length(); i++) {
    		if(pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z') {
    			uppercaseCnt++;
    		}
    		else if(pass.charAt(i) >= 'a' && pass.charAt(i) <= 'z') {
    			lowercaseCnt++;
    		}
    		else if(pass.charAt(i) == '!' || pass.charAt(i) == '@' || pass.charAt(i) == '#' || pass.charAt(i) == '$' || pass.charAt(i) == '%' || pass.charAt(i) == '^' || pass.charAt(i) == '&' || pass.charAt(i) == '*' || pass.charAt(i) == '(' || pass.charAt(i) == ')') {
    			specialCnt++;
    		}
    		else if(pass.charAt(i) >= '0' && pass.charAt(i) <= '9') {
    			intCnt++;
    		}
    	}
    	
    	if(uppercaseCnt == 0 || lowercaseCnt == 0 || specialCnt == 0 || intCnt > 0 || pass.length() < 8) {
    		if(pass.length() < 8) {
    			massage += "Atlist 8 characters ";
    		}
    		if(uppercaseCnt == 0)
    		massage += "missing uppercase ";
    		if(lowercaseCnt == 0) {
        		massage += "missing lowercase ";
        	}
    		if(specialCnt == 0) {
        		massage += "missing special case ";
        	}  
    		if(intCnt > 0) {
        		massage += "no integers allowed! ";
        	}
    	}
    	else {
    		massage = "";
    	}
    	if(custPassword.getText().isEmpty()) {
    		massage = "";
    	}
    	wrongP.setText(massage);
    	
    	if(wrongP.getText().isEmpty()) {
	    	if(custPassword.getText().length() >= 8 && custPassword.getText().length() <= 13) {
	    		lowpassbar.setVisible(true);
	    		mediumpassbar.setVisible(false);
	    		strongpassbar.setVisible(false);
	    		passstatusLbl.setText("Weak password");
	    	}
	    	else if(custPassword.getText().length() >= 14 && custPassword.getText().length() <= 16) {
	    		lowpassbar.setVisible(false);
	    		mediumpassbar.setVisible(true);
	    		strongpassbar.setVisible(false);
	    		passstatusLbl.setText("Medium password");
	    	}
	    	else if(custPassword.getText().length() >= 17 && uppercaseCnt >= 2 && lowercaseCnt >= 2 && specialCnt >= 2){
	    		mediumpassbar.setVisible(false);
	    		lowpassbar.setVisible(false);
	    		strongpassbar.setVisible(true);
	    		passstatusLbl.setText("Strong password");
	    	}
    	}
    	if(pass.isEmpty()) {
    		mediumpassbar.setVisible(false);
    		lowpassbar.setVisible(false);
    		strongpassbar.setVisible(false);
    		passstatusLbl.setText("");
    	}
    }

    @FXML
    void upload(ActionEvent event) {

    	imageName = aM.uploadImg(choosenImg, imageName);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Customer cust = MainController.getCust();
		customerFName.setText(cust.getFirstName());
		customerLName.setText(cust.getLastName());
		customerBday.setValue(cust.getBirthDay());
		customerGender.setValue(cust.getGender());
		custEmail.setText(cust.getEmail());
		custUName.setText(cust.getUserName().substring(1));
		custPassword.setText(cust.getPassword().substring(1));
		if(cust.isSensitiveToLactose()) {
			sensLactose.setSelected(true);
		}
		if(cust.isSensitiveToGluten()){
			sensGluten.setSelected(true);
		}
		neighberhood.setValue(cust.getNeighberhood());
		aM = new AssistingMethods();
		if(cust.getCustPicName() != null) {
			aM.showImage(cust.getCustPicName(), currectpath, choosenImg);
			imageName = cust.getCustPicName();
		}
		Tooltip passtooltip = new Tooltip();
    	Tooltip usertooltip = new Tooltip();
    	usertooltip.setText("no integers!");
		passtooltip.setText("Must contain at list 8 characters (1 special character, 1 upper case, 1 lower case), No integers!");
		infoLbl.setTooltip(passtooltip);
		custPassword.setTooltip(passtooltip);
		custUName.setTooltip(usertooltip);
		customerGender.setItems(genderList);
		customerGender.setValue(Gender.Male);
		neighberhood.setItems(neighberhoodList);
		neighberhood.setValue(Neighberhood.Neve_Shanan);
	}
	
	

}
