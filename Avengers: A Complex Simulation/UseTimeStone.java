package avengers;

import java.util.*;

/**
 * Given a starting event and an Adjacency Matrix representing a graph of all possible 
 * events once Thanos arrives on Titan, determine the total possible number of timelines 
 * that could occur AND the number of timelines with a total Expected Utility (EU) at 
 * least the threshold value.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * UseTimeStoneInputFile name is passed through the command line as args[0]
 * Read from UseTimeStoneInputFile with the format:
 *    1. t (int): expected utility (EU) threshold
 *    2. v (int): number of events (vertices in the graph)
 *    3. v lines, each with 2 values: (int) event number and (int) EU value
 *    4. v lines, each with v (int) edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note 1: the last v lines of the UseTimeStoneInputFile is an ajacency matrix for a directed
 * graph. 
 * The rows represent the "from" vertex and the columns represent the "to" vertex.
 * 
 * The matrix below has only two edges: (1) from vertex 1 to vertex 3 and, (2) from vertex 2 to vertex 0
 * 0 0 0 0
 * 0 0 0 1
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * UseTimeStoneOutputFile name is passed through the command line as args[1]
 * Assume the starting event is vertex 0 (zero)
 * Compute all the possible timelines, output this number to the output file.
 * Compute all the posssible timelines with Expected Utility higher than the EU threshold,
 * output this number to the output file.
 * 
 * Note 2: output these number the in above order, one per line.
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
 *     //Call StdOut.print() for total number of timelines
 *     //Call StdOut.print() for number of timelines with EU >= threshold EU 
 * 
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/UseTimeStone usetimestone.in usetimestone.out
 * 
 * @author Yashas Ravi
 * 
 */

public class UseTimeStone {

        // depth first search
        private static void Depth1stSearch(LinkedList<Integer>[] adjMatrix, LinkedList<List<Integer>> all, Stack<Integer> anyPath, boolean[] isVisited, int src, int dest) {

            anyPath.push(src);

            isVisited[src] = true;

            // if found
            if(src == dest) {
                // create a new linked list w/ type int

                LinkedList<Integer> list = new LinkedList<>();

                all.addLast(list);

                // create a new stack from up-bottom priority

                Stack<Integer> reverse = new Stack<>();

                //perform push/pop operations to modify stack

                for(int z : anyPath) {
                    reverse.push(z);
                }

                if (reverse.size() > 1 || reverse.size() == 1) {
                    list.add(reverse.pop());
                }

                while(reverse.isEmpty() == false) {
                    list.add(reverse.pop());
                }

                //apply
            } else {
                for (int i : adjMatrix[src]) {
                    if (isVisited[i] == false) {
                        Depth1stSearch(adjMatrix, all, anyPath, isVisited, i, dest);
                    }
                }
            }

            // close off
            isVisited[src] = false;
            anyPath.pop();

        }

    // find every single path
    private static LinkedList<List<Integer>> allPaths(LinkedList<Integer> [] adjMatrix, int src, int dest) {
        
        // implement an empty base stack and ll, then execute prev. method on it
        Stack<Integer> anyPath = new Stack<>();

        LinkedList<List<Integer>> everyPath = new LinkedList<>();

        boolean[] isVisited = new boolean[adjMatrix.length];
        
        Depth1stSearch(adjMatrix, everyPath, anyPath, isVisited, src, dest);
        
        return everyPath;
    }


    public static void main (String [] args) {
    	
        // prereq

        if ( args.length < 2 ) {
            StdOut.println("Execute: java UseTimeStone <INput file> <OUTput file>");
            return;
        }

        // create vars and initialize 

        StdIn.setFile(args[0]);

        int Max = 0;
        Max = StdIn.readInt();

        int nNodes = 0;
        nNodes = StdIn.readInt();

        int currEvent[] = new int[nNodes];

        int expUtil[] = new int[nNodes];

        // read and assign
        for(int x = 0; x < nNodes; x++) {

            currEvent[x] = StdIn.readInt();

            expUtil[x] = StdIn.readInt();

        }
        
        // create empty adjMatrix
        LinkedList<Integer> adjMatrix [] = new LinkedList[nNodes];

        // fill w/ proper nodes
        for(int x = 0; x < nNodes; x++) {

            // each new node is an interlinked ll

            adjMatrix[x] = new LinkedList<>();

            for(int k = 0; k < nNodes; k++) {

                // if value is one add to ll (output should only be 0/1s)
                if (StdIn.readInt() == 1) {

                    adjMatrix[x].add(k);
                }
            }
        }

        int tt = 0;
        int ct = 0;

        for(int x = 0; x < nNodes; x++) {

            // new ll
            x = x;
            LinkedList<List<Integer>> paths = allPaths(adjMatrix, 0, x);

            // fill w/ all paths

            for(List<Integer> list : paths) {

                int EV = 0;

                for(int j = 0; j < list.size(); j++) {

                    int e = 0;
                    e = list.get(j);

                    EV += expUtil[e];

                }
                
                if (EV > Max || EV == Max) {
                    ct += 1;
                }
            }
            // add
            tt += paths.size();
        }

        // output to file

        StdOut.setFile(args[1]);

        StdOut.println(tt);

        StdOut.println(ct);
    }
}
