/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPS;

import ImageHandler.Vertex;
import java.util.Arrays;
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
    Vertex end = new Vertex(229, 159, 0);

    public void searchPath(int x, int y) {

        queue = new PriorityQueue<>();

        boolean[][] visited = new boolean[512][512];
        queue.add(new Vertex(x, y, 0));
        predecessor[x][y] = null;
        distance[x][y] = 0;
        int endX = end.getX();
        int endY = end.getY();

        while (true) {

            if (queue.isEmpty()) {
                System.out.println("No path to endpoint");
                break;
            }
            Vertex currentVertex = queue.poll();
            visited[currentVertex.getX()][currentVertex.getY()] = true;
            

            if (currentVertex.getX() == endX && currentVertex.getY() == endY) {
                //Add here pathdrawing
                System.out.println("Path found");
                break;
            }

            Vertex[] successors = successors(currentVertex);
            for (int i = 0; i < successors.length; i++) {
                if (successors[i] != null && !visited[successors[i].getX()][successors[i].getY()]) {
                    //System.out.println(successors[i]);
                    queue.add(successors[i]);
                }
            }
        }
    }

    public Vertex[] successors(Vertex vertex) {
        int x = vertex.getX();
        int y = vertex.getY();
        Vertex[] successors = new Vertex[9];
        Vertex[] neighbours = checkNeighbours(vertex);
        int parentX = 0;
        int parentY = 0;

        //System.out.println(Arrays.toString(neighbours));
        if (predecessor[x][y] != null) {
            parentX = predecessor[x][y].getX();
            parentY = predecessor[x][y].getY();
        }

        for (int i = 0; i < neighbours.length; i++) {
            Vertex jump = jump(neighbours[i],x, y, parentX, parentY);
            //System.out.println(jump);
            successors[i] = jump;
        }
        return successors;
    }

    // Jump forward from each neighbour of a jump point
    public Vertex jump(Vertex vertex,int x, int y, int parentX, int parentY) {
        while (true) {
            int directionX = (x - parentX) / Math.max(Math.abs(x - parentX), 1);
            int directionY = (y - parentY) / Math.max(Math.abs(y - parentY), 1);

            if (map[x][y] != '.') {
                return null;
            }
            if (x == end.getX() && y == end.getY()) {
                //System.out.println(new Vertex(x, y, 0));
                predecessor[x][y] = vertex;
                return new Vertex(x, y, 0);
            }

            if (directionX == 0 && directionY == 0) {
                if (directionX != 0) {
                    if (map[x + directionX][y + 1] == '.' && map[x][y + 1] != '.'
                            || map[x + directionX][y - 1] == '.' && map[x][y - 1] != '.') {
                        predecessor[x][y] = vertex;
                        return new Vertex(x, y, 0);
                    }
                } else {
                    if (map[x + 1][y + directionY] == '.' && map[x + 1][y] != '.'
                            || map[x - 1][y + directionY] == '.' && map[x - 1][y] != '.') {
                        predecessor[x][y] = vertex;
                        return new Vertex(x, y, 0);
                    }
                }
            } else if (directionX != 0 && directionY != 0) {
                if (map[x + directionX][y + directionY] == '.' && map[x + directionX][y] == '.' && map[x][y + directionY] == '.') {
                    predecessor[x][y] = vertex;
                    return new Vertex(x, y, 0);
                }

            } else if (jump(vertex,x + directionX, y, x, y) != null || jump(vertex,x, y + directionY, x, y) != null) {
                predecessor[x][y] = vertex;
                return new Vertex(x, y, 0);

            }
            
            x += directionX;
            y += directionY;
        }
    }

    public Vertex[] checkNeighbours(Vertex vertex) { // add predecessor information to neighbours
        Vertex[] neighbours = new Vertex[9];
        int x = vertex.getX();
        int y = vertex.getY();
        int parentX = 0;
        int parentY = 0;
        int index = 0;

        if (predecessor[x][y] != null) {
            parentX = predecessor[x][y].getX();
            parentY = predecessor[x][y].getY();
        }

        if (predecessor[x][y] == null) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (map[x + i][y + j] == '.') {
                        neighbours[index] = new Vertex(x + i, y + j, 0);
                        index++;
                    }
                }
            }
            return neighbours;
        }
        int directionX = (x - parentX) / Math.max(Math.abs(x - parentX), 1);
        int directionY = (y - parentY) / Math.max(Math.abs(y - parentY), 1);

        //System.out.println(directionX + " " + directionY);
        if (directionX != 0 && directionY != 0) {
            if (map[x][y + directionY] == '.') {
                neighbours[0] = new Vertex(x, y + directionY, 0);
                predecessor[x][y + directionY] = new Vertex(x, y, 0);
            }
            if (map[x + directionX][y] == '.') {
                neighbours[1] = new Vertex(x + directionX, y, 0);
                predecessor[x + directionX][y] = new Vertex(x, y, 0);
            }
            if (map[x + directionX][y + directionY] == '.') {
                neighbours[2] = new Vertex(x + directionX, y + directionY, 0);
                predecessor[x + directionX][y + directionY] = new Vertex(x, y, 0);
            }
            if (map[x - directionX][y] != '.') {
                neighbours[3] = new Vertex(x - directionX, y + directionY, 0);
                predecessor[x - directionX][y + directionY] = new Vertex(x, y, 0);
            }
            if (map[x][y - directionY] != '.') {
                neighbours[4] = new Vertex(x + directionX, y - directionY, 0);
                predecessor[x + directionX][y - directionY] = new Vertex(x, y, 0);
            }
        } else {
            if (directionX == 0) {
                if (map[x][y + directionY] == '.') {
                    neighbours[0] = new Vertex(x, y + directionY, 0);
                    predecessor[x][y + directionY] = new Vertex(x, y, 0);
                }
                if (map[x + 1][y] != '.') {
                    neighbours[1] = new Vertex(x + 1, y + directionY, 0);
                    predecessor[x][y + directionY] = new Vertex(x, y, 0);
                }
                if (map[x - 1][y] != '.') {
                    neighbours[2] = new Vertex(x - 1, y + directionY, 0);
                    predecessor[x - 1][y + directionY] = new Vertex(x, y, 0);
                }
            } else {

                if (map[x + directionX][y] == '.') {
                    neighbours[0] = new Vertex(x + directionX, y, 0);
                    predecessor[x + directionX][y] = new Vertex(x, y, 0);
                }
                if (map[x][y + 1] != '.') {
                    neighbours[1] = new Vertex(x + directionX, y + 1, 0);
                    predecessor[x + directionX][y + 1] = new Vertex(x, y, 0);
                }
                if (map[x][y - 1] != '.') {
                    neighbours[2] = new Vertex(x + directionX, y - 1, 0);
                    predecessor[x + directionX][y - 1] = new Vertex(x, y, 0);
                }
            }
        }
        return neighbours;
    }
}
