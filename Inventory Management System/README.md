**Summary**

This program can be used in order to manage simple inventory systems.

The Warehouse class is a data structure that implements a hash table-like structure where each entry of the table stores a priority queue of products. The class provides methods to add, restock, delete, and purchase products in the warehouse. When a new product is added using the addProduct() method, the method first checks if the number of products in the sector is greater than or equal to 5. If it is, then the least popular item is evicted from the sector while maintaining the heap structure. The product is then added to the end of the corresponding sector, and the heap structure of the sector is fixed by invoking the fixHeap() method. The restockProduct() method allows updating the stock of a product by some amount. To do so, the method first retrieves the product from the corresponding sector using the get() method and then invokes the updateStock() method of the product to update its stock. The deleteProduct() method allows deleting a product from the warehouse. It retrieves the product from the corresponding sector using the get() method and then swaps the product with the last item in the sector. After deleting the last item, the heap structure is maintained using the sink() method. The purchaseProduct() method simulates a purchase order for a product. The method retrieves the product from the corresponding sector using the get() method and checks if the stock of the product is greater than or equal to the amount purchased. If it is, then the method updates the stock of the product using the updateStock() method and the last purchase day of the product using the setLastPurchaseDay() method. The demand for the product is also updated using the updateDemand() method. If the stock of the product is less than the amount purchased, no updates are made.

The warehouse is implemented using the Sector class, which provides methods to add, retrieve, and delete products in the sector, and to maintain the heap structure of the sector using the swim() and sink() methods. The products in the sector are stored in a priority queue, which is implemented using the Product class. The Product class provides methods to update the stock, demand, and last purchase day of the product. The products in the warehouse are organized by their id, where each sector in the hash table-like structure corresponds to the last digit of the id.

**Skills Utilized**

The inventory management program for this inventory warehouse involves a complex system that utilizes various data structures and algorithms. The system is designed to efficiently look up products and delete underperforming ones to make space for new items, alongside other complex actions. The program utilizes a Hash Table-like structure, with each entry storing a priority queue. The priority queue structure allows for the deletion of less popular items, which helps to keep the space constant. The warehouse is split into 10 sectors, and the last digit of the product ID determines which sector it should go into. This allows for a more efficient search process, narrowing down the search to at most 5 items per sector.

A simple metric for evaluating the popularity of each product was established, which includes the initial demand, the total amount purchased, and the date of last purchase. The popularity of each item is automatically calculated and updated as the sum of the last purchase day and current demand. Each sector of the warehouse is implemented as a min heap ranked by popularity, allowing for the efficient deletion of the least popular item.

The program also utilizes various Java programming techniques, such as object-oriented programming, getters and setters, array manipulation, file I/O, and error handling. Time management is also a critical skill required for this assignment. The ability to use the command line is essential as well.

Debugging and testing skills are also crucial for this project, as this required to identify and fix errors in the code and create input files to test the various methods. The complexity of this project requires an advanced understanding of programming concepts and vocabulary, making it a challenging yet rewarding task.

**How to use?**

Simply, the user will give a json-type file implementation of data, and run it as a parameter for the warehouse inventory management program. There, the user can choose what he'd/she'd like to do with the data based on the methods provided: *Add Product, Restock Product, Delete Product, Purchase Product, and a more cleaner version of the Add Product (Better Add Product)*.

An example .in file has been provided to view the format. 

Miscellanous

The user must have the following libraries installed: StdRandom.java StdIn.java StdOut.java

Also, please note that this system is run within the terminal, as it is an ASCII, text-based implementation.
