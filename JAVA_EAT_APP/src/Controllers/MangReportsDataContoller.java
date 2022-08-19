package Controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import Model.Component;
import Model.Customer;
import Model.Delivery;
import Model.DeliveryPerson;
import Model.Dish;
import Model.ExpressDelivery;
import default_package.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MangReportsDataContoller implements Initializable{

	 @FXML
    private AnchorPane toReplacePane;
	 
	@FXML
    private Pane mainPane;

    @FXML
    private ImageView HelpIcn;

    @FXML
    private Button helpBtn;

    @FXML
    private Button delPerTypeBtn;

    @FXML
    private Button expRevenBtn;

    @FXML
    private Button popCompBtn;

    @FXML
    private Button profitRltBtn;

    @FXML
    private Button delsPerPBtn;
    
    @FXML
    private Pane delByPPane;

    @FXML
    private Button showBtn;

    @FXML
    private ComboBox<Integer> dpIds;
    
    @FXML
    private PieChart delperTypeChart;
    
    @FXML
    private BarChart<String, Integer> compsPopChart;

    @FXML
    private CategoryAxis ComponentsX;

    @FXML
    private NumberAxis FrequencyY;

    @FXML
    private ComboBox<Integer> montsListView;
    
    private ObservableList<Integer> dpList = FXCollections.observableArrayList();
    
    private ObservableList<Integer> monthsList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
   
    public static HashMap<Component, Integer> componentsandAmounts;
    
    public static HashMap<Customer, Double> revenuByCust;
    
    public static HashMap<Dish, Double> relation;
    
    @FXML
    private BarChart<String, Double> revnExpChart;

    @FXML
    private CategoryAxis revX;

    @FXML
    private NumberAxis revY;
    
    @FXML
    private Label value;
    
    String helpMassage;
    
    @FXML
    private ListView<String> list;

    @FXML
    private TextArea result;

    @FXML
    private PieChart delByDelP;
    
    private ObservableList<String> delvList = FXCollections.observableArrayList();

    @FXML
    private PieChart ProfitRelationChart;
    
    @FXML
    void delPerType(ActionEvent event) {

    	compsPopChart.setVisible(false);
    	delByPPane.setVisible(false);
    	delperTypeChart.setVisible(true);
    	revnExpChart.setVisible(false);
    	ProfitRelationChart.setVisible(false);
    	value.setVisible(false);
    	delByDelP.setVisible(false);
    }

    @FXML
    void delsPerP(ActionEvent event) {

    	delByPPane.setVisible(true);
    	delperTypeChart.setVisible(false);
    	compsPopChart.setVisible(false);
    	revnExpChart.setVisible(false);
    	ProfitRelationChart.setVisible(false);
    	value.setVisible(false);
    	delByDelP.setVisible(true);
    }

    @FXML
    void expReven(ActionEvent event) {

    	delByPPane.setVisible(false);
    	delperTypeChart.setVisible(false);
    	compsPopChart.setVisible(false);
    	revnExpChart.setVisible(true);
    	ProfitRelationChart.setVisible(false);
    	value.setVisible(false);
    	delByDelP.setVisible(false);
    }

   

    @FXML
    void popComp(ActionEvent event) {

    	delByPPane.setVisible(false);
    	delperTypeChart.setVisible(false);
    	compsPopChart.setVisible(true); 
    	revnExpChart.setVisible(false);
    	ProfitRelationChart.setVisible(false);
    	value.setVisible(false);
    	delByDelP.setVisible(false);
    }

    @FXML
    void profit(ActionEvent event) {

    	delByPPane.setVisible(false);
    	delperTypeChart.setVisible(false);
    	compsPopChart.setVisible(false);
    	revnExpChart.setVisible(false);
    	ProfitRelationChart.setVisible(true);
    	value.setVisible(true);
    	delByDelP.setVisible(false);
    }

    @FXML
    void readMore(MouseEvent event) {

    	JOptionPane.showMessageDialog(null, helpMassage);
    }

    @FXML
    void readMorePopUp(ActionEvent event) {

    	JOptionPane.showMessageDialog(null, helpMassage);
    }

   
    
    @FXML
    void showResult(ActionEvent event) {

    	delvList.clear();
    	list.setItems(null);
    	result.setText("");
    	if(dpIds.getValue()!= null && montsListView.getValue() != null) {
	    	DeliveryPerson dp = MainController.getRest().getRealDeliveryPerson(dpIds.getValue());
	    	Integer month = montsListView.getValue();
	    	TreeSet<Delivery> ALLDels = MainController.getRest().getDeliveriesByPerson(dp, month);
	    	String type = "";
	    	String s = "";
	    	if(ALLDels != null) {
	    		for(Delivery del: ALLDels) {
    				if(del instanceof ExpressDelivery) {
    					type = "Express Delivery ";
    				}
    				else {
    					type = "Regular Delivery ";
    				}
    				s += del.getId() +"Type: " + type;			
	    			if(!s.equals("")) {
	    				delvList.add(s);
	    			}
	    			s = "";
	    		}
	    		list.setItems(delvList);
	    	}
    	}
    }
    
    @FXML
    void showMoreDetails(MouseEvent event) {

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
			result.setText(delivery.toString());
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		relation = new HashMap<Dish, Double>();
		dpList.addAll(MainController.getRest().getDeliveryPersons().keySet());
		dpIds.setItems(dpList);
		montsListView.setItems(monthsList);
		helpMassage = "About the queries:\n"
				+ "1)Deliveries By Person: by given delivery person and month returns all the deliveries he or she delivered at the given month.\n"
				+ "2)Number Of Deliveries Per Type: returns the number of each type of delivery at the last year.\n"
				+ "3) revenue From Express Deliveries: returns the revenue from the express deliveries.\n"
				+ "4)Popular Components: returns a list of all the components of the restaurant sorted by their popularity.\n"
				+ "5)Profit Relation: returns a sorted list of the relation between each dish and its prepare time.";
		setDelPerTypeChart();
		setCompPopChart();
		setRevExpChart();
		setProfitChart();
		setDelsPerDpChart();
	}
	
	private void setDelPerTypeChart() {
		
		HashMap<String,Integer> delsPerType = MainController.getRest().getNumberOfDeliveriesPerType();
		if(delsPerType != null) {																																																		
    		ObservableList<PieChart.Data> typesandAmount = FXCollections.observableArrayList(new PieChart.Data(String.format("Regular delivery amount:%d", delsPerType.get("Regular delivery")), delsPerType.get("Regular delivery")),new PieChart.Data(String.format("Express delivery amount:%d", delsPerType.get("Express delivery")),delsPerType.get("Express delivery"))); 		
    		delperTypeChart.setData(typesandAmount);
    		delperTypeChart.setStartAngle(90);
    		
    	}
	}
	
	private void setDelsPerDpChart() {
		
		HashMap<DeliveryPerson,Integer> delsPerDp = MainController.getRest().getDelsByDp();
    	if(delsPerDp != null) {	
    		ObservableList<PieChart.Data> typesandAmount = FXCollections.observableArrayList(); 		
    		for(DeliveryPerson dp: delsPerDp.keySet()) {
    			PieChart.Data data = new PieChart.Data(dp.getFirstName() + " " + dp.getLastName() + " amount:"+(delsPerDp.get(dp)), delsPerDp.get(dp));
    			typesandAmount.add(data);
    		}
    		delByDelP.setData(typesandAmount);
    		delByDelP.setStartAngle(90);
    		
    	}
	}
	
	private void setProfitChart() {
		
		TreeSet<Dish> dishesByprofit = MainController.getRest().getProfitRelation();
    	if(dishesByprofit != null) {																																																		
    		ObservableList<PieChart.Data> dByPList = FXCollections.observableArrayList(); 		
    		for(Dish d: dishesByprofit) {
    			PieChart.Data data = new PieChart.Data(d.getDishName(), relation.get(d));
    			dByPList.add(data);
    		}		
    		ProfitRelationChart.setData(dByPList);
    		ProfitRelationChart.setStartAngle(90);
    		for(PieChart.Data data: ProfitRelationChart.getData()) {
    			data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						// TODO Auto-generated method stub
						value.setTranslateX(e.getSceneX() - value.getLayoutX());
						value.setTranslateY(e.getSceneY() - value.getLayoutY());
						value.setText(String.format("%.2f", data.getPieValue()));
					}
    				
    			});
    		}
    		
    	}
	}
	
	private void setCompPopChart() {
		
		LinkedList<Component> compsByPop = MainController.getRest().getPopularComponents();
    	if(compsByPop != null) {
    		XYChart.Series<String, Integer> s1 = new XYChart.Series<String, Integer>();
    		for(Component comp: compsByPop) {
    			XYChart.Data<String, Integer> data = new XYChart.Data<>(comp.getComponentName() + " (" +  componentsandAmounts.get(comp) + ")", componentsandAmounts.get(comp));
    			s1.getData().add(data);
    		}
    		compsPopChart.getData().addAll(s1);
    		compsPopChart.setAnimated(false);
    	}
	}
	
	private void setRevExpChart() {
		
		double total = MainController.getRest().revenueFromExpressDeliveries();
    	if(revnExpChart != null) {
    		XYChart.Series<String, Double> s1 = new XYChart.Series<String, Double>();
    		for(Customer cust: revenuByCust.keySet()) {
    			XYChart.Data<String, Double> data = new XYChart.Data<>("ID: " + cust.getId() + "(" + String.format("%.2f $", revenuByCust.get(cust)) + ")", revenuByCust.get(cust));
    			s1.getData().add(data);
    		}
    		revnExpChart.getData().addAll(s1);
    		revnExpChart.setAnimated(false);
    		revnExpChart.setTitle("Revenue from Express (Total Revenue: " + String.format("%.2f $", total) + ")");
    	}
	}
	
}
