package avengers;

import java.util.*;

/**
 * Given a Set of Edges representing Vision's Neural Network, identify all of the 
 * vertices that connect to the Mind Stone. 
 * List the names of these neurons in the output file.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * MindStoneNeighborNeuronsInputFile name is passed through the command line as args[0]
 * Read from the MindStoneNeighborNeuronsInputFile with the format:
 *    1. v (int): number of neurons (vertices in the graph)
 *    2. v lines, each with a String referring to a neuron's name (vertex name)
 *    3. e (int): number of synapses (edges in the graph)
 *    4. e lines, each line refers to an edge, each line has 2 (two) Strings: from to
 * 
 * Step 2:
 * MindStoneNeighborNeuronsOutputFile name is passed through the command line as args[1]
 * Identify the vertices that connect to the Mind Stone vertex. 
 * Output these vertices, one per line, to the output file.
 * 
 * Note 1: The Mind Stone vertex has out degree 0 (zero), meaning that there are 
 * no edges leaving the vertex.
 * 
 * Note 2: If a vertex v connects to the Mind Stone vertex m then the graph has
 * an edge v -> m
 * 
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut:
 *     StdOut.setFile(outputfilename);
 *     //Call StdOut.print() for EVERY neuron (vertex) neighboring the Mind Stone neuron (vertex)
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/MindStoneNeighborNeurons mindstoneneighborneurons.in mindstoneneighborneurons.out
 *
 * @author Yashas Ravi
 * 
 */


public class MindStoneNeighborNeurons {
    
    public static void main (String [] args) {

        HashMap<String, Integer> hm = new HashMap<>();

        // prereq
        
    	if ( args.length < 2 ) {
            StdOut.println("Execute: java MindStoneNeighborNeurons <INput file> <OUTput file>");
            return;
        }
        
        // setup file and read

        StdIn.setFile(args[0]);
        int num = 0;
        num = StdIn.readInt();
        
        // using a hash map and array list as the value for the hash map's each binded key - req'd import

        String[] NoN = new String[num];

        for(int x = 0; x < num; x++) {

            NoN[x] = StdIn.readString();

            hm.put(NoN[x], x);

        }

        // initialiaze syn, and find neighbors via triple conditional
        int numS = 0;
        numS = StdIn.readInt();

        int[][] syn = new int[num][num];
        
        for(int x = 0; x < numS; x++) {
            
            int indexOne = hm.get(StdIn.readString());
            int indexTwo = hm.get(StdIn.readString());

            syn[indexOne][indexTwo] = 1;

        }

        // degrees
        int[] dg = new int[num];

        for(int x = 0; x < num; x++) {

            for(int k = 0; k < num; k++) {

                if(syn[x][k] == 1 && syn[x][k] != 0) {

                    dg[x] += 1;
                }
            }
        }

        //index of mindstone
        int index = 0;

        for(int x = 0; x < num; x++){

            if(dg[x] == 0 && dg[x] != 1) {
                index = x;
            }

        }

        // read to file and output result
        StdOut.setFile(args[1]);

        for(int i = 0; i < num; i++) {

            if(syn[i][index] == 1 && syn[i][index] != 0) {
                StdOut.println(NoN[i]);
                
            }
        }
    }
}