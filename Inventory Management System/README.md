**Summary**


**Skills Utilized**

The inventory management program for this inventory warehouse involves a complex system that utilizes various data structures and algorithms. The system is designed to efficiently look up products and delete underperforming ones to make space for new items, alongside other complex actions. The program utilizes a Hash Table-like structure, with each entry storing a priority queue. The priority queue structure allows for the deletion of less popular items, which helps to keep the space constant. The warehouse is split into 10 sectors, and the last digit of the product ID determines which sector it should go into. This allows for a more efficient search process, narrowing down the search to at most 5 items per sector.

A simple metric for evaluating the popularity of each product was established, which includes the initial demand, the total amount purchased, and the date of last purchase. The popularity of each item is automatically calculated and updated as the sum of the last purchase day and current demand. Each sector of the warehouse is implemented as a min heap ranked by popularity, allowing for the efficient deletion of the least popular item.

The program also utilizes various Java programming techniques, such as object-oriented programming, getters and setters, array manipulation, file I/O, and error handling. Time management is also a critical skill required for this assignment. The ability to use the command line is essential as well.

Debugging and testing skills are also crucial for this project, as this required to identify and fix errors in the code and create input files to test the various methods. The complexity of this project requires an advanced understanding of programming concepts and vocabulary, making it a challenging yet rewarding task.

**How to use?**

Simply, the user will give a json-type file implementation of data, and run it as a parameter for the warehouse inventory management program. There, the user can choose what he'd/she'd like to do with the data based on the methods provided: *Add Product, Restock Product, Delete Product, Purchase Product, and a more cleaner version of the Add Product (Better Add Product)*. 

Miscellanous

The user must have the following libraries installed: StdRandom.java StdIn.java StdOut.java

Also, please note that this system is run within the terminal, as it is an ASCII, text-based implementation.
