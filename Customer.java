import java.util.*;
import java.beans.*;

public abstract class Customer implements PropertyChangeListener{ //https://www.baeldung.com/java-observer-pattern
	
	private List<String> order;
	private String customerType;
	private List<String> menu;
	private String storeStatus;

	public Customer(String customerType) {
		this.customerType = customerType;
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
	
	public void observeMenu(List<String> menu) {
		this.menu = menu;
	}
	
	public void observeStoreStatus() {
		if(this.storeStatus == "Open") {
			this.decideOrder();
		}
		else if(this.storeStatus == "Closed") {
			this.order = null;
		}
		
	}
	
	public abstract void decideOrder();
	
	public void propertyChange(PropertyChangeEvent evt) { //observer pattern
		if(evt.getPropertyName().equals("Store Menu")) {
			this.observeMenu((List<String>) evt.getNewValue());
		}
		else if(evt.getPropertyName().equals("Store Status")) {
			this.storeStatus = (String) evt.getNewValue();
			this.observeStoreStatus();
		}
		
        
    }

}

