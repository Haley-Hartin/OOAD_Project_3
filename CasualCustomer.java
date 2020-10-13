import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CasualCustomer extends Customer{

	public CasualCustomer(List<String> menu) {
		super("Casual Customer", menu);
	}

	public void decideOrder() {
		Random rand = new Random(); ////https://www.geeksforgeeks.org/java-util-random-nextint-java/)
		int numRolls = rand.nextInt(3) + 1; //1-3 rolls
		List<String> order = new ArrayList<String>(); 
		for(int i = 0; i < numRolls; i++) { ////https://www.baeldung.com/java-random-list-element
			String type = getMenu().get(rand.nextInt(getMenu().size()));
			order.add(type);
		}
		
		setOrder(order);
 	}
	
	public void changeOrder(int unfilledOrderIndex) {
		orderHasChanged();
		List<String> prevOrder = getOrder();
		List<String> newOrder = new ArrayList<String>();
		Random rand = new Random();
		
		for(int i = 0; i < prevOrder.size(); i++) {
			if(unfilledOrderIndex == 0 && i == unfilledOrderIndex) {
				List<String> altMenu = new ArrayList<String>(getMenu());
				boolean wasRemoved = altMenu.remove(prevOrder.get(unfilledOrderIndex));
				if(wasRemoved) {
					setMenu(altMenu);
				}
				String newRoll = altMenu.get(rand.nextInt(altMenu.size()));
				newOrder.add(newRoll);
			}
			else if(i == unfilledOrderIndex) {
				continue; //https://www.w3schools.com/java/java_break.asp
			}
			else {
				newOrder.add(prevOrder.get(i));
			}
		}
		
		setOrder(newOrder);
	}
}

