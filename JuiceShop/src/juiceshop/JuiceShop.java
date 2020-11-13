/**
 * Project Name: JuiceShop.java
 * Project: Juice Assignment
 * Programmer: Rayaan Name
 * Date: 2020-10-30
 */
package juiceshop;
import java.util.*;

public class JuiceShop {

    static public Scanner scanS = new Scanner(System.in); // String Scanner
    static public Scanner scanN = new Scanner(System.in); // Number Scanner
    
    public static void main(String[] args) throws InterruptedException {
        
        boolean useAgain = true; // mainMenu repeatedly runs again
        ArrayList<Order> ordersArray = new ArrayList<>(); // stores user info
        
        System.out.println("\nJuice Shoppe, Cashier Product App ");
        do {
            mainMenu(ordersArray); // presents main menu   
        } while (useAgain == true); // keeps looping untill user quits
        
    } // main class ends
    
    /**
     * Method Name: mainMenu
     * Description: Presents options of what to do and directs user to
     * appropriate method
     * @param ordersArray //the Order array where name, order, and size is
     * @throws InterruptedException 
     */
    public static void mainMenu (ArrayList ordersArray) 
            throws InterruptedException  {
        
        String userChoice; // store user choice
        System.out.print("\nMain Menu"
                + "\n1) View Orders"
                + "\n2) Add Order"
                + "\n3) Delete Order"
                + "\n4) Calculate total profit"
                + "\n5) Exit --> ");
        userChoice = scanS.nextLine(); // scan
        
        // Swtich statement sends user to appropriate method
        switch (userChoice.toLowerCase()) 
        {
            case "1":
            case "view orders":
                vO(ordersArray); // calls view order method
                break;
            case "2":
            case "add order":
                ordersArray.add(aO(ordersArray)); // calls add Order method
                break;
            case "3":
            case "delete order":
                dO(ordersArray); // calls delete Order method
                break;
            case "4":
            case "calculate total profit":
                cTP(ordersArray); // calls calculate Total Profit Method
                break;
            case "5":
            case "exit":
                System.out.println("Khan™ systems 2020.");
                System.exit(0);
                break;
            default:
                System.out.println("\nInvalid input try again");
        }
    } // end of mainMenu class
    
    /**
     * Method Name: vO
     * Definition: vO - View Order shows all existing orders
     * @param ordersArray //the Order array where name, order, and size is 
     * @throws java.lang.InterruptedException 
     */ 
    public static void vO(ArrayList<Order> ordersArray) 
            throws InterruptedException {       
        
        int orderView = 0;
        boolean errorTrap;
        
        if (!ordersArray.isEmpty()) {
            
            OrderName(ordersArray); // prints names

            do {
                errorTrap = false; // avoid infinite loop
                System.out.print("\nWhich order would you like to view? "
                        + "\nplease enter the number next to the name --> ");        
                orderView = scanN.nextInt() - 1; // minus 1 because we add one

                if (orderView > ordersArray.size() || orderView < 0) {
                    System.out.println("invalid input try again");
                    errorTrap = true; // repeats do-loop
                }
            } while (errorTrap == true); // error Trap ends   
                
                System.out.println("\n"); // formatting
                System.out.println(ordersArray.get(orderView).toString()); 
            
            System.out.println("\nPress enter to return to menu");
            scanS.nextLine(); // waits for enter   
        } // conditional ends
        
        else // if no orders have been placed
            System.out.println("\nNo orders yet!");
    } // method ends 
    
