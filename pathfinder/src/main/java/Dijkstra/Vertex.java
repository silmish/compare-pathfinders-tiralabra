/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;


/**
 *
 * @author pate
 */
public class Vertex implements Comparable<Vertex> {

    private int x, y, cost;
    public Vertex[] adjacent;
    private boolean visited;
    private Vertex predecessor;
    private int distance = Integer.MAX_VALUE;
    public Vertex startVertex;
    public Vertex endVertex;

    public Vertex(int x, int y, int cost) {
        adjacent = new Vertex[8];
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setStartVertex(Vertex vertex) {
        this.startVertex = vertex;
    }

    public void setEndVertex(Vertex vertex) {
        this.endVertex = vertex;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Vertex{" + " X = " + this.x + " Y = " + this.y + " Distance = " + this.distance + '}';
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.distance, otherVertex.getDistance());
    }

}
