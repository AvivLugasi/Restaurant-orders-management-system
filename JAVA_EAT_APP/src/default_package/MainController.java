package default_package;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.Customer;
import Model.DeliveryArea;
import Model.Order;
import Model.RegularDelivery;
import Model.Restaurant;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Utils.SendMail;

public class MainController extends Application{

	private static Restaurant rest;
	private static Customer cust;
	public static Stage stg;
	private static Scene scn;
	private static boolean manager = false;
	
	
	public static Customer getCust() {
		
		return cust;
	}

	public static void setCust(Customer cust) {
		
		MainController.cust = cust;
	}

	
	public static boolean isManager() {
		return manager;
	}

	public static void setManager(boolean manager) {
		MainController.manager = manager;
	}

	public static Restaurant getRest() {
		return rest;
	}

	public static void setRest(Restaurant rest) {
		MainController.rest = rest;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
  
		stg = primaryStage; 
		rest = Restaurant.deserialize();
		if(rest == null) {
			rest = Restaurant.getInstance();
		} 
		Parent root = FXMLLoader.load(getClass().getResource("/View/LoginView.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/View/app.css").toExternalForm());
		scn = scene;
		primaryStage.setScene(scene);
		primaryStage.setTitle("JAVA EAT-LOGIN");
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
		//set close option by clicking the X button
		primaryStage.setOnCloseRequest(e ->{
		
			e.consume();
		closeApp();});
		//set close option by clicking the Esc button
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
		  () {

		        @Override
		        public void handle(KeyEvent t) {
		          if(t.getCode()==KeyCode.ESCAPE)
		          {
		        	  closeApp();
		          }
		        }
		    });
	}
	
	
	
	public static void main(String args[]) {
	 
		launch(args);
	}
	
	public static void restSerialize() {
		rest.serialize();
	}
	
	public static void changeScene(int num, Scene scn2) {
		switch(num) {
			case 1:{
				stg.setTitle("JAVA EAT-SIGNUP");
				scn = scn2;
				stg.setScene(scn);stg.show();
				break;
			}
			case 2:{
				stg.setTitle("JAVA EAT-LOGIN");
				scn = scn2;
				stg.setScene(scn);stg.show();
				manager = false;
				break;
			}
			case 3:{
				stg.setTitle("JAVA EAT- ADMIN HOMEPAGE");
				scn = scn2;
				stg.setScene(scn);stg.show();
				break;
			}
			case 4:{
				stg.setTitle("JAVA EAT- USER HOMEPAGE");
				scn = scn2;
				stg.setScene(scn);stg.show();
				break;
			}
		
		}
	}
	
	//close app method
	private void closeApp() {
		
		//confirm action dialog
		int n = JOptionPane.showConfirmDialog(
			    null,
			    "Are you sure you want to close the app?",
			    "CLOSE CONFIRM",
			    JOptionPane.YES_NO_OPTION);
		//if answer is yes
		if(n == 0) {
			stg.close();
		}
		
	}

}


