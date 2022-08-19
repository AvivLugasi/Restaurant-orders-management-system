package Controllers;

import java.io.IOException;

import Utils.AssistingMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MangOpsAreaController {

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView addAreaBtn;

    @FXML
    private ImageView removeAreaBtn;

    @FXML
    private ImageView viewAllAreasBtn;

    @FXML
    private ImageView areaEditBtn;

    @FXML
    private AnchorPane toReplacePane;

    @FXML
    void addArea(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-ADD AREA", "/View/MangOpsAreaAddView.fxml", toReplacePane);
    }
    	
    @FXML
    void areaEdit(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-EDIT AREA", "/View/MangOpsAreaEditView.fxml", toReplacePane);
    }

    @FXML
    void removeArea(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-REMOVE AREA", "/View/MangOpsAreaRemoveView.fxml", toReplacePane);
    }

    @FXML
    void viewAllAreas(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-VIEW ALL AREAS", "/View/MangOpsAreaViewAllView.fxml", toReplacePane);
    }

}
