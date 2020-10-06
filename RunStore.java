import java.beans.*;
import java.io.*;
import java.util.*;

public class RunStore {
    public static void main(String[] args) throws FileNotFoundException {
        
        Store store = new Store(); //create menu items
        Sales sales = new Sales(0); //create sales object
        
        for (int i = 1; i < 31; i++) { //run program for 30 days
            
            store.openStore(); // set inventories 
            
            List<Menu> menuItems = store.getMenu(); //get menu items 

            //future implementation: randomly generate customer types and order 
            //below is just an example 
            Customer casualCustomer = new CasualCustomer(menuItems); 
            
            List<Menu> order =  casualCustomer.getOrder();
            
            sales.checkout(order);
            
  
            
        }
    }
}