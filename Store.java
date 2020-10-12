import java.util.*;
import java.beans.*;

public abstract class Store {
	
	private int inventorySize;
	private HashMap<String, Integer> inventory;
	private List<String> menu;
	private PropertyChangeSupport support; //observer pattern //https://www.baeldung.com/java-observer-pattern
	private String status;
	
	public Store(int inventorySize, List<String> menu) {
		this.inventorySize = inventorySize;
		this.menu = menu;
		this.inventory = new HashMap<String, Integer>(); //https://www.geeksforgeeks.org/list-interface-java-examples/
		//https://stackoverflow.com/questions/6589744/how-to-return-a-list-of-keys-from-a-hash-map
		
		for(int i = 0; i < menu.size(); i++) {
			this.inventory.put(menu.get(i), inventorySize);
		}
		
		support = new PropertyChangeSupport(this);
		this.status = "<unk>";
	}
	
	public void restockInventory() { //https://beginnersbook.com/2013/12/hashmap-in-java-with-example/
		Set<Map.Entry<String, Integer>> set = this.inventory.entrySet();
		Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
	    while(iterator.hasNext()) {
	       Map.Entry<String, Integer> mentry = iterator.next(); 
	       if(mentry.getValue() == 0) {
	    	   this.inventory.put(mentry.getKey(), this.inventorySize);
	       }
	    }
	}
	
	public void checkInventory() { //https://beginnersbook.com/2013/12/hashmap-in-java-with-example/
		int zeroedInventory =0;
		Set<Map.Entry<String, Integer>> set = this.inventory.entrySet();
		Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
	    while(iterator.hasNext()) {
	       Map.Entry<String, Integer> mentry = iterator.next(); 
	       if(mentry.getValue() == 0) {
	    	   zeroedInventory += 1;
	       }
		}
	    if(zeroedInventory == this.menu.size()) {
	    	System.out.println("The Store has closed for the day due to sold out inventory.");
	    	this.setStatus("Closed");
	    }
	    else {
			this.setStatus("Open");
		}
	}

	public Roll createRoll(String order) {
		Roll roll = orderFromMenu(order);
		Random rand = new Random(); //https://www.geeksforgeeks.org/java-util-random-nextint-java/)
		int extraSauces = rand.nextInt(4); //0-3  extra sauces
		int extraFillings = rand.nextInt(2); //0-1 extra fillings
		int extraToppings = rand.nextInt(3); //0-2 extra toppings
		
		if(extraSauces > 0) { 
			roll = new ExtraSauces(roll, extraSauces);
		}
		if(extraFillings > 0) {
			roll = new ExtraFillings(roll, extraFillings);
		}
		if(extraToppings > 0) {
			roll = new ExtraToppings(roll, extraToppings);
		}
		
		return roll;
	}
	
	public abstract Roll orderFromMenu(String order);
	public abstract List<Customer> newCustomerLine();
    

	//public abstract void defaultStoreSettings();
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {//observer pattern //https://www.baeldung.com/java-observer-pattern
        support.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {//observer pattern //https://www.baeldung.com/java-observer-pattern
        support.removePropertyChangeListener(pcl);
    }
 
	public void setStatus(String status) { //https://www.baeldung.com/java-observer-pattern
		support.firePropertyChange("Store Status", "<unk>", status); //observer pattern
		System.out.println("HELLO LAST");
		this.status = status;
    }
    
    public void giveMenu() { //https://www.baeldung.com/java-observer-pattern
        support.firePropertyChange("Store Menu",  Arrays.asList("Nothing"), this.menu); //observer pattern
    }
    
    public void announceInventory() {
    	String inventoryReport = "The inventory has ";
    	for(int j = 0; j < this.menu.size(); j++) {
			String key = this.menu.get(j);
			inventoryReport = inventoryReport + this.inventory.get(key) + " "+ key + ", " ;
		}
    	System.out.println(inventoryReport + ".");
    }
<<<<<<< Updated upstream
	
	public void runStore(int days) {
=======

    public void checkout(List<String> order){
         for(int i = 0; i<order.size(); i++){
             Roll roll = createRoll(order.get(i));
             System.out.println(roll);
             //add a sales counter here 
         }
    }

    public void runStore(int days) {		
>>>>>>> Stashed changes
		for(int i = 1; i <= days; i++) {
			List<Customer> customerLine = newCustomerLine();
			System.out.println("It is day " + i + ".");
			this.announceInventory();
            this.giveMenu();
            this.checkInventory();
			for(int j = 0; j < customerLine.size(); j++) {
                Customer customer = customerLine.get(j);
                
                //issue with setting the menu here
                //menu gets set to null and causes errors 
				this.addPropertyChangeListener(customer); //observer pattern 
                customer.decideOrder();
                List<String> order = customer.getOrder();
                checkout(order);
			}
<<<<<<< Updated upstream
			this.giveMenu();
			this.checkInventory();
			
			//this.giveMenu();
=======
			
			
>>>>>>> Stashed changes
			
			
			
			
			for(int j = 0; j < customerLine.size(); j++) {
				this.removePropertyChangeListener(customerLine.get(j)); //observer pattern
			}
			
			//End of the day
			this.announceInventory();
			this.restockInventory();
			System.out.println("-----------------------------------------------------------");
		}
		
	}
}
