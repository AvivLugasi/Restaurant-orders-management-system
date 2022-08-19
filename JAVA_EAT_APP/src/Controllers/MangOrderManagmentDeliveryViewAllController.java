package Controllers;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import Model.Delivery;
import Model.DeliveryPerson;
import Model.ExpressDelivery;
import Model.Order;
import Model.RegularDelivery;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class MangOrderManagmentDeliveryViewAllController implements Initializable{

    @FXML
    private TextArea viewResualt;

    @FXML
    private ListView<String> list;
    
    @FXML
    private Label Revenue;

    @FXML
    private ListView<String> revnList;
    
    private ObservableList<String> InfoList = FXCollections.observableArrayList();

    @FXML
    private Label totalRev;
    
    private ObservableList<String> delvList = FXCollections.observableArrayList();

    @FXML
    void showDetails(MouseEvent event) {

    	String s = list.getSelectionModel().getSelectedItem();
    	if(s != null) {
    		String ID = "";
    		for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
    		Delivery delivery = MainController.getRest().getRealDelivery(Integer.parseInt(ID));
			viewResualt.setText(delivery.toString());
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String s = "";
		String ID = "";
		String type = "";
		String revenue = "";
		double sum = 0;
		Collection<Delivery> d = MainController.getRest().getDeliveries().values();
		for(Delivery del: d) {
			if(del != null) {
				if(del instanceof ExpressDelivery && ((ExpressDelivery) del).getOrder() != null) {
					type = "Express Delivery ";
					revenue = del.getId() + " " + String.format("%.2f",((ExpressDelivery)del).getOrder().calcOrderRevenue()) + " $";
					sum += ((ExpressDelivery)del).getOrder().calcOrderRevenue();
				}
				else if(del instanceof RegularDelivery){
					type = "Regular Delivery ";
					Set<Order> rOrders = ((RegularDelivery)del).getOrders();
					double total = 0;
					for(Order o:rOrders) {
						if(o != null)
						total += o.calcOrderRevenue();
					}
					sum += total;
					revenue = del.getId() + " " + String.format("%.2f", total) + " $";
				}
				s += del.getId() +"Type: " + type + "Arrived: " + del.isDelivered();
			}
			delvList.add(s);
			InfoList.add(revenue);
			s = "";
			revenue = "";
		}
		totalRev.setText(String.format("%.2f $", sum));
		revnList.setItems(InfoList);
		list.setItems(delvList);
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		if(!delvList.isEmpty()) {
			list.getSelectionModel().select(delvList.get(0));
			s = delvList.get(0);
			
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					ID += s.charAt(i);
				}
				else {
					break;
				}
					
			}
			Delivery delivery = MainController.getRest().getRealDelivery(Integer.parseInt(ID));
			viewResualt.setText(delivery.toString());
		}
		}
	}

