package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {

    // helper func to help find min node in alg

    private static int getminc(int [] minc, boolean[] DijkstraSet) {

        // init

        int in = 0;
        int c = Integer.MAX_VALUE;
    
        // swap

        for (int i = 0; i < minc.length; i++) {
            
            if (minc[i] <= c && (DijkstraSet[i] == false)) {

                in = i;

                c = minc[i];
            }
        }
        
        return in;
    }
	
    public static void main (String [] args){

        // prereq
        
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }

        StdIn.setFile(args[0]);

        int num = StdIn.readInt();

        // initialize matrix and its adjacent replica

        int [][] adj = new int[num][num];

        double[] mat = new double[num];        

        // iterating to read / write

        for(int x = 0; x < num; x++) {
            int gen = StdIn.readInt();
            mat[gen] = StdIn.readDouble();
        }

        for(int x = 0; x < num; x++) {
            for(int k = 0; k < num; k++) {
                adj[x][k] = StdIn.readInt();
            }
        }

        for(int x = 0; x < num; x++) {
            for(int k = 0; k < num; k++) {
                if(adj[x][k] > 0) {

                    double val = mat[x] * mat[k];

                    double energyCost = adj[x][k] / val + 0;
                    
                    adj[x][k] = (int)(energyCost);

                }
            }
        }

        // dijkstraset algorithm *prov pseudo

        int[] minc = new int[num];

        boolean[] DijkstraSet = new boolean[num];

        for(int x = 0; x < num; x++) {
            if(x == 0) {
                minc[x] = 0;
            }
            else {
                minc[x] = Integer.MAX_VALUE;
            }
        }

        for (int x = 0; x < minc.length - 1; x++) {
            
            int src = getminc(minc, DijkstraSet);

            DijkstraSet[src] = true;

            for(int k = 0; k < num; k++) {

                if(adj[src][k] != 0) {

                    if(minc[k] > (minc[src] + adj[src][k]) && (DijkstraSet[k] == false && (minc[src] != Integer.MAX_VALUE))) { // to comp to max val

                            minc[k] = minc[src] + adj[src][k];
                            
                        }
                    }
                }
            }

        StdOut.setFile(args[1]);

        StdOut.print(minc[num-1]);
        
    }
    
}