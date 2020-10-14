
public class ExtraFillings extends ExtraDecorator{ //https://www.baeldung.com/java-decorator-pattern and Lecture 13
	
	public ExtraFillings(Roll roll, int numExtra) { //decorator pattern
		super(roll, numExtra);
	}
	
	@Override
	public double choosePrice(String rollType) { //https://www.tutorialspoint.com/java/switch_statement_in_java.htm
		switch(rollType) { 
			case "Egg Roll":
				setExtraName("Pork");
				return 1.10;
			case "Jelly Roll":
				setExtraName("Jelly");
				return 1.20;
			case "Pastry Roll":
				setExtraName("Cinnamon");
				return 1.30;
			case "Sausage Roll":
				setExtraName("Sausage");
				return 1.40;
			case "Spring Roll":
				setExtraName("Vegetable");
				return 1.50;
			default:
				return 0;
		}
	}
	
	public String getOrderDescription() { //decorator pattern //add the filling to the rolls description
		return getRoll().getOrderDescription() + ", " +  getNumExtra() + " " + getExtraName() + " Filling"; 
	}
}
