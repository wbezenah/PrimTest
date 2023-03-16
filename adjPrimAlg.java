//package cs375Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

// The only class in this file is the adjPrimAlg class. This is the driving class for the program. It creates an adjacency list and an 
// adjacency matrix object that hold all the edges provided by the input problem. The class extracts these edges and weights by parsing
// the input file by line, inserting the vertexes for each edge pair and the corresponding weight where needed. The file also makes the
// calls to the primsAlgorithm function for each object. There are no functions in this class aside from main, which is the driver that takes
// in the command line arguments.


public class adjPrimAlg {
    public static void main(String args[]) throws IOException{
        BufferedReader fInput = null;   // Reads in the input file holding the edges for the graph
        FileWriter fOutputWriter = new FileWriter("output.txt");        // Writes to output file
        String sLineTrack = "";     // String to read each line of input file
        int iNumNodes = 0;          // Number of nodes as indicated by input file
        int iProblemtrack = 0;      // Tracks which problem we're on for the input file
        adjList lAdjList = null;    // Adjacency list object
        ArrayList<ArrayList<Edge>> minSpanningTree = null;     // ArrayList needed for adjacency list implementation. Holds arraylist of edges for each vertex
        ArrayList<Edge> eEdgesToAdd = new ArrayList<Edge>();    // The actual edges to be used in adjacency list
        adjMatrix mAdjMatrix = null; // Adjacency matrix object
        String sParseProblem[] = null;  // Parse's each line in the input file to extractn each vertex and the weight

        // Verify correct number of arguments were passed in when calling program
        if (args.length != 1){
            System.out.println("Incorrect number of arguments passed in. Please try again.");
            System.exit(0);
        } else {
            fInput = new BufferedReader(new FileReader(args[0]));
            sLineTrack = fInput.readLine();

            while (sLineTrack != null){
                sParseProblem = sLineTrack.split(" ");
                if (Character.isDigit(sParseProblem[0].charAt(0)) & sParseProblem.length == 1){
                    iProblemtrack++;
                    // If first character is a digit and array is only length 1, we know a new problem is being sent in
                    sParseProblem = sLineTrack.split(" ");
                    iNumNodes = Integer.parseInt(sParseProblem[0]);
                    sLineTrack = fInput.readLine(); // Move to first given edge
                    sParseProblem = sLineTrack.split(" ");
                    mAdjMatrix = new adjMatrix(iNumNodes);
                    minSpanningTree = new ArrayList<ArrayList<Edge>>(iNumNodes);
                    // Initialize array list of edges for each vertex in adjacency list
                    for(int i = 0; i < iNumNodes; i++){
                        ArrayList<Edge> b = new ArrayList<Edge>();  // Creation of arraylist for edges of each vertex in adjacency list
                        minSpanningTree.add(b);
                    }
                    lAdjList = new adjList(minSpanningTree, iNumNodes);
                    // Add edges to each objects
                    while (!(sParseProblem.length == 1) && sLineTrack != null){
                        sParseProblem = sLineTrack.split(" ");
                        mAdjMatrix.addEdge(Integer.parseInt(sParseProblem[0]), Integer.parseInt(sParseProblem[1]), Integer.parseInt(sParseProblem[2]));
                        eEdgesToAdd.add(new Edge(Integer.parseInt(sParseProblem[0]), Integer.parseInt(sParseProblem[1]), Integer.parseInt(sParseProblem[2])));
                        sLineTrack = fInput.readLine();
                    }
                    for(int i = 0; i < eEdgesToAdd.size(); i++){
                        lAdjList.addEdge(eEdgesToAdd.get(i).getSource(), eEdgesToAdd.get(i).getDest(), eEdgesToAdd.get(i).getWeight());
                    }
                    // Format output file and call algorithms where needed
                    fOutputWriter.write("Problem " + iProblemtrack + "\n");
                    fOutputWriter.write("\n");
                    lAdjList.primsMST(fOutputWriter);
                    fOutputWriter.write("\n\n");
                    mAdjMatrix.primsMST(fOutputWriter);
                    fOutputWriter.write("\n");
                } else {
                    sLineTrack = fInput.readLine();
                }
                sParseProblem = null;
                mAdjMatrix = null;
                lAdjList = null;
            }
            fOutputWriter.close();
        }
    }
}