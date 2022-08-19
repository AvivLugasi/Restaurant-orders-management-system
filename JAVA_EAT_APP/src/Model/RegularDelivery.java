package Model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class RegularDelivery extends Delivery {
	@Override
	public String toString() {
		String ids = "";
		for(Order o : orders) {
			ids += o.getId() + " ";
		}
		return super.toString() + "\n" +"[orders id: " + ids + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeSet<Order> orders;
	
	public RegularDelivery(DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered,LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		this.orders = new TreeSet<>();
	}

	public RegularDelivery(TreeSet<Order> orders, DeliveryPerson deliveryPerson, DeliveryArea area,
			boolean isDelivered,LocalDate deliveredDate) {
		super(deliveryPerson, area, isDelivered, deliveredDate);
		this.orders = orders;
	}
	
	public RegularDelivery(int id) {
		super(id);
	}

	public SortedSet<Order> getOrders() {
		return Collections.unmodifiableSortedSet(orders);
	}
	
	public void setOrders(TreeSet<Order> orders) {
		this.orders = orders;
	}
	
	//Methods
	
	public boolean addOrder(Order o) {
		return orders.add(o);
	}
	
	public boolean removeOrder(Order o) {
		return orders.remove(o);
	}

}

