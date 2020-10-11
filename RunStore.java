import java.util.*;

public class RunStore {

	public static void main(String[] args) {
		
		//Store store = new GourmetStore(30);
		//Roll roll = store.fullfillOrder("Egg Roll");
		
		Roll roll2 = new ExtraSauces(new ExtraFillings(new EggRoll(2.00), 1), 1);
		Roll roll = new EggRoll(2.00);
		roll = new ExtraFillings(roll, 1);
		roll = new ExtraSauces(roll, 1);
		roll = new ExtraToppings(roll, 1);
		
		System.out.println(roll.cost());
		System.out.println(roll.getOrderDescription());
		System.out.println(roll2.cost());
		System.out.println(roll2.getOrderDescription());
		
		//List<Customer> line = store.newCustomerLine();
		//System.out.println(line);
		
//		Customer c1 = new CasualCustomer();
//		Customer c2 = new BusinessCustomer();
//		Customer c3 = new CateringCustomer();
//		
//		c1.setMenu(Arrays.asList("Egg Roll", "Jelly Roll", "Pastry Roll", "Sausage Roll", "Spring Roll"));
//		c2.setMenu(Arrays.asList("Egg Roll", "Jelly Roll", "Pastry Roll", "Sausage Roll", "Spring Roll"));
//		c3.setMenu(Arrays.asList("Egg Roll", "Jelly Roll", "Pastry Roll", "Sausage Roll", "Spring Roll"));
//		
//		c1.decideOrder();
//		c2.decideOrder();
//		c3.decideOrder();
//		
//		System.out.println(c1.getOrder());
//		System.out.println(c2.getOrder());
//		System.out.println(c3.getOrder());
		
	}

}
