# OOAD_Project_3

## Group Members: Haley Drexel, Blythe Waltman, Haley Hartin

1. Language and Environment

   * Java 8 in Eclipse IDE

2. Notes

   * There are 3 output files for this assignment. Each for the 30, 45, 60 inventory level. They will be titled output_Inventory_##.txt
   * Results for the JUnit Tests can be found at the bottom of each of the 3 output files
   * The Observer Pattern can be found in Store.java and CustomerLine.java
   * The Factory Pattern can be found in Store.java and GourmetStore.java (which is the factory in this case)
   * The Decorator Pattern can be found in ExtraDecorator.java, ExtraSauces.java, ExtraFillings.java, ExtraToppings.java
   * Bonus: Iterator Pattern can be found in CashReigster.java

3. Assumptions Made

   * The randomness probability of something being chosen (i.e. how many customers per customer type, number of extras etc.) is assumed to be equally probable across the entire required domain.
   * As per the reading in the document "Casual customers will buy 1 to 3 rolls (type determined randomly)." This has been determined to mean that each of the 1-3 rolls will have a randomly determined type (i.e. a Casual customer will order a 2 rolls and the roll types are an Egg Roll and Spring Roll). This is assumed that this reading will also allow for the Casual customer to order 2+ of the same roll type (i.e. a casual customer orders 3 rolls and the types are 2 Pastry Rolls and 1 Sausage roll).
   * Assumed that when a customer does not make a purchase (will only be Business Customers), the Rolls created will go back into the inventory. Assumed that the the object rolls being created are not the actual rolls, so if an order is cancelled, the inventory is updated to show no change in the inventory after the customer leaves without purchasing.
   * Assumed that when a customer finds out a roll is not available, they will have persistant knowledge/remember that it is not available in case they need to change their order again in regards to another roll being out.
   * With the first assumption of the casual customers (that customers randomly select types for each of the 1-3 rolls), assumed that when they are responding to a roll outage, they will only change their order if they find out the first roll they wanted is out. Else they will just drop the roll from their order (i.e. 2nd or 3rd roll) due to being extremely busy and are in a hurry and can't take the time to decide for every single roll. I found the wording to be a bit vague, mean what does "initial" in :A casual customer will try to select a different roll type to make up their 1 to 3 roll order if the initial one they select is out for the day." I assumed it meant first.
   * Also assumed that "Total payment for orders for the day by customer type, and overall" meant the total profits for the day, and total for all of the customer orders for the day.
   
   
4. Issues Encountered During Development

   * Tried to think of where the Observer pattern may be useful, but determined that it is really only useful for the whether the store is opened or closed.
   
5. Instructions To Run Application

   * To run the program normally, just run the RunStore.java
   * Make sure you have JUnit 4 library installed for the JUnit tests. Make sure to (in Eclipse) go to properties of the project->Java Build Path -> Libaries -> Add Library -> JUnit -> JUnit 4 -> Finish ->Apply & Close
   * To run with the extra credit code: uncomment code marked as extra credit (in RunStore.java and ExtraCredit.java) and download XChart jar and add it to external libraries: https://knowm.org/open-source/xchart/
