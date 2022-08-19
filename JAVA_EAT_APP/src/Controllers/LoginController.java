package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.JOptionPane;

import Model.Customer;
import Utils.ChangeScene;
import default_package.MainController;

public class LoginController {

static private int toggle = 1;
	
	@FXML
    private ImageView userImg;

    @FXML
    private ImageView passImg;

    @FXML
    private ImageView visiblePassImg;
	
	@FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView loginImg;

    @FXML
    private TextField uName;

    @FXML
    private PasswordField uPass;

    @FXML
    private Button lgnBtn;

    @FXML
    private Label wrongName;

    @FXML
    private Label wrongPASS;

    @FXML
    private Button passSet;
    
    @FXML
    private TextField visiblePassword;
    
    @FXML
    private Button signUpBtn;
    
    @FXML
    private Button forgotBtn;
    
    @FXML
    void initilaize() {
    	
    	Tooltip passtooltip = new Tooltip();
    	Tooltip usertooltip = new Tooltip();
    	passtooltip.setText("Your JAVA-EAT ID + your password");
    	usertooltip.setText("Your JAVA-EAT ID + your user name");
    	uName.setTooltip(usertooltip);
    	uPass.setTooltip(passtooltip);
    }
    @FXML
    void lgnAction(ActionEvent event) {
    	//input validation checks
    	
    	String password = null;
    	if(toggle % 2 != 0) {
    		password = uPass.getText();
    	}
    	else {
    		password = visiblePassword.getText();
    	}
    	if(uName.getText().isEmpty()) {
    		errorMassage(1);
    	}
    	if(password.isEmpty()) {
    		errorMassage(2);
    	}
    	String name = uName.getText();
    	if(!name.isEmpty() && name.charAt(0)>= '0' && name.charAt(0)<='9') {
    		clientValidetion(name,password);
    	}
    	else if(name.equals("manager")) {
    		adminValidetion(name,password);
    	}
    	else {
    		if(!uName.getText().isEmpty())
    			errorMassage(5);
    	}
	}
    
    void errorMassage(int scenario) {
    	switch(scenario) {
    		case 1:{
    			wrongName.setText("User name is empty!");
    			break;
    		}
    		case 2:{
    			wrongPASS.setText("User Password is empty!");
    			break;
    		}
    		case 3:{
    			wrongName.setText("Incorrect user name!");
    			break;
    		}
    		case 4:{
    			wrongPASS.setText("Incorrect password!");
    			break;
    		}
    		case 5:{
    			wrongName.setText("Incorrect user name or password!");
    		}
    	}
    }
   
    @FXML
    void visibleSet(ActionEvent event) {
    	
    	if(toggle % 2 != 0) {
    		visiblePassword.setVisible(true);
    		visiblePassword.setText(uPass.getText());
    		uPass.setVisible(false);
			toggle++;
    	}
		else {
			visiblePassword.setVisible(false);
			uPass.setText(visiblePassword.getText());
    		visiblePassword.setText(uPass.getText());
			uPass.setVisible(true);
    		toggle++;
    		}
	}
    
    @FXML
    void sendEmail(ActionEvent event) {
    	
    	String ID = JOptionPane.showInputDialog(null, "What is your JAVA-EAT ID?", 
                "Help", JOptionPane.QUESTION_MESSAGE);
    	try {
    		Customer cust = MainController.getRest().getRealCustomer(Integer.parseInt(ID));
    		String userName = cust.getUserName();
    		String password = cust.getPassword();
    		String email = cust.getEmail();
    		String massage = "Hello " + cust.getFirstName() + " " + cust.getLastName() + "\n"+
    		"Your user name: " + userName + "\n" +
    				"Your password: " + password;
    		Utils.SendMail.send(email, massage, "UserName and Password Reminder","javaeat136@gmail.com", "javaeatsupport");
    		JOptionPane.showMessageDialog(null, "User name and password reminder was sent successfully to your email acount.");
    				
    	}catch(Exception e) {
    		if(ID != null )
    			JOptionPane.showMessageDialog(null, "Invalid ID!",
				      "INPUT ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }

	@FXML
	void sgnUp(ActionEvent event) {
		
		ChangeScene s = new ChangeScene();
		s.changeScene("/View/SignUpView.fxml", 1);
		
	}
	
	void managerHomP() {
		
		ChangeScene s = new ChangeScene();
		s.changeScene("/View/HomePageMangView.fxml", 3);
		
	}
	
	void clientHomP() {
		
		ChangeScene s = new ChangeScene();
		s.changeScene("/View/HomePageClientView.fxml", 4);
		
	}
	
	private void clientValidetion(String name, String password) {
		
		int num = 0;
		String s = "";
		s = "" + name.charAt(0);
		int i = 1;
		while(i < name.length() && name.charAt(i)>='0' && name.charAt(i)<='9') {
			s += name.charAt(i);
			i++;
		}
		num = Integer.parseInt(s);
		Customer cust = MainController.getRest().getRealCustomer(num);
		if(cust == null) {
			errorMassage(3);
			errorMassage(4);
		}
		else {
			wrongName.setText("");
			if(!cust.getPassword().equals(password)) {
				errorMassage(4);
			}
			else {
				wrongPASS.setText("");
				if(uName.getText().equals(cust.getUserName())) {
					MainController.setManager(false);
					MainController.setCust(cust);
					clientHomP();
				}
				else {
					errorMassage(3);
				}
			}
		}
	}
	
	private void adminValidetion(String name, String password) {
		
		wrongName.setText("");
		if(password.equals("manager")) {
			wrongPASS.setText("");
			MainController.setManager(true);
			managerHomP();		
		}
	
    	if(!name.equals("manager")) {
    		errorMassage(3);
    	}
    	if(!password.equals("manager")) {
    		errorMassage(4);
    	}
	}
}

	
    




   
    


