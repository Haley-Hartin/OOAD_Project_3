import java.util.*;
import java.beans.*;

public abstract class Customer { //https://www.baeldung.com/java-observer-pattern
	
	private List<String> order;
	private String customerType;
	private List<String> menu;
	private String storeStatus;
	private boolean changedOrder;

	public Customer(String customerType, List<String> menu) {
		this.customerType = customerType;
		this.menu = menu;
		this.changedOrder = false; //customer has NOT changed their order initally
		decideOrder(); //when customer arrives at store, they will decide their initial order
	}
	
	public List<String> getOrder(){
		return this.order;
	}
	
	public void setOrder(List<String> order) {
		this.order = order;
	}
	
	public String getCustomerType(){
		return this.customerType;
	}
	
	public List<String> getMenu(){
		return this.menu;
	}
	
	public void setMenu(List<String> menu) {
		this.menu = menu;
	}
	
	public HashMap<String, Integer> getHashMapOrder() { //used to get a summary of how many of each roll type a customer has ordered
		HashMap<String, Integer> numOrder = new HashMap<String, Integer>();
		if(getOrder() == null) { //if there is no order, there is no summary
			return null;
		}
		for(int i = 0; i < getOrder().size(); i++) {
			String key = getOrder().get(i);
			if(numOrder.get(key) == null) { //check to see if the roll type is in the summary
				numOrder.put(key, 0); //if not, add it
			}
			numOrder.put(key, numOrder.get(key) + 1); //increase the count for roll type
		}
		
		return numOrder;
	}
	
	public boolean didCustomerChangeOrder() { //checks if the customer did in fact change their original order
		return this.changedOrder;
	}
	
	public void orderHasChanged() { //indicates that the order has changed
		this.changedOrder = true; //used in changeOrder(int)
	}
	
	public abstract void decideOrder(); 
	public abstract void changeOrder(int unfilledOrderIndex);

}

