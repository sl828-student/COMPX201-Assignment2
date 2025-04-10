public class Appliance {
    String category;
    float price;
    String name;

    public Appliance(String category, float price, String name) {
        this.category = category; 
        this.price = price;
        this.name = name;
    }

    public String getCategory(){
        return category;
    }

    public float getPrice(){
        return price;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return String.format("%20s | %15s | $%10.2f", name, category, price);
    }

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
