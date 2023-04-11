**Summary**

The Huffman coding project is a program that performs the entire Huffman coding encoding and decoding process. The project is divided into three parts: (1) reading the input file and creating a sorted list of characters and their frequencies; (2) building a Huffman coding tree using the sorted list from part 1; and (3) encoding the input file using the Huffman coding tree and decoding the encoded file back to the original file.

**Skills Utilized**

This project requires an understanding of data structures such as ArrayLists, Queues, and Complex Binary Trees, BSTs, as well as their implementation and manipulation in Java. It also requires an understanding of file input/output and bit manipulation.

**How to use**

The user will use the Driver to run the program. The program will prompt the user to enter the name of the input file. Once the input file is read, the program will create a sorted list of characters and their frequencies, then build a Huffman coding tree using the sorted list. The program will then encode the input file using the Huffman coding tree and write the encoded output to a new file. The program can also decode the encoded file back to the original file.

**Miscellaneous**

The program uses the following libraries:

*Queue.java:* A queue data structure
*CharFreq.java:* A class that stores a character and its frequency
*TreeNode.java:* A class that represents a node in a tree
*BinaryIn.java:* A class that provides methods for reading binary data from a file
*BinaryOut.java:* A class that provides methods for writing binary data to a file

Note that the program writes the encoded output to a binary file, so the user will need to use the *BinaryIn* and *BinaryOut* libraries to read and write the encoded file. Also note that it is important to have installed the StdRandom, StdOut, and StdIn instances of the larger Standard class.
