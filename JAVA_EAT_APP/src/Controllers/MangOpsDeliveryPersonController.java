package Controllers;

import java.io.IOException;

import Utils.AssistingMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MangOpsDeliveryPersonController {

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView addDelPBtn;

    @FXML
    private ImageView removeDelPBtn;

    @FXML
    private ImageView viewAllDelPBtn;

    @FXML
    private ImageView delPEditBtn;

    @FXML
    private AnchorPane toReplacePane;

    @FXML
    void addDelP(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-ADD DELIVERY PERSON", "/View/MangOpsDeliveryPersonAddView.fxml", toReplacePane);
    }

    @FXML
    void delPEdit(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-EDIT DELIVERY PERSON", "/View/MangOpsDeliveryPersonEditView.fxml", toReplacePane);
    }

    @FXML
    void removeDelP(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-REMOVE DELIVERY PERSON", "/View/MangOpsDeliveryPersonRemoveView.fxml", toReplacePane);
    }

    @FXML
    void viewAllDelP(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-VIEW ALL DELIVERY PERSON", "/View/MangOpsDeliveryPersonViewAllView.fxml", toReplacePane);
    }
    
}
