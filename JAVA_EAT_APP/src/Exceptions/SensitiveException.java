package Exceptions;

import Model.Customer;
import Model.Dish;

public class SensitiveException extends Exception {

	public SensitiveException(String customerName, String dishName) {
		super("Customer " + customerName + " is sensitive to one of the components in the dish " + dishName + "!");
		
	}
	
}

