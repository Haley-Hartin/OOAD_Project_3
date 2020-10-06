import java.util.Random;
import java.beans.*;
import java.io.*;
import java.util.*;

public class CasualCustomer extends Customer {
    
    private List<Menu> order = new ArrayList<>();
    private List<Menu> menuItems;
    
    public CasualCustomer(List<Menu> menuItems){
        this.menuItems = menuItems;
    }
    
    public List<Menu> getOrder(){
        
        Random rand = new Random();
        boolean valid_order = false;
        
        while (!valid_order){ //keep ordering until a valid order is made
            
            order.clear(); //reset order 
            int number_of_rolls = rand.nextInt(3) + 1; //number of rolls 1-3
            int roll_type_index = rand.nextInt(menuItems.size());  //get index for roll type
            Menu roll_type = menuItems.get(roll_type_index); //get roll type
            
            if(roll_type.getInventory()<1){ //if the wanted roll is out of stock
                valid_order = false;
            }
            
            else if (roll_type.getInventory() < number_of_rolls){
                for (int i = 0; i<roll_type.getInventory(); i++){ //if there are less rolls availiable than wanted
                   order.add(roll_type);
                   valid_order = true;
                }
            }
                
            else {
               for (int i = 0; i<number_of_rolls; i++){ //if there are enough rolls for the order
                   order.add(roll_type);
                   valid_order = true;
                }
            }
            
            
        }
        return order;
    }
    
}