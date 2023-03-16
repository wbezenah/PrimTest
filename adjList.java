//package cs375Project;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class adjList{
    private static Comparator<Edge> minWeight;
    private ArrayList<ArrayList<Edge>> graph;
    private int numV;

    public adjList(ArrayList<ArrayList<Edge>> graph, int numV){
        this.graph = graph;
        this.numV = numV;
    }

    static{
        minWeight = new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2){
                return Integer.compare(e1.getWeight(), e2.getWeight());
            }
        };
    }

    public void addEdge(int source, int destination, int weight){
        Edge e1 = new Edge(source, destination, weight);
        Edge e2 = new Edge(destination, source, weight);
        graph.get(source).add(e1);
        graph.get(destination).add(e2);
    }

    public void primsMST(FileWriter w) throws IOException{
        double startTime = System.nanoTime();
        double endTime = 0;
        w.write("Prim's Algorithm using adjacency list representation:\n");
		w.write("Edge : Weight\n");
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(minWeight);
        ArrayList<Edge> mst = new ArrayList<Edge>();
        boolean selected[] = new boolean[numV];	//keep track of selected vertices
		selected[0] = true; 					//arbitrarily select first vertex
        int currentNode = 0;
        for(Edge e : graph.get(currentNode)){
            pq.add(e);
        }
        while(!pq.isEmpty()){
            Edge minEdge = pq.peek();
            currentNode = minEdge.getDest();
            pq.remove();
            if (!selected[currentNode]) {
                selected[currentNode] = true;
                mst.add(minEdge);
                // Iterate through all the nodes adjacent to the node taken out of priority queue.
                // Push only those nodes that are not yet present in the minumum spanning tree.
                for (Edge e : graph.get(currentNode)) {
                    int adj_node = e.getDest();
                    if (selected[adj_node] == false) {
                        pq.add(e);
                    }
                }
            }
        }
        for(int i = 0; i < mst.size(); i++){
            w.write(mst.get(i).getSource() + " - " + mst.get(i).getDest() + " : " + mst.get(i).getWeight() + "\n");
        }
        w.write("Total Computation Time: ");
        endTime = System.nanoTime();
        w.write(Double.toString((endTime-startTime)/1000000) + " milliseconds");
        System.out.println("LIST\n" + Double.toString((endTime-startTime)/1000000) + " milliseconds");
    }

    public static void main(String args[]) throws IOException{
        FileWriter fOutput = new FileWriter("adjListOut.txt");
        ArrayList<Edge> a = new ArrayList<Edge>();
		a.add(new Edge(0, 1, 4));
		a.add(new Edge(0, 2, 4));
        a.add(new Edge(1, 2, 2));
        a.add(new Edge(2, 3, 3));
		a.add(new Edge(2, 4, 2));
		a.add(new Edge(2, 5, 4));
		a.add(new Edge(3, 5, 3));
        a.add(new Edge(4, 5, 3));
        ArrayList<ArrayList<Edge>> minSpanningTree = new ArrayList<ArrayList<Edge>>(6);
        for(int i = 0; i < 6; i++){
            ArrayList<Edge> b = new ArrayList<Edge>();
            minSpanningTree.add(b);
        }
        adjList aList = new adjList(minSpanningTree, 6);
        for(int i = 0; i < a.size(); i++){
            aList.addEdge(a.get(i).getSource(), a.get(i).getDest(), a.get(i).getWeight());
        }
        aList.primsMST(fOutput);
        fOutput.close();
    }
}