# PrimTest

Java implementation of Prim's algorithm on the two different types of graph representations-- adjacency matrix and adjacency list.  
Includes a GraphGenerator class that can be used to generate files describing any size and density graph.  

This file is run in command line by completing the following steps:  
1.Compile the code with the following:  

javac adjPrimAlg.java adjList.java adjMatrix.java Edge.java  
OR    
javac *.java

2.Run the program with the following:  

java adjPrimAlg input.txt  

The input file should have the following format:  

NUMBER (number of nodes that are in the graph)   
LIST (list of pairs of nodes with edges, along with their weight)

EXAMPLE INPUT FILE:

3  
3 2 3  
2 1 5  
1 3 4

This input file represents a graph with 3 vertices, with an edge between 3 and 2 with weight 3, an edge between 2 and 1 with weight 5, and an edge between 1 and 3 with weight 4

>>> TO USE GRAPH GENERATOR  
    -edit source code GraphGenerator.java  
    -use the GraphGenerator object's generateGraph(String outputFile, int minVertices, int maxVertices, bool sparse) method to generate graph input files.  
    -javac GraphGenerator.java  
    -java GraphGenerator  
>>> CAN USE GRAPH GENERATOR OUTPUT FILES AS INPUT FILES FOR PRIMS  

This program produces an output.txt file that prints the minimum spanning tree for this graph along with the total computation time in the following format:  

Problem #  
Adjacency List:  
Edge : Weight  
Node - Node : weight  
...  
Total computation time: 0.0 seconds

Adjacency Matrix:  
Edge : Weight  
Node - Node : weight  
...  
Total computation time: 0.0 seconds
