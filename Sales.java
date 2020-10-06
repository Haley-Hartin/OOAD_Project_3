import java.beans.*;
import java.io.*;
import java.util.*;

public class Sales{
     
     private double sales;
     
     public Sales(int sales){
         this.sales = sales;
     }
    
     public void updateSales(int price){
         this.sales = this.sales + price;
     }
    
     public double getSales(){
         return this.sales;
     }
     
     public void checkout(List<Menu> order){
         for(int i = 0; i<order.size(); i++){
             int cost = (order.get(i)).getCost();
             updateSales(cost); //update sales
             (order.get(i)).updateInventory(); //update inventory
         }
     }
}