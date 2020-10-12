import java.util.*;
import java.beans.*;

public abstract class Store {
	
	private int inventorySize;
	private HashMap<String, Integer> inventory;
    private HashMap<String, Integer> dailyInventory;
	private List<String> menu;
	private PropertyChangeSupport support; //observer pattern //https://www.baeldung.com/java-observer-pattern
	private String status;
    private double sales;
    private double daySales;
	
	public Store(int inventorySize, List<String> menu) {
		this.inventorySize = inventorySize;
		this.menu = menu;
        this.sales = 0;
		this.inventory = new HashMap<String, Integer>(); //https://www.geeksforgeeks.org/list-interface-java-examples/
		//https://stackoverflow.com/questions/6589744/how-to-return-a-list-of-keys-from-a-hash-map
        this.dailyInventory = new HashMap<String, Integer>(); 
		
		for(int i = 0; i < menu.size(); i++) {
			this.inventory.put(menu.get(i), inventorySize);
            this.inventory.put(menu.get(i), 0);
		}
		
		support = new PropertyChangeSupport(this);
		this.status = "<unk>";
	}
	
	public void restockInventory() { //https://beginnersbook.com/2013/12/hashmap-in-java-with-example/
		Set<Map.Entry<String, Integer>> set = this.inventory.entrySet();
		Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
	    while(iterator.hasNext()) {
	       Map.Entry<String, Integer> mentry = iterator.next(); 
           this.dailyInventory.put(mentry.getKey(), 0);
	       
            if(mentry.getValue() == 0) { //if the inventory for a roll ran out
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
    public void updateInventory(String order) {
        int currAmount = this.inventory.get(order);
        
        if (currAmount -1 <0){
            currAmount = 1;
        }
        this.inventory.replace(order, currAmount -1); //update the inventory left for the roll 
        
        int dailyAmount = this.inventory.get(order); //update the amount of each roll ordered for the day
        this.dailyInventory.replace(order, currAmount +1);
    }

	public Roll createRoll(String order) {
        updateInventory(order); //update inventory 
		
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
        System.out.println(roll.getOrderDescription());
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
		this.status = status;
    }
    
    public void giveMenu() { //https://www.baeldung.com/java-observer-pattern
        support.firePropertyChange("Store Menu",  Arrays.asList("Nothing"), this.menu); //observer pattern
    }
    
    public void announceInventory() {
    	String inventoryReport = "The inventory has ";
        String dailySales = "Rolls sold today: ";
    	for(int j = 0; j < this.menu.size(); j++) {
			String key = this.menu.get(j);
			inventoryReport = inventoryReport + this.inventory.get(key) + " "+ key + ", " ;
            dailySales = dailySales + this.dailyInventory.get(key) + " "+ key + ", " ;
		}
    	System.out.println(inventoryReport + ".");
        System.out.println(dailySales + ".");
    }


    public double checkout(List<String> order){
         double orderCost = 0;
         System.out.println("Order:");
         for(int i = 0; i<order.size(); i++){
             Roll roll = createRoll(order.get(i));
             orderCost += roll.cost();
         }
         this.sales += orderCost; 
         this.daySales += orderCost;
         System.out.println("Order total: " + orderCost);
         System.out.println("-------");
         return orderCost;
    }

    public void runStore(int days) {		
        StoreInfo tracker = new StoreInfo();
		for(int i = 1; i <= days; i++) {
            this.daySales = 0;
			List<Customer> customerLine = newCustomerLine();
			System.out.println("It is day " + i + ".");
			this.announceInventory();
            this.giveMenu();
            this.checkInventory();
			for(int j = 0; j < customerLine.size(); j++) {
                Customer customer = customerLine.get(j);
                
                addPropertyChangeListener(customer); //observer pattern 
                customer.decideOrder();
                List<String> order = customer.getOrder();
                double total = this.checkout(order);
                tracker.setOrder(customer.getCustomerType(), total); // update sales from each customer
			}
            this.checkInventory();

			for(int j = 0; j < customerLine.size(); j++) {
				this.removePropertyChangeListener(customerLine.get(j)); //observer pattern
			}
			
			//End of the day
            System.out.println("-----Todays Sales Data-----");
            System.out.println("Total Sales: "+ this.sales);
            
            tracker.announceCustomerOrders();
            System.out.println("    The Day's sales: " + this.daySales);
            
			this.announceInventory(); 
			this.restockInventory();
            tracker.resetOrderTotals();
			System.out.println("-----------------------------------------------------------");
		}
		
	}
}
