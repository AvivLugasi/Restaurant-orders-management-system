package Controllers;

import java.io.IOException;

import Utils.AssistingMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MangOpsCustomerController {

    @FXML
    private AnchorPane toReplacePane;

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView addToBlackListBtn;

    @FXML
    private ImageView addCustomerBtn;

    @FXML
    private ImageView RemoveCustomerBtn;

    @FXML
    private ImageView viewCustomerBtn;

    @FXML
    private ImageView customerEditBtn;

    @FXML
    void addCustomer(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-ADD CUSTOMER", "/View/MangOpsCustomerAddView.fxml", toReplacePane);
    }

    @FXML
    void addToBlackList(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-ADD CUSTOMER TO BLACKLIST", "/View/MangOpsCustomerAddToBlackListView.fxml", toReplacePane);
    }

    @FXML
    void editCustomer(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-EDIT CUSTOMER", "/View/MangOpsCustomerEditView.fxml", toReplacePane);
    }

    @FXML
    void removeCustomer(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-REMOVE CUSTOMER", "/View/MangOpsCustomerRemoveView.fxml", toReplacePane);
    }

    @FXML
    void viewAllCustomers(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN OPS-VIEW ALL CUSTOMERS", "/View/MangOpsCustomerViewAllView.fxml", toReplacePane);
    }
  
}
