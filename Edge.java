//package cs375Project;
//import java.util.*;
public class Edge{
    private int weight;
    private boolean included = false;
    private int source;
    private int dest;

    public Edge(int source, int dest, int weight){
        this.source = source;
        this.dest = dest;
        this.weight = weight;
        this.included = false;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }
}