import java.beans.*;
import java.io.*;
import java.util.*;

public class Menu{
    
    protected int cost;
    protected int inventory;
    protected String name;
   
    public boolean checkInventory(){  //check if item is out of stock
        if (this.inventory > 0){
            return true;
        }
        return false;
    }
    
    public void setInventory(int inventory){
        this.inventory = inventory;
    }
   
    
    public void updateInventory(){
        this.inventory = this.inventory - 5;
    }
    
    public int getCost(){
        return this.cost;
    }
    
    public int getInventory(){
        return this.inventory;
    }
     
    public String getName(){
        return this.name;
    }
}
    
