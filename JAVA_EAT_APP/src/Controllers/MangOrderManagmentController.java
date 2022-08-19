package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Model.Delivery;
import Model.ExpressDelivery;
import Model.Order;
import Utils.AssistingMethods;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MangOrderManagmentController implements Initializable{

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView addOrderBtn;

    @FXML
    private ImageView removeOrderBtn;

    @FXML
    private ImageView editOrderBtn;

    @FXML
    private ImageView removeDelBtn;
    
    @FXML
    private AnchorPane toReplacePane;

    @FXML
    private ImageView packOrdersBtn;

    @FXML
    private ListView<String> ordersListView;
    
    @FXML
    private ListView<String> orderInfoList;

    @FXML
    private ListView<String> delsListView;
    
    private ObservableList<String> orderList = FXCollections.observableArrayList();

    private ObservableList<String> delvList = FXCollections.observableArrayList();
    
    private ObservableList<String> orderInfo = FXCollections.observableArrayList();
    
    @FXML
    private ImageView viewDelBtn;

    @FXML
    private ImageView viewOrderBtn;
    
    @FXML
    private ToggleButton yes = new ToggleButton("YES");
    
    @FXML
    private ToggleButton no = new ToggleButton("NO");
    
    private ToggleGroup group = new ToggleGroup();
    
    @FXML
    private ImageView red;

    @FXML
    private ImageView green;
    private Delivery d;

    @FXML
    void addOrder(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN -ADD ORDER", "/View/MangOrderManagmentOrderAddView.fxml", toReplacePane);
    }

    @FXML
    void editOrder(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN -EDIT ORDER", "/View/MangOrderManagmentOrderEditView.fxml", toReplacePane);
    }

    @FXML
    void packOrders(MouseEvent event) {

    	if(!orderList.isEmpty()) {
    		MainController.getRest().CreateDeliveries();
    		orderList.clear();
    		ordersListView.setItems(orderList);
    		orderInfo.clear();
    		orderInfoList.setItems(null);
    		Collection<Delivery> d = MainController.getRest().getDeliveries().values();
    		delvList.clear();
    		String s = "";
    		String type = "";
    		for(Delivery del: d) {
    			if(del != null) {
    				if(del instanceof ExpressDelivery) {
    					type = "Express Delivery ";
    				}
    				else {
    					type = "Regular Delivery ";
    				}
    				s += del.getId() +"Type: " + type;
    			}
    			if(!s.equals("")) {
    				delvList.add(s);
    			}
    			s = "";
    		}
    		delsListView.setItems(delvList);
    		MainController.restSerialize();
    		JOptionPane.showMessageDialog(null, "Orders were packed succsesfully!");
    	}
    	else {
    		  JOptionPane.showMessageDialog(null, "Orders waiting list is empty!");
    	}
    }

    @FXML
    void removeDel(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN -REMOVE DELIVERY", "/View/MangOrderManagmentDeliveryRemoveView.fxml", toReplacePane);
    }

    @FXML
    void removeOrder(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN -REMOVE ORDER", "/View/MangOrderManagmentOrderRemoveView.fxml", toReplacePane);
    }

    @FXML
    void viewDel(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN -VIEW ALL DELIVERIES", "/View/MangOrderManagmentDeliveryViewAllView.fxml", toReplacePane);
    }

    @FXML
    void viewOrder(MouseEvent event) {

    	AssistingMethods aM = new AssistingMethods();
    	aM.replace("JAVA EAT- ADMIN -VIEW ALL ORDERS", "/View/MangOrderManagmentOrderViewAllView.fxml", toReplacePane);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String s = "";
		String info = "";
		String type = "";
		Collection<Order> o = MainController.getRest().getOrders().values();
		for(Order order: o) {
			if(order.getDelivery() == null) {
				s += order.getId() + " " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName();
				info += String.format("%d %.2f $",order.getId(),order.calcOrderRevenue());
			}
			if(!info.equals("") && !s.equals("")) {
				orderInfo.add(info);
				orderList.add(s);
			}
			s = "";
			info = "";
		}
		ordersListView.setItems(orderList);
		orderInfoList.setItems(orderInfo);
		Collection<Delivery> d = MainController.getRest().getDeliveries().values();
		for(Delivery del: d) {
			if(del != null) {
				if(del instanceof ExpressDelivery) {
					type = "Express Delivery ";
				}
				else {
					type = "Regular Delivery ";
				}
				s += del.getId() +"Type: " + type;			
			}
			if(!s.equals("")) {
				delvList.add(s);
			}
			s = "";
		}
		delsListView.setItems(delvList);
		yes.setToggleGroup(group);no.setToggleGroup(group);
	}
	
	 @FXML
	    void showDetails(MouseEvent event) {
		 
		 String s = delsListView.getSelectionModel().getSelectedItem();
		 if(!s.isEmpty()) {
			 String id = "";
			 for(int i = 0; i < s.length(); i++) {
				 if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					 id += s.charAt(i);
				 }
				 else
					 break;
			 }
			 d = MainController.getRest().getRealDelivery(Integer.parseInt(id));
			 if(d != null) {
				 if(d.isDelivered() == false) {
					 red.setVisible(true);
					 green.setVisible(false);
				 }
				 else {
					 red.setVisible(false);
					 green.setVisible(true);
				 }
					 
			 }
		 }

	    }
	 
	 @FXML
	    void setArrive(ActionEvent event) {
		 
		 	if(event.getSource().equals(yes)) {
		 		red.setVisible(false);
				 green.setVisible(true);
				 if(d != null)
					 MainController.getRest().deliver(d);
				 	 MainController.restSerialize();
		 	}
		 	else {
	 			red.setVisible(true);
	 			green.setVisible(false);
	 			if(d != null)
	 				d.setDelivered(false);
	 			MainController.restSerialize();
		 	}
		 	
	    }
}
