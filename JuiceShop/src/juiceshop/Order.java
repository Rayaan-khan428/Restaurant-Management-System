 /**
 * Class Name: Order
 * Programmer:
 * Date:
 * Description: Creates a single order for a customer.
 */
package juiceshop;

public class Order {
    
    //fields
    private String name;
    private String juiceFlavour;
    private int orderSize;

    //arg constructor
    public Order(String n, String j, int o) {
        name = n;
        juiceFlavour = j;
        orderSize = o;
    }

    //Accessors
    public String getName() {
        return name;
    }

    public String getJuiceFlavour() {
        return juiceFlavour;
    }

    public int getOrderSize() {
        return orderSize;
    }

    //Mutators
    public void setName(String n) {
        name = n;
    }

    public void setJuiceFlavour(String j) {
        juiceFlavour = j;
    }

    public void setOrderSize(int o) {
        orderSize = o;
    }
    
    /**
     * Method Name: calcPrice
     * Method Description: Calculates the price of this orders bill
     * @return - order cost
     */
    public int calcPrice() {
        int price = 0;
        
        //change price for each flavour
        switch(juiceFlavour) {
            case "orange":
                price = 8;
                break;
            
            case "apple":
                price = 5;
                break;
                
            case "pickle":
                price = 15;
                break;
        }
        
        return orderSize * price;
    }
    
    /**
     * Method Name: toString
     * Description: creates a String representing object state  
     * @return state of the object
     */
    @Override
    public String toString() {
        return name + " has ordered " + orderSize + " litres of " 
                + juiceFlavour + " Juice";
    }
}
