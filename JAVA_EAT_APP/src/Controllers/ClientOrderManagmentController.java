package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import Model.Customer;
import Model.Order;
import Utils.AssistingMethods;
import Utils.Neighberhood;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ClientOrderManagmentController implements Initializable{

    @FXML
    private ListView<String> onPrepareList;

    @FXML
    private ListView<String> onRouteList;

    @FXML
    private ListView<String> arrivedList;
    
    private ObservableList<String> arrList = FXCollections.observableArrayList();
    
    private ObservableList<String> onPrepList = FXCollections.observableArrayList();
    
    private ObservableList<String> onRtList = FXCollections.observableArrayList();

    @FXML
    private ImageView editBtn;

    @FXML
    private ImageView removeOnPrepareBtn;

    @FXML
    private ImageView viewOnPrepareBtn;

    @FXML
    private ImageView removeOnRouteBtn;

    @FXML
    private ImageView viewOnRouteBtn;

    @FXML
    private ImageView viewArrivedBtn;
    
    @FXML
    private AnchorPane toReplacePane;
    
    //the order id the user want to edit from the on prepare list
    public static int orderIDToEdit;

    
    private Customer cust;

    @FXML
    void edit(MouseEvent event) {

    	orderIDToEdit = extractId(onPrepareList.getSelectionModel().getSelectedItem());
    	if(orderIDToEdit != 0) {
    		AssistingMethods aM = new AssistingMethods();
        	aM.replace("JAVA EAT- EDIT ORDERS", "/View/ClientOrderManagmentEditOrdersView.fxml", toReplacePane);
    	}
    }

    @FXML
    void removeOnPrepare(MouseEvent event) {

    	String s = onPrepareList.getSelectionModel().getSelectedItem();
    	int id = extractId(s);
    	Order o;
    	if(id != 0) {
    		o = MainController.getRest().getRealOrder(id);
    		MainController.getRest().removeOrder(o);
    		JOptionPane.showMessageDialog(null, "Order " + o.getId() + " was removed successfully!");
    		onPrepList.remove(s);
    		MainController.restSerialize();
    	}
    }

    @FXML
    void removeOnRoute(MouseEvent event) {

    	String s = onRouteList.getSelectionModel().getSelectedItem();
    	int id = extractId(s);
    	Order o;
    	if(id != 0) {
    		o = MainController.getRest().getRealOrder(id);
    		MainController.getRest().removeOrder(o);
    		JOptionPane.showMessageDialog(null, "Order " + o.getId() + " was removed successfully!");
    		onRtList.remove(s);
    		MainController.restSerialize();
    	}
    }

    @FXML
    void viewArrived(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- VIEW ARRIVED ORDERS", "/View/ClientOrderManagmentArrivedViewAllView.fxml", toReplacePane);
    }

    @FXML
    void viewOnPrepare(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- VIEW ON PREPARE ORDERS", "/View/ClientOrderManagmentPrepViewAllView.fxml", toReplacePane);
    }

    @FXML
    void viewOnRoute(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- VIEW ON ROUTE ORDERS", "/View/ClientOrderManagmentOnRouteViewAllView.fxml", toReplacePane);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cust = MainController.getCust();
		TreeSet<Order> allCustOrds = MainController.getRest().getOrderByCustomer().get(cust);
		String prep = "";
		String route = "";
		String arrived = "";
		for(Order o: allCustOrds) {
			if(o != null) {
				if(o.getDelivery() == null) {
					prep = "ID: " + o.getId();
					onPrepList.add(prep);
				}
				else if(o.getDelivery().isDelivered()) {
					arrived = "ID: " + o.getId();
					arrList.add(arrived);
				}
				else {
					route = "ID: " + o.getId();
					onRtList.add(route);
				}
			}
		}
		onPrepareList.setItems(onPrepList);
		onRouteList.setItems(onRtList);
		arrivedList.setItems(arrList);
	}
	
	private int extractId(String s) {
		
		String ID = "";
		int id = 0;
    	if(s != null) {
    		for(int i = 4; i < s.length(); i++) {
    			if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
    				ID += s.charAt(i);
    			}
    			else {
    				break;
    			}
    		}
    		id = Integer.parseInt(ID);
    	}
    	return id;
	}
	
}
