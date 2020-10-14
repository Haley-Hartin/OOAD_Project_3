import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

public abstract class CustomerLine implements PropertyChangeListener {
	
	private String storeStatus; 
	private List<Customer> line;
	private int currentCustomer;
	private List<String> storeMenu;

	public CustomerLine(List<String> menu) {
		this.storeMenu = menu;
		this.currentCustomer = -1;
		this.line = getCustomerLine(); //create the random line of customers
	}
	
	public abstract List<Customer> getCustomerLine();
	
	public void propertyChange(PropertyChangeEvent evt) { //observer pattern
		if(evt.getPropertyName().equals("Store Status")) {
			this.storeStatus = (String) evt.getNewValue();
		}
    }
	
	public Customer getCurrentCustomer() {
		//only get the next customer from the line if the store is open
		if(this.storeStatus == "Open") {
			return this.line.get(this.currentCustomer);
		}
		else {
			return null;
		}
	}
	
	public boolean serveNextCustomer() {
		if(this.storeStatus == "Open" && this.currentCustomer + 1 < this.line.size()) { //if there are still costomers to serve and store is still open
			this.currentCustomer += 1; //move on to the next customer
			return true;
		}
		else { //if the store closes early or there are no more customers to serve, then end line
			this.line = null;
			return false;
		}
	}
	
	public List<String> getStoreMenu(){
		return this.storeMenu;
	}
	
	public void printLine() { //for debugging
		System.out.println(this.line);
	}
	
	public void printNumCustomerTypes() { //track the number of each customer type in the line
		//mainly used for debugging
		HashMap<String, Integer> numTypes = new HashMap<String, Integer>();
		for(int i = 0; i < this.line.size(); i++) {
			String key = line.get(i).getCustomerType();
			if(numTypes.get(key) == null) {
				numTypes.put(key, 0);
			}
			numTypes.put(key, numTypes.get(key) + 1);
		}
		System.out.println("Types of customers in line: " + numTypes);
	}

}
