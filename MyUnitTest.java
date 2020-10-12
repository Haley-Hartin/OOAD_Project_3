import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

public class MyUnitTest {

	 @Test
    public void testInstantiateBusinessCustomer() {
    	Customer business = new BusinessCustomer();

    	assertNotNull(business);

     } 

    @Test
    public void testInstantiateCasualCustomer() {
    	Customer casual = new CasualCustomer();

    	assertNotNull(casual);
     }  

     @Test
    public void testInstantiateCateringCustomer() {
    	Customer catering = new CateringCustomer();

    	assertNotNull(catering);
      }

    @Test
    public void testGetUniqueRoll() {
    	Roll pastryroll = new PastryRoll(2.00);

        ExtraFillings filling = new ExtraFillings(pastryroll,1);

    	assertNotSame(filling.choosePrice("Pastry Roll"), filling.choosePrice("Pastry Roll"));
      }


    @Test
    public void testchoosePrice() {
        Roll springroll = new SpringRoll(2.00);

        ExtraFillings filling = new ExtraFillings(springroll,1);

        double result = filling.choosePrice("Spring Roll");

        assertEquals(1.50,result,0.0);

    }

    @Test
    public void testExtraDecoratorWithSaucesAndEggRoll() {
        Roll testRoll = new ExtraSauces(new EggRoll(1.00), 1);

        double result = testRoll.cost();

        assertEquals(1.60,result,0.0);

    }

    @Test
    public void testExtraDecoratorWithFillingsAndEggRoll() {
        Roll testRoll2 = new ExtraFillings(new EggRoll(1.00), 1);

        double result = testRoll2.cost();

        assertEquals(2.10,result,0.0);

    }

    @Test
    public void testExtraDecoratorWithToppingsAndEggRoll() {
        Roll testRoll3 = new ExtraToppings(new EggRoll(1.00), 1);

        double result = testRoll3.cost();

        assertEquals(1.10,result,0.0);

    }

    @Test
    public void testExtraDecoratorWithToppingsFillingsSaucesAndSpringRoll() {
        Roll testRoll4 = new ExtraToppings(new ExtraSauces(new ExtraFillings(new SpringRoll(2.00), 1), 1), 1);

        double result = testRoll4.cost();

        assertEquals(5.00,result,0.0);

    }

    @Test
    public void testgetOrderDescriptionJellyRollAndSauce() {
        Roll jellyroll = new JellyRoll(2.00);

        ExtraSauces sauces = new ExtraSauces(jellyroll,2);

        sauces.choosePrice("Jelly Roll");

        String result = sauces.getOrderDescription();

        String expected = "Jelly Roll, 2 Berry Sauce";

        assertEquals(expected,result);
    }

    @Test
    public void testgetOrderDescriptionSausageRollAndTopping() {
        Roll sausageRoll = new SausageRoll(1.50);

        ExtraToppings toppings = new ExtraToppings(sausageRoll,3);

        toppings.choosePrice("Sausage Roll");

        String result = toppings.getOrderDescription();

        String expected = "Sausage Roll, 3 Cheese Topping";

        assertEquals(expected,result);
    }

    @Test
    public void testgetOrderDescriptionPastryAndFilling() {
        Roll pastryRoll = new PastryRoll(2.00);

        ExtraFillings fillings = new ExtraFillings(pastryRoll,1);

        fillings.choosePrice("Pastry Roll");

        String result = fillings.getOrderDescription();

        String expected = "Pastry Roll, 1 Cinnamon Filling";

        assertEquals(expected,result);
    }
}