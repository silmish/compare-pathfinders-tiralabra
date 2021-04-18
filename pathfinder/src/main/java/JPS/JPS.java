/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPS;

import ImageHandler.Vertex;
import java.util.PriorityQueue;

/**
 *
 * @author pate
 */
public class JPS {

    public char[][] map;
    public double[][] distance;
    public Vertex[][] predecessor;
    public Vertex[][] successor;
    PriorityQueue<Vertex> queue;
    Vertex vertex = new Vertex(0, 0, 0);

    public void searchPath(int x, int y) {

        queue = new PriorityQueue<>();

        boolean[][] visited = new boolean[512][512];

        queue.add(new Vertex(x, y, 0));
        predecessor[x][y] = null;
        distance[x][y] = 0;

        while (true) {

            int endX = vertex.endVertex.getX();
            int endY = vertex.endVertex.getY();

            Vertex currentVertex = queue.poll();

            if (currentVertex.getX() == endX && currentVertex.getY() == endY) {
                //Add here pathdrawing
                System.out.println("Path found");
                break;
            }

            if (queue.isEmpty()) {
                System.out.println("No path to endpoint");
                break;
            }

        }

    }

    public Vertex[] checkNeighbours(Vertex vertex) {
        int x = vertex.getX();
        int y = vertex.getY();
        int index = 0;

        Vertex[] neighbours = new Vertex[8];

        if (predecessor[x][y] == null) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (map[x + i][y + j] == '.') {
                        
                        neighbours[index] = new Vertex(x + i, y + j, 0);
                    }
                }
                index++;
            }

        }

        return neighbours;

    }
    
    
    
    

}
