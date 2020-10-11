
public class ExtraSauces extends ExtraDecorator{ //https://www.baeldung.com/java-decorator-pattern and Lecture 13

	
	public ExtraSauces(Roll roll, int numExtra) {
		super(roll, numExtra);
	}

	@Override
	public double choosePrice(String rollType) {//https://www.tutorialspoint.com/java/switch_statement_in_java.htm
		switch(rollType) {
			case "Egg Roll":
				setExtraName("Soy");
				return 0.60;
			case "Jelly Roll":
				setExtraName("Berry");
				return 0.70;
			case "Pastry Roll":
				setExtraName("Chocolate");
				return 0.80;
			case "Sausage Roll":
				setExtraName("Worcestershire");
				return 0.90;
			case "Spring Roll":
				setExtraName("Sweet & Spicy");
				return 1.00;
			default:
				return 0;
		}
	}
	
	public String getOrderDescription() {
		return getRoll().getOrderDescription() + ", " + getNumExtra() + " " + getExtraName() + " Sauce"; 
	}
}
