package avengers;

import java.util.*;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes. 
 * Then, write into the output file a boolean (true or false) indicating if 
 * the graph is still connected.
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 *    1. seed (long): a seed for the random number generator
 *    2. p (int): number of people (vertices in the graph)
 *    2. p lines, each with p edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency matrix for
 * an undirected graph. 
 * 
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 * 
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) { 
 *     if (StdRandom.uniform() <= 0.5) { 
 *          delete vertex;
 *     }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 * 
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 * 
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, isConnected is true if the graph is connected,
 *   false otherwise):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(isConnected);
 * 
 * @author Yashas Ravi
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in predictthanossnap.out
*/

public class PredictThanosSnap {

	// finding nodes
    private static void Depth1stSearch(int vx, LinkedList<Integer>[] adjMatrix, boolean[] visitedNodes) {
        // check
        visitedNodes[vx] = false;

        visitedNodes[vx] = true;

        for(int i = 0; i < adjMatrix[vx].size(); i++) {
            
            int neighbor = adjMatrix[vx].get(i);
            
            if(visitedNodes[neighbor] != true) {
                Depth1stSearch(neighbor, adjMatrix, visitedNodes);
            }
        }
    }
    
    public static void main (String[] args) {
 
        // prereq
        if ( args.length < 2 ) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }

        // set inputs and assign linkedlist w/seed

        StdIn.setFile(args[0]);

        long sd = 0;
        sd = StdIn.readLong();

        int nNodes = 0;
        nNodes = StdIn.readInt();

        boolean connected = false;

        LinkedList<Integer> adjMatrix[] = new LinkedList[nNodes];

        StdRandom.setSeed(sd);

        for(int i = 0; i < nNodes; i++) {
            // fill linked list w/ adj mat
            adjMatrix[i] = new LinkedList<>();
        }

        // 2d matrix fill 
        for (int x = 0; x < nNodes; x++) {

            for (int k = 0; k < nNodes; k++) {
                
                int value = StdIn.readInt();
                
                if(value != 0 && value == 1){
                    if (StdRandom.uniform() > 0.5) {
                        adjMatrix[x].addFirst(k);
                        adjMatrix[k].addFirst(x);
                    }
                }
            }
        }

        connected = true;

        boolean[] visitedNodes = new boolean[nNodes];

        Depth1stSearch(0, adjMatrix, visitedNodes);

        for(int i = 0; i < visitedNodes.length; i++){
            if(visitedNodes[i] == false) {
                connected = false;
                break;
            }
        }
        StdOut.setFile(args[1]);
        StdOut.print(connected);
    }
}