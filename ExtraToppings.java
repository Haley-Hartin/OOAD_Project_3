
public class ExtraToppings extends ExtraDecorator{ //https://www.baeldung.com/java-decorator-pattern and Lecture 13
	
	public ExtraToppings(Roll roll, int numExtra) { //decorator pattern
		super(roll, numExtra);
	}
	
	@Override
	public double choosePrice(String rollType) { //https://www.tutorialspoint.com/java/switch_statement_in_java.htm
		switch(rollType) { 
			case "Egg Roll":
				setExtraName("Lemon Zest"); 
				return 0.10;
			case "Jelly Roll":
				setExtraName("Crumb");
				return 0.20;
			case "Pastry Roll":
				setExtraName("Whipped Cream");
				return 0.30;
			case "Sausage Roll":
				setExtraName("Cheese");
				return 0.40;
			case "Spring Roll":
				setExtraName("Nut");
				return 0.50;
			default:
				return 0;
		}
	}
	
	public String getOrderDescription() { //decorator pattern //add the topping to the rolls description
		return getRoll().getOrderDescription() + ", " + getNumExtra() + " " + getExtraName() + " Topping"; 
	}
}
