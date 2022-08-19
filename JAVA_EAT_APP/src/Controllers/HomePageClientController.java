package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Utils.AssistingMethods;
import Utils.ChangeScene;
import default_package.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class HomePageClientController implements Initializable{

    @FXML
    private AnchorPane mainScreen;

    @FXML
    private MediaView homeVideo;
    
    private  File file;  
    private Media media;
    private MediaPlayer mediap;

    @FXML
    private ImageView HomeBtn;

    @FXML
    private ImageView logoutBtn;

    @FXML
    private ImageView saveBtn;

    @FXML
    public Label helloName;

    @FXML
    private ImageView profileIcn;

    @FXML
    private Button shoppingBtn;

    @FXML
    private Button readBtn;
    
    @FXML
    private Button  profileBtn;

    @FXML
    private ImageView readIcn;

    @FXML
    private ImageView shoppingIcn;

    @FXML
    private ImageView ordermngIcn;

    @FXML
    private Button orderMngBtn;

    @FXML
    private AnchorPane toReplacePane;

    @FXML
    private  ImageView clienImg;

    @FXML
    void goToHome(MouseEvent event) {

    	toReplacePane.setVisible(false);
    	resetMedia();
    	homeVideo.setVisible(true);
    	MainController.stg.setTitle("JAVA EAT- USER HOMEPAGE");
    }

    @FXML
    void logOut(MouseEvent event) {

    	MainController.restSerialize();
    	ChangeScene s = new ChangeScene();
		s.changeScene("/View/LoginView.fxml", 2);
    }

    @FXML
    void orderMngChosen(ActionEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- USER ORDER MANAGMENT", "/View/ClientOrderManagmentView.fxml", toReplacePane);
    }

    @FXML
    void ordermngMoveTo(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- USER ORDER MANAGMENT", "/View/ClientOrderManagmentView.fxml", toReplacePane);
    }

    @FXML
    void profileChosen(ActionEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- USER PROFILE", "/View/ClientProfileView.fxml", toReplacePane);
    }

    @FXML
    void profileMoveTo(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- USER PROFILE", "/View/ClientProfileView.fxml", toReplacePane);
    }

    @FXML
    void readChosen(ActionEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- READ MORE", "/View/ClientReadMoreView.fxml", toReplacePane);
    }

    @FXML
    void readMoveTo(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- READ MORE", "/View/ClientReadMoreView.fxml", toReplacePane);
    }

    @FXML
    void saveData(MouseEvent event) {

    	MainController.restSerialize();
    }

    @FXML
    void shoppingChosen(ActionEvent event) {

    	if(!MainController.getRest().getBlackList().contains(MainController.getCust())) {
	    	AssistingMethods aM = new AssistingMethods();
	    	aM.replace("JAVA EAT- SHOPPING CART", "/View/ClientShoppingCartView.fxml", toReplacePane);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "We are sorry youre recent actions are incompatible with \n"
    				+ "our policy so we cannot let you access this service. \n"
    				+ "For more information and sending a complaint move to Read More page. :(");
    	}
    }

    @FXML
    void shoppingMoveTo(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- SHOPPING CART", "/View/ClientShoppingCartView.fxml", toReplacePane);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		file = new File("media\\welcome to.mp4");
		media = new Media(file.toURI().toString());
		mediap = new MediaPlayer(media);
		homeVideo.setMediaPlayer(mediap);
		mediap.setCycleCount(MediaPlayer.INDEFINITE);
		homeVideo.setFitHeight(1050);homeVideo.setFitWidth(1596);
		mediap.setMute(true);
		playMedia();
		helloName.setText(MainController.getCust().getFirstName());
		String imageName = MainController.getCust().getCustPicName();
		String reletivePath = "/media/" + imageName + ".png";
    	String projectPath = System.getProperty("user.dir");
    	String currectpath = projectPath.replace('\\', '/');
    	currectpath += reletivePath;
    	File f = new File(currectpath);
		Image original = new Image(f.toURI().toString());
		clienImg.setImage(original);
		clienImg.setVisible(true);
		
	}
	
	private void playMedia() {
		
		mediap.play();
	}

	private void resetMedia() {
	
		if(mediap.getStatus() != MediaPlayer.Status.READY) {
			mediap.seek(Duration.seconds(0.0));
		}
	}

}
