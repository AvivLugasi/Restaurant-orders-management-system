package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Utils.AssistingMethods;
import Utils.ChangeScene;
import default_package.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MngHomePageController implements Initializable{

    @FXML
    private MediaView homeVideo;

    @FXML
   	private AnchorPane toReplacePane;

    @FXML
    private Button orderMngBtn;

    @FXML
    private Button repDataBtn;

    @FXML
    private Button opdBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private Label helloName;

    @FXML
    private ImageView HomeBtn;

    @FXML
    private ImageView saveBtn;

    @FXML
    private ImageView logoutBtn;

    @FXML
    private ImageView searchIcn;

    @FXML
    private ImageView opsIcn;

    @FXML
    private ImageView repDataIcn;

    @FXML
    private ImageView ordermngIcn;
    
    @FXML
    private AnchorPane mainScreen;

    private  File file;  
    private Media media;
    private MediaPlayer mediap;

    

    @FXML
    void goToHome(MouseEvent event) {

    	toReplacePane.setVisible(false);
    	resetMedia();
    	homeVideo.setVisible(true);
    	MainController.stg.setTitle("JAVA EAT- ADMIN HOMEPAGE");
    }

    @FXML
    void logOut(MouseEvent event) {

    	MainController.restSerialize();
    	ChangeScene s = new ChangeScene();
		s.changeScene("/View/LoginView.fxml", 2);
    }

    @FXML
    void opsChosen(ActionEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS", "/View/MangOpsView.fxml", toReplacePane);
    }

    @FXML
    void orderMngChosen(ActionEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN ORDER MANAGMENT", "/View/MangOrderManagmentView.fxml", toReplacePane);
    }

    @FXML
    void reportsDataChosen(ActionEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN REPORTS&DATA", "/View/MangReports&DataView.fxml", toReplacePane);
    }

    @FXML
    void saveData(MouseEvent event) {

    	MainController.restSerialize();
    }

    @FXML
    void seearchChosen(ActionEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN SEARCH", "/View/MangSearchView.fxml", toReplacePane);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		file = new File("media\\welcome to.mp4");
		media = new Media(file.toURI().toString());
		mediap = new MediaPlayer(media);
		homeVideo.setMediaPlayer(mediap);
		mediap.setCycleCount(MediaPlayer.INDEFINITE);
		homeVideo.setFitHeight(1050);homeVideo.setFitWidth(1596);
		mediap.setMute(true);
		playMedia();
		helloName.setText("ADMIN");
		
		
	}
	
    @FXML
    void opsMoveTo(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS", "/View/MangOpsView.fxml", toReplacePane);
    }

    @FXML
    void ordermngMoveTo(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN ORDER MANAGMENT", "/View/MangOrderManagmentView.fxml", toReplacePane);
    }

    @FXML
    void repDataMoveTo(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN REPORTS&DATA", "/View/MangReports&DataView.fxml", toReplacePane);
    }

    @FXML
    void searchMoveTo(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN SEARCH", "/View/MangSearchView.fxml", toReplacePane);
    }

	private void playMedia() {
			
			mediap.play();
	}
	
	private void resetMedia() {
		
		if(mediap.getStatus() != MediaPlayer.Status.READY) {
			mediap.seek(Duration.seconds(0.0));
		}
	}
	
	public  void replacePane(AnchorPane toReplacePane, String path) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		AnchorPane pane = loader.load();
		toReplacePane.getChildren().removeAll(toReplacePane.getChildren());
		toReplacePane.getChildren().add(pane);
		}
	
	

}

