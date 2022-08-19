package Controllers;

import java.io.IOException;

import Utils.AssistingMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MangOpsComponentController {

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView addCompBtn;

    @FXML
    private ImageView RemoveCompBtn;

    @FXML
    private ImageView viewCompBtn;

    @FXML
    private ImageView changePriceBtn;

    @FXML
    private AnchorPane toReplacePane;

    @FXML
    void addComp(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-ADD COMPONENT", "/View/MangOpsComponentAddView.fxml", toReplacePane);
    }

    @FXML
    void editPrice(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-COMPONENT CHANGE PRICE", "/View/MangOpsComponentChangePriceView.fxml", toReplacePane);
    }

    @FXML
    void removeComp(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-REMOVE COMPONENT", "/View/MangOpsComponentRemoveView.fxml", toReplacePane);
    }

    @FXML
    void viewAllComp(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-VIEW ALL COMPONENTS", "/View/MangOpsComponentViewAllView.fxml", toReplacePane);
    }
    
}
