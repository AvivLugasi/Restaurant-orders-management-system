package Model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import Utils.Neighberhood;

public class DeliveryArea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int idCounter = 1;
	private int id;
	private String areaName;
	private HashSet<DeliveryPerson> delPersons;
	private HashSet<Delivery> delivers;
	private HashSet<Neighberhood> neighberhoods;
	private final int deliverTime;
	
	public DeliveryArea(String areaName, HashSet<Neighberhood> neighberhoods, int deliverTime) {
		super();
		this.id = idCounter++;
		this.areaName = areaName;
		this.neighberhoods = neighberhoods;
		this.deliverTime = deliverTime;
		delPersons = new HashSet<>();
		delivers = new HashSet<>();
	}
	
	public DeliveryArea(int id) {
		this.id = id;
		this.deliverTime = 0;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		DeliveryArea.idCounter = idCounter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Set<DeliveryPerson> getDelPersons() {
		return Collections.unmodifiableSet(delPersons);
	}

	public Set<Delivery> getDelivers() {
		return Collections.unmodifiableSet(delivers);
	}

	public Set<Neighberhood> getNeighberhoods() {
		return Collections.unmodifiableSet(neighberhoods);
	}

	public int getDeliverTime() {
		return deliverTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeliveryArea other = (DeliveryArea) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JAVA-EAT ID: " + id + "\n" + "Name:" + areaName + "\n" + "Neighberhoods: " + neighberhoods
				+ "\n" + "Delivery time: " + deliverTime + "\n" + "Delivery Persons:" + delPersons;
	}
	
	//methods
	public boolean addDelPerson(DeliveryPerson dp) {
		if(dp.getArea() != null) {
			dp.getArea().removeDelPerson(dp);
		}
		dp.setArea(this);
		return delPersons.add(dp);
	}
	
	public boolean removeDelPerson(DeliveryPerson dp) {
		if(dp != null)
			dp.setArea(null);
		return delPersons.remove(dp);
	}
	
	public boolean addDelivery(Delivery d) {
		if(d.getArea() != null) {
			d.getArea().removeDelivery(d);
		}
		d.setArea(this);
		return delivers.add(d);
	}
	
	public boolean removeDelivery(Delivery d) {
		if(d != null)
			d.setArea(null);
		return delivers.remove(d);
	}
	
	public boolean addNeighberhood(Neighberhood neighberhood) {
		return neighberhoods.add(neighberhood);
	}
	
	public boolean removeNeighberhood(Neighberhood neighberhood) {
		return neighberhoods.remove(neighberhood);
	}
}

