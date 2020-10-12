import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.beans.*;
import java.util.*;


public class StoreInfo{ //class to track sales by customer group
    
    private HashMap<String, Double> CustomerOrders; //track payment by customer type
	
    public StoreInfo() {
       this.CustomerOrders = new HashMap<String, Double>();
       this.CustomerOrders.put("Casual Customer", 0.0);
       this.CustomerOrders.put("Business Customer", 0.0);
       this.CustomerOrders.put("Catering Customer", 0.0);
    }
	
    public void setOrder(String Customer, double orderTotal){
        double currTotal = this.CustomerOrders.get(Customer);
        this.CustomerOrders.replace(Customer, currTotal + orderTotal);
    }
    
    public void resetOrderTotals(){
       this.CustomerOrders.replace("Casual Customer", 0.0);
       this.CustomerOrders.replace("Business Customer", 0.0);
       this.CustomerOrders.replace("Catering Customer", 0.0);
    }
    
    public HashMap<String, Double> getCustomerOrders(){
       return this.CustomerOrders;
    }
    
    public void announceCustomerOrders(){
      
    	System.out.println("    Sales from Casual Customer's: " + this.CustomerOrders.get("Casual Customer"));
        System.out.println("    Sales from Business Customer's: " + this.CustomerOrders.get("Business Customer"));
        System.out.println("    Sales from Catering Customer's: " + this.CustomerOrders.get("Catering Customer"));
    }
}
    