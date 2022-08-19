package Controllers;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import Model.Order;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ClientOrderManagmentOnRouteViewAllController implements Initializable{
	 
		@FXML
	    private TextArea viewResualt;

	    @FXML
	    private ListView<String> list;

	    @FXML
	    private ListView<String> orderInfo;

	    private ObservableList<String> ordersList = FXCollections.observableArrayList();
	    
	    private ObservableList<String> infolist = FXCollections.observableArrayList();
	    
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
	    		Order order = MainController.getRest().getRealOrder(Integer.parseInt(ID));
				viewResualt.setText(order.toString());
	    	}
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			String s = "";
			String info = "";
			Collection<Order> o = MainController.getRest().getOrderByCustomer().get((MainController.getCust()));
			for(Order order: o) {
				if(order.getDelivery() != null && order.getDelivery().isDelivered() == false) {
					s += order.getId();
					info += String.format("%d %.2f $",order.getId(),order.calcOrderRevenue());
					ordersList.add(s);
					infolist.add(info);
					s = "";
					info = "";
				}
			}
			list.setItems(ordersList);
			orderInfo.setItems(infolist);
		}

	}
