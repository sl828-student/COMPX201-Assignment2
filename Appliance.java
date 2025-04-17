/**
 * class represents an appliance object
 * Each appliance has a category, price, and name.
 * The class implements the Comparable interface to allow for comparison between appliances based on their category, price, and name.
 * The class also provides methods to get the category, price, and name of the appliance, as well as a toString method for formatted output.
 */
public class Appliance {
    String category; //actegory of the appliance
    float price; //price of the appliance
    String name; //name of the appliance
    
    /**
     * Constructor to create an appliance object with the given category, price, and name.
     * @param category is the category of the appliance
     * @param price is the price of the appliance
     * @param name is the name of the appliance
     */
    public Appliance(String category, float price, String name) {
        this.category = category; 
        this.price = price;
        this.name = name;
    }

    /**
     * Getter method to retrieve the category of the appliance.
     * @return the category of the appliance
     */
    public String getCategory(){
        return category;
    }

    /**
     * Getter method to retrieve the price of the appliance.
     * @return the price of the appliance
     */
    public float getPrice(){
        return price;
    }

    /**
     * Getter method to retrieve the name of the appliance.
     * @return the name of the appliance
     */
    public String getName(){
        return name;
    }

    /**
     * toString method to provide a formatted string representation of the appliance object.
     * @return a formatted string representing the appliance
     */
    @Override
    public String toString(){
        return String.format("%20s | %15s | $%10.2f", name, category, price);
    }

    /**
     * compareTo method to compare this appliance with another appliance based on category, price, and name.
     * @param other is the other appliance to compare with
     * @return a negative integer, zero, or a positive integer as this appliance is less than, equal to, or greater than the specified appliance
     */
    public int compareTo(Appliance other){
       
        //compare this category with the other object's category
        if (this.category.compareTo(other.category) != 0){
            //compare by category if theyre not the same
            return this.category.compareTo(other.category);
        }
        else {
            //comparing by price if category is the same
            if (this.price < other.price) {
                return -1;
            } 
            else if (this.price > other.price) {
                return 1;
            } 
            else {
                //comparing by name if price is the same
                return this.name.compareTo(other.name);
            }
        }
    }
}
