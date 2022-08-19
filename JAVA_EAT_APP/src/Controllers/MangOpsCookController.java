package Controllers;

import java.io.IOException;

import Utils.AssistingMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MangOpsCookController {

    @FXML
    private ImageView viewCookByExpBtn;

    @FXML
    private ImageView addCookBtn;

    @FXML
    private ImageView RemoveCookBtn;

    @FXML
    private ImageView viewCookBtn;

    @FXML
    private ImageView cookEditBtn;
    
    @FXML
    private AnchorPane toReplacePane;
    
    @FXML
    private Pane mainPane;

    @FXML
    void addCook(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-ADD COOK", "/View/MangOpsCookAddView.fxml", toReplacePane);
    }

    @FXML
    void removeCook(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-REMOVE COOK", "/View/MangOpsCookRemoveView.fxml", toReplacePane);
    }

    @FXML
    void viewAllCooks(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-VIEW ALL COOKS", "/View/MangOpsCookViewAllView.fxml", toReplacePane);
    }
    
    @FXML
    void editCook(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-EDIT COOK", "/View/MangOpsCookEditView.fxml", toReplacePane);
    }

    @FXML
    void viewCooksByExp(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-VIEW BY EXPERTISE", "/View/MangOpsCookViewByExpView.fxml", toReplacePane);
    }
}
