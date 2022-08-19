package Controllers;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class SignUpController implements Initializable{

	 @FXML
	    private AnchorPane signupPane;//sign up pane

	    @FXML
	    private ImageView signUpImg;//background

	    @FXML
	    private TextField custFName;//customer first name field

	    @FXML
	    private TextField custLName;//customer last name field

	    @FXML
	    private Label sntsLbl;//sensitive label

	    @FXML
	    private Label lactLbl;//lactose label

	    @FXML
	    private Label gluLbl;//gluten label

	    @FXML
	    private RadioButton sensRBtn;//sensitive radio button

	    @FXML
	    private RadioButton lactRBtn;// lactose radio button

	    @FXML
	    private RadioButton gluRBtn;// gluten radio button

	    @FXML
	    private TextField custUName;//customer user name field

	    @FXML
	    private TextField custPassword;//customer password field

	    @FXML
	    private ComboBox<Gender> custGnd;// customer gender combo box
	   
	    @FXML
	    private Label infoLbl;// label for activating tool tip for password
	    
	    //password strength status bars
	    @FXML
	    private ProgressBar lowpassbar;

	    @FXML
	    private ProgressBar mediumpassbar;

	    @FXML
	    private ProgressBar strongpassbar;
	    
	    @FXML
	    private Label passstatusLbl;//password strength status label
	    

	    @FXML
	    private ImageView back;//back to login page button

	    @FXML
	    private ComboBox<Neighberhood> custneighberhood;//customer neighbergoods combo box  
	    
	    //lists of neighborhoods and genders
	    private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.Male, Gender.Female, Gender.Unknown);
	    private ObservableList<Neighberhood> neighberhoodList = FXCollections.observableArrayList(Neighberhood.Neve_Shanan, Neighberhood.Kiriat_Haim, Neighberhood.DownTown, Neighberhood.Vardia, Neighberhood.Bat_Galim, Neighberhood.Merkaz_Karmel, Neighberhood.Denya, Neighberhood.Kiriat_Eliezer,
	    		Neighberhood.Hadar, Neighberhood.Romema, Neighberhood.German_Colony, Neighberhood.Vadi_Nisnas, Neighberhood.Vadi_Saliv, Neighberhood.Neot_Peres, Neighberhood.Kababir, Neighberhood.Neve_David,
	    		Neighberhood.Karmelia, Neighberhood.Halisa, Neighberhood.French_Karmel, Neighberhood.Ramat_Hanasi, Neighberhood.Neve_Yosef, Neighberhood.Ramat_Almogi);
	    
	    @FXML
	    private DatePicker custBday;//customer birth day picker
	    
	    @FXML
	    private Button uploadImg;

	    @FXML
	    private ImageView choosenImg;
	    
	    private String imageName;
	    
	    @FXML
	    private TextField emailFld;

	    private String custEmail;
	   //input error labels
	    @FXML
	    private Label WrongFname;

	    @FXML
	    private Label WrongLname;

	    @FXML
	    private Label wrongBday;

	    @FXML
	    private Label wrongUname;

	    @FXML
	    private Label wrongP;

	    @FXML
	    private Label emptyP;
	    
	    @FXML
	    private Button signUpBtn;//sign up button
	    
	    private AssistingMethods aM;
	    
	    private int biggestID;	    
	   //methods
	    
	   @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			Tooltip passtooltip = new Tooltip();
	    	Tooltip usertooltip = new Tooltip();
	    	usertooltip.setText("no integers!");
			passtooltip.setText("Must contain at list 8 characters (1 special character, 1 upper case, 1 lower case), No integers!");
			infoLbl.setTooltip(passtooltip);
			custPassword.setTooltip(passtooltip);
			custUName.setTooltip(usertooltip);
			custGnd.setItems(genderList);
			custGnd.setValue(Gender.Male);
			custneighberhood.setItems(neighberhoodList);
			custneighberhood.setValue(Neighberhood.Neve_Shanan);
			aM = new AssistingMethods();
			biggestID = aM.getBiggestID(MainController.getRest().getCustomers().keySet()) + 1;
		}
	    
	   //showing and hiding sensitive labels
	    @FXML
	    void sensCheck(ActionEvent event) {
	    	
	    	if(sensRBtn.isSelected()) {
	    		lactLbl.setVisible(true);
	    		gluLbl.setVisible(true);
	    		lactRBtn.setVisible(true);
	    		gluRBtn.setVisible(true);
	    	}
	    	else {
	    		lactLbl.setVisible(false);
	    		gluLbl.setVisible(false);
	    		lactRBtn.setSelected(false);
	    		gluRBtn.setSelected(false);
	    		lactRBtn.setVisible(false);
	    		gluRBtn.setVisible(false);
	    	}
		}
	    
	    //determine password strength and if it legal
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
	    
	    //checking if the user name is legal(cannot contain integers)
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


	    //sign up
	    @FXML
	    void signUp(ActionEvent event) {
	    	
	    	labelModify();
	    	
	    	//all fields are legals
	    	if(legalFields()) {
	    	
	    		Customer cust = new Customer(custFName.getText(), custLName.getText(), custBday.getValue(), custGnd.getValue(), custneighberhood.getValue(), lactRBtn.isSelected(), gluRBtn.isSelected(), custUName.getText(), custPassword.getText(), imageName, custEmail);
	    		
	    		// checking if customer with the same ID already exists
	    		if(MainController.getRest().getCustomers().containsKey(cust.getId())) {
	    			cust.setId(biggestID);
	    			biggestID++;
	    			cust.setUserName(cust.getId() + custUName.getText());
	    			cust.setPassword(cust.getId() + custPassword.getText());
	    		}
	    		//adding the customer and saving data
	    		MainController.getRest().addCustomer(cust);
	    		JOptionPane.showMessageDialog(null, "Your JAVA-EAT ID is: " + cust.getId() + '\n' + 
	    				"Remember it, you need it in order to log in the system." + "\n" +
	    				"your user name: ID + the user name you chose." + "\n" +
	    				"your password: ID + the password you chose.");
	    		MainController.getRest().serialize();
	    		ChangeScene s = new ChangeScene();
				s.changeScene("/View/LoginView.fxml", 2);
	    	}
	    	else {
	    		  JOptionPane.showMessageDialog(null, "Invalid Input!",
					      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
	    	}

		}
	    
	    @FXML
	    void upload(ActionEvent event) {
	    	
	    	imageName = aM.uploadImg(choosenImg, imageName);
	    }
	    
	    //going back to login page
	    @FXML
	    void backToLogin(MouseEvent event) {
	  
			ChangeScene s = new ChangeScene();
			s.changeScene("/View/LoginView.fxml", 2);
	    }
	    
	    				/////// assisting methods///////
	  
	   //modify wrong input labels
	    private void labelModify() {
	    	
	    	if(custFName.getText().isEmpty()) {
	    		WrongFname.setText("Empty field!");
	    	}
	    	else {
	    		WrongFname.setText("");
	    	}
	    	if(custLName.getText().isEmpty()) {
	    		WrongLname.setText("Empty field!");
	    	}
	    	else {
	    		WrongLname.setText("");
	    	}
	    	if(custUName.getText().isEmpty()) {
	    		wrongUname.setText("Empty field!");
	    	}
	    	else{
	    		if(wrongUname.getText().isEmpty())
	    			wrongUname.setText("");
	    	}
	    	if(custBday.getValue() == null) {
	    		wrongBday.setText("Empty field!");
	    	}
	    	else {
	    		wrongBday.setText("");
	    	}
	    	if(custPassword.getText().isEmpty()) {
	    		emptyP.setText("Empty field!");
	    	}
	    	else {
	    		emptyP.setText("");
	    	}
	    	
	    }

	    //checking if all the fields values are legal
	    private boolean legalFields() {
    		
	    	if(WrongFname.getText().isEmpty() && WrongLname.getText().isEmpty() && wrongUname.getText().isEmpty() && wrongBday.getText().isEmpty() && emptyP.getText().isEmpty() && wrongP.getText().isEmpty() && !emailFld.getText().isEmpty()) {
	    		custEmail = emailFld.getText();
	    		return true;
	    	}
	    	return false;
    	}     
}
	    

