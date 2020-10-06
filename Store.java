import java.beans.*;
import java.io.*;
import java.util.*;

public class Store{
    
    public List<Menu> menuItems = new ArrayList<>();
    
    public Store(){

        Menu eggroll = new EggRoll(2);
        Menu springroll = new SpringRoll(3);
        Menu pastryroll = new PastryRoll(3);
        Menu sausageroll = new SausageRoll(2);
        Menu jellyroll = new JellyRoll(3);
        
        menuItems.add(eggroll);
        menuItems.add(springroll);
        menuItems.add(pastryroll);
        menuItems.add(sausageroll);
        menuItems.add(jellyroll);
    }
    
    public void openStore(){
        
        //set all the items that ran out of inventory the previous day to 30
        for (int i = 0; i< menuItems.size(); i++){
            
            if( !(menuItems.get(i)).checkInventory() ){
                System.out.println(menuItems.get(i).getName() + " out of stock");
                (menuItems.get(i)).setInventory(30);
            }
        }
        
    }
    

    public List<Menu> getMenu(){
        return menuItems;
    }
        
    

}