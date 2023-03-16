import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class GraphGenerator {
	private final int MAX_WEIGHT = 10; //Arbitrary choice for maximum edge weight
	private Random random;
	
	public GraphGenerator() {
		random = new Random();
	}
	
	/*
	 * void generateGraph
	 * generates graph with minV <= |V| <= maxV
	 * |E| is dependent on if the graph is sparse (true) or not (false)
	 * writes to file specified by fileName
	 */
	public void generateGraph(String fileName, int minV, int maxV, boolean sparse) throws IOException{
		FileWriter fOutput = new FileWriter(fileName);
		
		//Randomly choose |V| between minV and maxV
		int numV = minV == maxV ? minV : random.nextInt(maxV - minV) + minV;
		
		//Calculate density in the range 0 - 1. 0 <= density <= 0.5 if sparse. 0.75 <= density <= 1 if dense
		double density = sparse ? random.nextDouble() * 0.5 : (random.nextDouble() * 0.25 + 0.75);
		int numE = (int)(density / 2.0 * numV * (numV - 1));
		
		fOutput.write(numV + "\n");
		
		int count = 0;
		Set<String> edges = new HashSet<String>();	//create set of already included edges to prevent generating same edge twice
		List<Integer> unconnected = new ArrayList<Integer>(); //create list of all vertices-- need to be connected
		for(int i = 0; i < numV; i++) {
			unconnected.add(i);
		}
		
		List<Integer> init_st = new ArrayList<Integer>(); //create list of all already connected vertices-- initialize with random vertex
		
		//Add random vertex to spanning tree to serve as initial vertex
		int posUnconnected = random.nextInt(unconnected.size()-1);
		int v_connect = unconnected.remove(posUnconnected);
		init_st.add(v_connect);
		
		//Begin connecting all vertices to the spanning tree
		while(unconnected.size() > 0) {
			//Find a random connection point in the partial spanning tree
			int posMST;
			if(init_st.size() == 1) { posMST = 0; }
			else{ posMST = random.nextInt(init_st.size()-1); }
			int v_mst = init_st.get(posMST);
			
			//Find a random unconnected vertex to add
			if( unconnected.size() == 1) { posUnconnected = 0; }
			else{ posUnconnected = random.nextInt(unconnected.size()-1); }
			v_connect = unconnected.remove(posUnconnected);
			init_st.add(v_connect);
			
			//Generate random weight and add new edge to output file
			int weight = random.nextInt(MAX_WEIGHT - 1) + 1;
			String e = v_mst + " " + v_connect;
			String e2 = v_connect + " " + v_mst;
			edges.add(e);
			edges.add(e2);
			count++;
			fOutput.write(v_mst + " " + v_connect + " " + weight + "\n");
		}
		
		//If MST contains less edges than the randomly generated numEdges, add more random edges
		while(count < numE) {
			//Get random vertices v1 and v2
			int v1 = random.nextInt(numV);
			int v2 = random.nextInt(numV);
			while(v1 == v2) {
				v2 = random.nextInt(numV);	//Don't want self edge, continue to generate
			}
			//Check possible edge combinations in set edges
			String e = v1 + " " + v2;
			String e2 = v2 + " " + v1;
			if(!edges.contains(e) && !edges.contains(e2)) {
				edges.add(e);
				edges.add(e2);
				count++;
				int weight = random.nextInt(MAX_WEIGHT-1) + 1;
				fOutput.write(v1 + " " + v2 + " " + weight + "\n");
			}
		}
		fOutput.close();
	}
	
	/*
	 * Examples of generating a graph
	 */
	public static void main(String[] args) throws IOException{
		GraphGenerator g = new GraphGenerator();
		
		g.generateGraph("ginput.txt", 1000, 1000, true);
		
		
		//g.generateGraph("gInputSmallSparse1.txt", 0, 10, true);
		//g.generateGraph("gInputSmallSparse2.txt", 10, 20, true);
		//g.generateGraph("gInputSmallDense1.txt", 5, 10, false);
		//g.generateGraph("gInputSmallDense2.txt", 10, 20, false);
		//g.generateGraph("gInputBigSparse1.txt", 100, 150, true);
		//g.generateGraph("gInputBigSparse2.txt", 150, 200, true);
		//g.generateGraph("gInputBigDense1.txt", 100, 150, false);
		//g.generateGraph("gInputBigDense2.txt", 190, 200, false);
		//g.generateGraph("gInputSuperLargeDense.txt", 1000, 1000, false);
	}
}
