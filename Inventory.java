import java.util.*;

public class Inventory {
	
	private int inventorySize;
	private HashMap<String, Integer> inventory;

	public Inventory(int inventorySize, List<String> keys) {
		this.inventorySize = inventorySize;
		
		this.inventory = new HashMap<String, Integer>(); //https://www.geeksforgeeks.org/list-interface-java-examples/
		//https://stackoverflow.com/questions/6589744/how-to-return-a-list-of-keys-from-a-hash-map
		
		for(int i = 0; i < keys.size(); i++) {
			this.inventory.put(keys.get(i), inventorySize); //stock the inventory with each inventory item to (inventorySize)
		}
	}
	
	public void restockInventory() { //https://beginnersbook.com/2013/12/hashmap-in-java-with-example/
		String restockMessage = "The inventory was restocked with";
		int restockedInventory = 0;
		
		Iterator<Map.Entry<String, Integer>> iterator = this.inventory.entrySet().iterator();
	    while(iterator.hasNext()) { //restock an inventory item to the max inventory level if its quantity is 0
	       Map.Entry<String, Integer> mentry = iterator.next(); 

	       if(mentry.getValue() == 0) {
	    	   this.inventory.put(mentry.getKey(), this.inventorySize);
	    	   restockMessage = restockMessage + " " + this.inventorySize + " " + mentry.getKey() + "(s),";
	    	   restockedInventory += 1;
	       }
	    }
	    
	    if(restockedInventory > 0) { //if any inventory item was restocked, print what wass restocked
	    	restockMessage = restockMessage.substring(0, restockMessage.length()-1);
		    System.out.println(restockMessage + ".");
	    }
	    else {
	    	System.out.println("No inventory was restocked.");
	    }
	}
	
	public int checkInventoryForEmptiedStock() { 	    
	    List<Integer> inventoryValues = new ArrayList<Integer>(this.inventory.values()); 
	    //get how many inventory items stock is gone
	    int zeroedInventory = Collections.frequency(inventoryValues, 0);//https://www.geeksforgeeks.org/java-util-collections-frequency-java-examples/

	    return zeroedInventory;
	}
	
	public void announceInventory() { //prints out the inventory levels of all individual iventory items
    	String inventoryReport = "The inventory has: ";
    	for(int j = 0; j < this.inventory.size(); j++) {
			String key = new ArrayList<>(this.inventory.keySet()).get(j);
			inventoryReport = inventoryReport +" " + this.inventory.get(key) + " " + key + "(s)," ;
		}
    	inventoryReport = inventoryReport.substring(0, inventoryReport.length()-1);
    	System.out.println(inventoryReport + ".");
    }
	
	public boolean stockAvailable(String key) {
		if(this.inventory.get(key) == 0) { //check if the inventory is gone
			return false;
		}
		else {
			return true;
		}
	}
	
	//reduce stock of an inventory item when item is ordered
	public void reduceStock(String key) {
		this.inventory.put(key, this.inventory.get(key) - 1);
	}
	
	//increase stock in case order cant be fufilled 
	public void increaseStock(String key) {
		this.inventory.put(key, this.inventory.get(key) + 1);
	}
	
	public int getNumItemsInInvetory() {
		return this.inventory.size();
	}

	public int getInventorySize() {
		return this.inventorySize;
	}
}