    /**
     * Method Name: aO
     * Definition: add an Order to the ArrayList
     * @param ordersArray //the Order array where name, order, and size is 
     * @return name // returns name
     * @return orderContent // returns what the user ordered
     * @return orderSize // returns the size of the oder (in L)
     * @throws java.lang.InterruptedException  
     */
    public static Order aO(ArrayList ordersArray) throws InterruptedException {
        
        String name; // customer name
        String orderContent; // what they ordered
        int orderSize; // how many liters of juice
        boolean validOrder; // if the order picked is valid
        boolean wrongSize; // if user enters an impossible ammount of juice
        
        System.out.print("\nWhat is the name of this customer? ");
        name = scanS.nextLine().toLowerCase(); // takes input and makes it LC
        
        do { // checks if order is valid
            validOrder = true; // prevent infinite loop
            System.out.print("\nWhat kind of of juice would they like to order?"
                    + "\n1) Apple"
                    + "\n2) Orange"
                    + "\n3) Pickle --> ");
            orderContent = scanS.nextLine().toLowerCase(); // stores ans in LC
            /*
            Switch statement is needed to set orderContent = to proper form 
            because to calculate profit we have to ensure that variable 
            is equal to "apple" not "1" and for user friendliness.
            */
            switch (orderContent) {
                case "1":
                case "apple":
                    orderContent = "apple";
                    break;
                case "2":
                case "orange":
                    orderContent = "orange";
                    break;
                case "3":
                case "pickle":
                    orderContent = "pickle";
                    break;
                default:
                    System.out.println("Invalid option try again");
                    validOrder = false;
                    Thread.sleep(500); // wait .5 seconds
            }
        } while (validOrder == false); // error trapping
        
        do {   
            wrongSize = false; // to avoid infinite loop
            System.out.print("\nHow many liters would of juice would " 
                    + name + " like? ");
            orderSize = scanN.nextInt(); 
            
            if (orderSize <= 0) { // if size is less than or = to 0 repeat!
                System.out.println("\nIncorrect ammount");
                wrongSize = true;
            }
        } while (wrongSize == true);
        System.out.println("\nOrder created... one moment please...");
        Thread.sleep(1000); // wait 1 second
        
        // returns name, order content, order size to new object
        return new Order(name, orderContent, orderSize); 
    }
    
    /**
     * Method Name: dO
     * Definition: delete an Order
     * @param ordersArray //the Order array where name, order, and size is 
     * @throws java.lang.InterruptedException 
     */
    public static void dO(ArrayList<Order> ordersArray) 
            throws InterruptedException {
        
        int deleteUser; // select which user to delete
        boolean runAgain;  // for errorTrapping
        
        /*
        If the array is not empty it will prompt the user to remove an order
        from the ArrayList
        */
        if (!ordersArray.isEmpty()) {
            do { // loop for error trapping
                runAgain = false; // error trap loop
                System.out.print("\nWhich order would you like to remove?");

                OrderName(ordersArray); // prints names

                System.out.print("\nPlease enter the corresponding #\n #");
                deleteUser = (scanN.nextInt()-1); // minus 1 because we added 1

                // to avoid index out of bounds error
                if (deleteUser < 0 || deleteUser > ordersArray.size()) {
                    System.out.println("That order # does not exist");
                    runAgain = true;
                }
            } while (runAgain == true); // error trap ends   

            ordersArray.remove(deleteUser); // deletes the order
            System.out.println("Order removed... one moment please");
            Thread.sleep(1000); // wait one second
        }
        
        else // if no order has been placed
            System.out.println("\nNo orders yet!");
    }
    
    /**
     * Method Name: cTP
     * Definition: Calculate total profit
     * @param ordersArray //the Order array where name, order, and size is 
     */
    public static void cTP(ArrayList<Order> ordersArray) {
        int totalC = 0; // total profit 
        // adds total of each order to totalC untill all orders have been added
        for(int i = 0; i < ordersArray.size(); i++)
            totalC += ordersArray.get(i).calcPrice();
        System.out.println("\nTotal profit of all your orders is $" + totalC);
    }

    /**
     * Method Name: OrderName
     * Definition: prints out just the names from each order method stops us
     * from having to repeat the code two times (once in vO and once in dO
     * @param ordersArray //the Order array where name, order, and size is 
     */
    public static void OrderName (ArrayList<Order> ordersArray) {
        // simply prints all the names in the object arrayList
        for (int i = 0; i < ordersArray.size(); i++) {
            System.out.println((i+1) + ") " + ordersArray.get(i).getName());
        } 
    }
}
