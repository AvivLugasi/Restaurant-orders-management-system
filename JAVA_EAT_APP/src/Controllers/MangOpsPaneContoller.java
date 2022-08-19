package Controllers;

import java.io.IOException;

import Utils.AssistingMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MangOpsPaneContoller {

    @FXML
    private ImageView cookOpp;

    @FXML
    private ImageView customersOpp;

    @FXML
    private ImageView deliveryprOpp;

    @FXML
    private ImageView areasOpp;

    @FXML
    private ImageView compOpp;

    @FXML
    private ImageView dishesOpp;
    
    @FXML
    private Pane mainPane;
    
    @FXML
	private AnchorPane toReplacePane;

    @FXML
    void areasChosen(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-AREA MENU", "/View/MangOpsAreaView.fxml", toReplacePane);
    	mainPane.setVisible(false);
    }

    @FXML
    void compChosen(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-COPMONENT MENU", "/View/MangOpsComponentView.fxml", toReplacePane);
    	mainPane.setVisible(false);
    }

    @FXML
    void cookChosen(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-COOK MENU", "/View/MangOpsCookView.fxml", toReplacePane);
    	mainPane.setVisible(false);
    }

    @FXML
    void custChosen(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-CUSTOMER MENU", "/View/MangOpsCustomerView.fxml", toReplacePane);
    	mainPane.setVisible(false);
    }

    @FXML
    void delPerChosen(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-DELIVERY PERSON MENU", "/View/MangOpsDeliveryPersonView.fxml", toReplacePane);
    	mainPane.setVisible(false);
    }

    @FXML
    void dishesChosen(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-DISH MENU", "/View/MangOpsDishView.fxml", toReplacePane);
    	mainPane.setVisible(false);
    }
}
