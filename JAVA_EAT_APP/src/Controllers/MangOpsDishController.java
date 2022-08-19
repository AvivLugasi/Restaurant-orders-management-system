package Controllers;

import java.io.IOException;

import Utils.AssistingMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MangOpsDishController {

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView addDishBtn;

    @FXML
    private ImageView removeDishBtn;

    @FXML
    private ImageView viewDishBtn;

    @FXML
    private ImageView dishEditBtn;

    @FXML
    private AnchorPane toReplacePane;

    @FXML
    void addDish(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-ADD DISH", "/View/MangOpsDishAddView.fxml", toReplacePane);
    }

    @FXML
    void editDish(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-EDIT DISH", "/View/MangOpsDishEditView.fxml", toReplacePane);
    }

    @FXML
    void removeDish(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-REMOVE DISHES", "/View/MangOpsDishRemoveView.fxml", toReplacePane);
    }

    @FXML
    void viewAllDishes(MouseEvent event) {
    	
    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-VIEW ALL DISHES", "/View/MangOpsDishViewAllView.fxml", toReplacePane);
    }
}
