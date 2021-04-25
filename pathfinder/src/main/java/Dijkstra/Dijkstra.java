/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

import ImageHandler.Vertex;
import java.util.PriorityQueue;

/**
 *
 * @author pate
 */
public class Dijkstra {

    public char[][] map;
    public double[][] distance;
    public Vertex[][] predecessor;
    PriorityQueue<Vertex> queue;

    public void findPath(int x, int y) {

        queue = new PriorityQueue<>();

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

    public double printDistance(int x, int y) {
        return distance[x][y];
    }

    public Vertex[] printPath(int x, int y) {
        int index = 0;
        Vertex[] path = new Vertex[262144];

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
