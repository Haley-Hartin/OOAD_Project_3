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
		
		support = new PropertyChangeSupport(this);
		this.status = "<unk>";
		this.cashRegister = new CashRegister(Arrays.asList("Catering Customer", "Business Customer", "Casual Customer"), this.menu);
	}
	
	
	public void updateStoreStatus() {
		if(this.inventory.checkInventoryForEmptiedStock() == this.inventory.getNumItemsInInvetory()) {
	    	System.out.println("The Store has closed for the day due to sold out inventory.");
	    	this.setStatus("Closed");
	    }
	    else {
	    	this.setStatus("Open");
	    }
	}
	

	public Roll createRoll(String order) {
		if(!this.inventory.stockAvailable(order)) {
			return null;
		}
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
		
		this.inventory.reduceStock(order);
		
		return roll;
	}
	
	
	public abstract Roll orderFromMenu(String order);
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
    
    
    
    public List<Roll> serveCustomer(Customer customer){
    	List<String> customerOrder = customer.getOrder();
		List<Roll> customerRolls = new ArrayList<Roll>();
		
		while(true) {
			for(int k = customerRolls.size(); k < customerOrder.size(); k++) {
				String rollType = customerOrder.get(k);
				Roll roll = createRoll(rollType);
				if(roll == null) {
					customer.changeOrder(k);
					customerOrder = customer.getOrder();
					break;
				}
				else 	{
					customerRolls.add(roll);
				}
			}
			
			if(customerOrder == null) {
				for(int r = 0; r < customerRolls.size(); r++) {
					String unpurchasedRoll = customerRolls.get(r).getRollType();
					this.inventory.increaseStock(unpurchasedRoll);
				}
				customerRolls = null;
				break;
			}
			else if(customerRolls.size() == customerOrder.size()) {
				break;
			}
		}
		return customerRolls;
    }
    
    
	public void runStore(int days) {	
		for(int i = 1; i <= days; i++) {
			CustomerLine customerLine = newCustomerLine();
			System.out.println("It is day " + i + ".");
			this.inventory.announceInventory();
			this.addPropertyChangeListener(customerLine); //observer pattern
			setStatus("Open");
			
			while(customerLine.serveNextCustomer()) {
				this.updateStoreStatus();
				Customer currentCustomer = customerLine.getCurrentCustomer();
								
				List<Roll> customerRolls = serveCustomer(currentCustomer);
				this.cashRegister.ringUpCustomer(currentCustomer, customerRolls);
				
				
				this.updateStoreStatus();
				
			}
				
			this.removePropertyChangeListener(customerLine); //observer pattern
			
			//End of the day
			this.cashRegister.reportDailyStats(i);
			this.inventory.announceInventory(); 
			this.inventory.restockInventory();
			this.setStatus("Closed");
			System.out.println("-----------------------------------------------------------");
		}
		
		this.cashRegister.reportTotalStats();
	}
	
	public CashRegister getCashReigster() {
		return this.cashRegister;
	}
	
	public int getInventorySize() {
		return this.inventory.getInventorySize();
	}
}
