import java.util.*;
import java.beans.*;

public abstract class Store {
	
	
	private Inventory inventory;
	private List<String> menu;
	private PropertyChangeSupport support; //observer pattern //https://www.baeldung.com/java-observer-pattern
	private String status;
	private CashRegister cashRegister;
	
	public Store(int inventorySize, List<String> menu) {
		this.menu = menu;                                        
		this.inventory = new Inventory(inventorySize, menu);
		
		support = new PropertyChangeSupport(this); //observer pattern
		this.status = "<unk>";
		this.cashRegister = new CashRegister(Arrays.asList("Catering Customer", "Business Customer", "Casual Customer"), this.menu);
	}
	
	
	public void updateStoreStatus() { //check the inventory
		if(this.inventory.checkInventoryForEmptiedStock() == this.inventory.getNumItemsInInvetory()) { //an empty inventory causes the store to close
	    	System.out.println("The Store has closed for the day due to sold out inventory.");
	    	this.setStatus("Closed");//observer pattern
	    }
	    else { //the store will remain open otherwise
	    	this.setStatus("Open"); //observer pattern
	    }
	}
	

	public Roll createRoll(String order) {  //create the roll and extras for the order
		if(!this.inventory.stockAvailable(order)) { //make sure there is inventory for a roll
			return null;
		}
		Roll roll = orderFromMenu(order); //factory pattern
		Random rand = new Random(); //https://www.geeksforgeeks.org/java-util-random-nextint-java/)
		//randomly decide on extras for 1 roll
		int extraSauces = rand.nextInt(4); //0-3  extra sauces
		int extraFillings = rand.nextInt(2); //0-1 extra fillings
		int extraToppings = rand.nextInt(3); //0-2 extra toppings
		
		//add those extras to the roll if the customer decides to put them on their roll
		if(extraSauces > 0) { 
			roll = new ExtraSauces(roll, extraSauces);
		}
		if(extraFillings > 0) {
			roll = new ExtraFillings(roll, extraFillings);
		}
		if(extraToppings > 0) {
			roll = new ExtraToppings(roll, extraToppings);
		}
		
		this.inventory.reduceStock(order); //update inventory to reflect roll creation
		
		return roll;
	}
	
	
	public abstract Roll orderFromMenu(String order); //factory pattern
	public abstract CustomerLine newCustomerLine();
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {//observer pattern //https://www.baeldung.com/java-observer-pattern
        support.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {//observer pattern //https://www.baeldung.com/java-observer-pattern
        support.removePropertyChangeListener(pcl);
    }
 
    public void setStatus(String status) { //https://www.baeldung.com/java-observer-pattern
        support.firePropertyChange("Store Status", this.status, status); //observer pattern
        this.status = status;
    }
    
    public List<String> getMenu() { 
    	return this.menu;
    } 
    
    
    public List<Roll> serveCustomer(Customer customer){  //take the customers order and create the customers order
    	List<String> customerOrder = customer.getOrder();
		List<Roll> customerRolls = new ArrayList<Roll>();
		
		while(true) {
			for(int k = customerRolls.size(); k < customerOrder.size(); k++) {
				String rollType = customerOrder.get(k);
				Roll roll = createRoll(rollType); //decide if ther is extras with the roll 
				if(roll == null) {  //if there is a roll outage, //tell customer which roll in their order cannot be filled
					customer.changeOrder(k); //customer changes their order
					customerOrder = customer.getOrder(); //update the customer order from customer
					break;
				}
				else 	{
					customerRolls.add(roll);
				}
			}
			
			if(customerOrder == null) { //the customer has decided not to make a purchase
				for(int r = 0; r < customerRolls.size(); r++) { //add the rolls back to the inventory
					String unpurchasedRoll = customerRolls.get(r).getRollType();
					this.inventory.increaseStock(unpurchasedRoll);
				}
				customerRolls = null; //no rolls created for customer
				break;
			}
			else if(customerRolls.size() == customerOrder.size()) { //the customer is satisfied with their order, they are ready to checkout
				break;
			}
		}
		return customerRolls;
    }
    
    
	public void runStore(int days) {	
		for(int i = 1; i <= days; i++) { //run the store for x amount of days
			//beginning of day
			CustomerLine customerLine = newCustomerLine();
			System.out.println("It is day " + i + ".");
			this.inventory.announceInventory();
			this.addPropertyChangeListener(customerLine); //observer pattern
			setStatus("Open");
			
			//during the day
			while(customerLine.serveNextCustomer()) { //serve the costomers in line until there are no more or the store closes early
				this.updateStoreStatus();
				Customer currentCustomer = customerLine.getCurrentCustomer(); //get the next customer from the line
								
				List<Roll> customerRolls = serveCustomer(currentCustomer);  //create the Rolls from the customers order 
				this.cashRegister.ringUpCustomer(currentCustomer, customerRolls); //checkout the customer with their order
				
				this.updateStoreStatus(); //check if store needs to close due to roll outages
				
			}
				
			this.removePropertyChangeListener(customerLine); //observer pattern
			
			//End of the day
			this.cashRegister.reportDailyStats(i);
			this.inventory.announceInventory(); 
			this.inventory.restockInventory();
			this.setStatus("Closed");
			System.out.println("-----------------------------------------------------------"); //for seperation purposes
		}
		
		this.cashRegister.reportTotalStats(); //at the end of the simulation, report the total stats
	}
	
	public CashRegister getCashReigster() {
		return this.cashRegister;
	}
	
	public int getInventorySize() {
		return this.inventory.getInventorySize();
	}
}
