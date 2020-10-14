import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CasualCustomer extends Customer{

	public CasualCustomer(List<String> menu) {
		super("Casual Customer", menu);
	}

	public void decideOrder() {
		//casual customer decides their order by deciding how many rolls they want (1-3) 
		Random rand = new Random(); ////https://www.geeksforgeeks.org/java-util-random-nextint-java/)
		int numRolls = rand.nextInt(3) + 1; //1-3 rolls
		List<String> order = new ArrayList<String>(); 
		for(int i = 0; i < numRolls; i++) { ////https://www.baeldung.com/java-random-list-element
			String type = getMenu().get(rand.nextInt(getMenu().size())); //each individual roll has a random type
			order.add(type);
		}
		
		setOrder(order);
 	}
	
	public void changeOrder(int unfilledOrderIndex) {
		orderHasChanged(); //raise flag that customer has changed thier order
		List<String> prevOrder = getOrder();
		List<String> newOrder = new ArrayList<String>();
		Random rand = new Random();
		
		//when a Casual customer is alerted to one of their desired rolls being gone, they have some decisions to make
		//since a casual customer is usually in a rush, they have different responses to how early the notification comes
		for(int i = 0; i < prevOrder.size(); i++) {//if the first roll on their order is gone, they will choose another roll to replace it
			if(unfilledOrderIndex == 0 && i == unfilledOrderIndex) {
				List<String> altMenu = new ArrayList<String>(getMenu());
				boolean wasRemoved = altMenu.remove(prevOrder.get(unfilledOrderIndex));
				if(wasRemoved) { //update their persistant knowledge of what roles are available 
					setMenu(altMenu);
				}
				String newRoll = altMenu.get(rand.nextInt(altMenu.size()));
				newOrder.add(newRoll);
			}
			else if(i == unfilledOrderIndex) { 
				//any roll that they are informed that is not avaialble and it is not the first roll, they will drop it from their order
				continue; //https://www.w3schools.com/java/java_break.asp
			}
			else { //they assume any other roll on their order is available unless otherwise informed
				newOrder.add(prevOrder.get(i));
			}
		}
		
		setOrder(newOrder);
	}
}

