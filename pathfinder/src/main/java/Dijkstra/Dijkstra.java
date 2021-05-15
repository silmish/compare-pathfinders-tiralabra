/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

import DataStructures.MinHeap;
import ImageHandler.Vertex;
import java.util.PriorityQueue;

/**
 * Finds shortest path using Dijkstra's algorithm using Minimum heap
 *
 * @author pate
 */
public class Dijkstra {

    public char[][] map;
    public double[][] distance;
    public Vertex[][] predecessor;
    public MinHeap queue;

    /**
     * Starts the algorithm on the given coordinates
     * @param x Start X coordinate
     * @param y Start Y coordinate
     */
    public void findPath(int x, int y) {

        queue = new MinHeap();

        boolean[][] visited = new boolean[512][512];

        queue.add(new Vertex(x, y, 0));
        distance[x][y] = 0;
        predecessor[x][y] = null;

        while (!queue.isEmpty()) {

            Vertex currentVertex = queue.poll();
            if (!visited[currentVertex.getX()][currentVertex.getY()]) {
                checkNeighbours(currentVertex);
            }

            visited[currentVertex.getX()][currentVertex.getY()] = true;
        }

    }

    /**
     *
     * @param vertex checks for eligible neighbour from the current Vertex If
     * found, updates distance to the Vertex and add it to the queue.
     *
     */
    public void checkNeighbours(Vertex vertex) {

        int x = vertex.getX();
        int y = vertex.getY();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0 || x + i < 0 || y + j < 0) {
                    continue;
                }
                if (map[x + i][y + j] == '.') {
                    double weight = 1;
                    if (i != 0 && j != 0) {
                        weight = Math.sqrt(2);
                    }

                    if (distance[x + i][y + j] > distance[x][y] + weight) {
                        distance[x + i][y + j] = distance[x][y] + weight;

                        Vertex adjacentVertex = new Vertex(x + i, y + j, distance[x + i][y + j]);
                        predecessor[x + i][y + j] = new Vertex(x, y, 0);
                        queue.add(adjacentVertex);

                    }
                }

            }

        }

    }

    /**
     * Prints shortest distance to the end Vertex
     *
     * @param x end vertex X position
     * @param y end vertex Y position
     * @return distance to end
     */
    public double printDistance(int x, int y) {
        return round(distance[x][y], 1);
    }
    
    /**
     * 
     * @param value double value that is wanted to be rounded.
     * @param precision which precision, how many decimals are wanted.
     * @return Rounded value
     */
    public double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    /**
     *Generates path from end vertex to start vertex after algorithm is done
     * returns all parent vertexes from end to start and sends it to ImageHandler.
     * @param x
     * @param y
     * @return path[]
     */
    public Vertex[] printPath(int x, int y) {
        int index = 0;
        Vertex[] path = new Vertex[100000];

        while (true) {
            Vertex current = predecessor[x][y];

            path[index] = current;

            if (current == null) {
                break;
            }

            x = current.getX();
            y = current.getY();

            index++;
        }
        return path;
    }

}
