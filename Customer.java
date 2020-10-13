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
		this.changedOrder = false;
		decideOrder();
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
	
	public HashMap<String, Integer> getHashMapOrder() {
		HashMap<String, Integer> numOrder = new HashMap<String, Integer>();
		if(getOrder() == null) {
			return null;
		}
		for(int i = 0; i < getOrder().size(); i++) {
			String key = getOrder().get(i);
			if(numOrder.get(key) == null) {
				numOrder.put(key, 0);
			}
			numOrder.put(key, numOrder.get(key) + 1);
		}
		
		return numOrder;
	}
	
	public boolean didCustomerChangeOrder() {
		return this.changedOrder;
	}
	
	public void orderHasChanged() {
		this.changedOrder = true;
	}
	
	public abstract void decideOrder();
	public abstract void changeOrder(int unfilledOrderIndex);

}

