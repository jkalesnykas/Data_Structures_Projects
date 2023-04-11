package warehouse;
/*
 * This class implements a warehouse on a Hash Table like structure, 
 * where each entry of the table stores a priority queue. 
 */ 
public class Warehouse {
    private Sector[] sectors;
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];
        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    /**
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
    }
    /**
     * Add a new product to the end of the correct sector
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {

        // % 10 for last sector as its determined by last digit
        int sector = id % 10;

        Product temp = new Product(id, name, stock, day, demand);
        
        sectors[sector].add(temp);

    }
    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
    private void fixHeap(int id) {
        
        // replace w variable placeholder later if needed
        sectors[id % 10].swim(sectors[id % 10].getSize());

    }
    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {

       // maintain heap via swap and sink
       if(sectors[id % 10].getSize() == 5) {

           sectors[id % 10].swap(1, sectors[id % 10].getSize());

           sectors[id % 10].deleteLast();

           sectors[id % 10].sink(1);

           }
    }

    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {

        //placeholder for last value
        int x = id%10;
        //first to last check if item has repeated
        for(int i = 1; i <= sectors[x].getSize(); i++)

            if(sectors[x].get(i).getId() == id)

                sectors[x].get(i).updateStock(amount);
    }
    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {

        int x = id%10;
        // first to last check if id is that of param, then swap del and sink
        for(int i = 1; i <= sectors[x].getSize(); i++)

            if(sectors[x].get(i).getId() == id) {

                sectors[x].swap(i, sectors[id % 10].getSize());

                sectors[x].deleteLast();

                sectors[x].sink(i);
            }

        }
    
    /**
     * Simulate a purchase order for some product
     * Requires proper use of the getSize(), sink(), get() methods in the Sector class
     * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
     * @param id The id of the purchased product
     * @param day The current day
     * @param amount The amount purchased
     */
    public void purchaseProduct(int id, int day, int amount) {

        int x = id % 10;

        for(int i = 1; i <= sectors[x].getSize(); i++)
        // check if id is matched, then check stock w/ amount comparison, and then perform proper update and deletion
            if(sectors[x].get(i).getId() == id && sectors[id % 10].get(i).getStock() >= amount) {

                sectors[x].get(i).setLastPurchaseDay(day);

                sectors[x].get(i).updateStock(-1 * amount);

                sectors[x].get(i).updateDemand(amount);

                sectors[x].sink(i);

            }
        }
    
    /**
     * Construct a better scheme to add a product, where empty spaces are always filled
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {
        
        int z = id % 10;

        if(sectors[z].getSize() == 5) {
            for(int i = 1; i < 10; i++) {

                if(sectors[(z + i) % 10].getSize() < 5) {

                    sectors[(z + i) % 10].add(new Product(id, name, stock, day, demand));

                    sectors[(z + i) % 10].swim(sectors[(z + i) % 10].getSize());
                    return;
                }
            }
        }
        // add w/ modified placement parameters
        addProduct(id, name, stock, day, demand);
    }

    /*
     * Returns the string representation of the warehouse
     */
    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    public Sector[] getSectors () {
        return sectors;
    }
}
