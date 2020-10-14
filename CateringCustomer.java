import java.util.*;


public class CateringCustomer extends Customer{

	public CateringCustomer(List<String> menu) {
		super("Catering Customer", menu);
	}
	
	public void decideOrder() {
		Random rand = new Random();
		List<String> order = new ArrayList<String>(); 
		
		//catering customer decides 3 random different roll types, each getting 5 rolls wanted
		int numberOfTypes = 3; //https://www.baeldung.com/java-random-list-element
		int numOfEachType = 5;
		List<String> tempMenu = new ArrayList<String>(getMenu()); //https://stackoverflow.com/questions/7399482/java-lang-unsupportedoperationexception-at-java-util-abstractlist-removeunknown
	
	    for (int i = 0; i < numberOfTypes; i++) {
	        int randomIndex = rand.nextInt(tempMenu.size());
	        String rollType = tempMenu.get(randomIndex);
	        tempMenu.remove(randomIndex);  //Once a type is selected, it is going to be selected again (i.e. random selection w/o replacement)
	        for(int j = 0; j < numOfEachType; j++) { //add the new random roll type to the order 5 times to allow the creation of 5 different rolls
	        	order.add(rollType);
	        }
	        
	    }
		
		setOrder(order);
 	}
	
	public void changeOrder(int unfilledOrderIndex) {
		orderHasChanged(); //raise the flag that the customer has decided to change their order
		List<String> prevOrder = getOrder();
		List<String> newOrder = new ArrayList<String>();
		Random rand = new Random();
		
		//the catering customer, when alterted that one of their desired roles is not available 
		//will go and randomly select a new type from the ones that they think are available to replace the 1 that is not available
		//if they know that all the roll types are not avaialble, they will buy up the remainder (i.e. only 14 rolls left available, they will buy all 14)
		// they will buy upto a total of 15
		for(int i = 0; i < prevOrder.size(); i++) { 
			if(i == unfilledOrderIndex) {
				List<String> altMenu = new ArrayList<String>(getMenu());
				boolean wasRemoved = altMenu.remove(prevOrder.get(unfilledOrderIndex));
				if(wasRemoved) { //update the customers persistant knowledge of which roll types are available
					setMenu(altMenu);
				}
				if(altMenu.size() == 0) {
					continue;
				}
				String newRoll = altMenu.get(rand.nextInt(altMenu.size()));
				newOrder.add(newRoll);
			}
			else {
				newOrder.add(prevOrder.get(i)); //add their original wants to the revised order
			}
		}
		
		setOrder(newOrder);
	}

}
