/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPS;

import ImageHandler.ImageHandler;
import JPS.Vertex;
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
    public boolean[][] visited;
    PriorityQueue<Vertex> queue;
    Vertex vertex = new Vertex(0, 0, 0);
    Vertex end = new Vertex(0, 0, 0);
    Vertex start = new Vertex(0, 0, 0);

    public void searchPath(Vertex startVertex, Vertex endVertex) {
        int x = startVertex.getX();
        int y = startVertex.getY();
        queue = new PriorityQueue<>();
        visited = new boolean[512][512];
        queue.add(new Vertex(x, y, 0));
        predecessor[y][x] = null;
        distance[y][x] = 0;
        end = endVertex;
        start = startVertex;
        int endX = endVertex.getX();
        int endY = endVertex.getY();

        while (!queue.isEmpty()) {

            if (queue.isEmpty()) {
                break;
            }
            Vertex currentVertex = queue.poll();
            //System.out.println(currentVertex);

            if (currentVertex.getX() == endX && currentVertex.getY() == endY) {
                System.out.println("Distance to end: " + distance[startVertex.getY()][startVertex.getX()]);
                System.out.println("Path found");
                break;
            }

            Vertex[] jumpSuccessors = new Vertex[9];

            jumpSuccessors = successors(currentVertex);

            for (int i = 0; i < jumpSuccessors.length; i++) {
                if (jumpSuccessors[i] != null) {
                    //System.out.println(jumpSuccessors[i]);
                    queue.add(jumpSuccessors[i]);
                }
            }
        }
    }

    public Vertex[] successors(Vertex vertex) {
        int x = vertex.getX();
        int y = vertex.getY();
        Vertex[] successors = new Vertex[9];

        int parentX = 0;
        int parentY = 0;
        int directionX = 0;
        int directionY = 0;

        if (!visited[vertex.getY()][vertex.getX()]) {
            Vertex[] neighbours = checkNeighbours(vertex);
            visited[vertex.getY()][vertex.getX()] = true;

            //System.out.println(Arrays.toString(neighbours));
            if (predecessor[y][x] != null) {
                parentX = predecessor[y][x].getX();
                parentY = predecessor[y][x].getY();
            }

            //System.out.println(vertex + " " + Arrays.toString(neighbours));

            for (int i = 0; i < neighbours.length; i++) {
                if (neighbours[i] != null) {
                    predecessor[neighbours[i].getY()][neighbours[i].getX()] = predecessor[y][x];
                    if (predecessor[y][x] != null) {
                        directionX = (neighbours[i].getX() - x) / Math.max(Math.abs(neighbours[i].getX() - x), 1);
                        directionY = (neighbours[i].getY() - y) / Math.max(Math.abs(neighbours[i].getY() - y), 1);
                        // System.out.println("OTHER");
                    } else {
                        directionX = (end.getX() - x) / Math.max(Math.abs(end.getX() - x), 1);
                        directionY = (end.getY() - y) / Math.max(Math.abs(end.getY() - y), 1);
                        // System.out.println("END");
                    }
                    Vertex jump = jump(neighbours[i], directionX, directionY);
                    //System.out.println(jump + "Successors metodi");
                    //System.out.println(neighbours[i].getPredecessor());
                    if (jump == null) {
                        continue;
                    }
                    
                    double currentDistance = this.distance[jump.getY()][jump.getX()];
                    double jumpDistance = jumpDistance(vertex, jump, directionX, directionY);
                    double newDistance = this.distance[vertex.getY()][vertex.getX()] + jumpDistance;
                    /*System.out.println("Current: " + currentDistance);
                    System.out.println("New: " + newDistance);
                    System.out.println("Jump: " + jumpDistance);*/
                    
                    if (currentDistance > newDistance) {
                        this.distance[jump.getY()][jump.getX()] = newDistance;
                        this.predecessor[jump.getY()][jump.getX()] = neighbours[i];
                        jump.setDistance(distanceToEnd(jump, end));
                        //System.out.println(jump.getDistance());
                    }
                    successors[i] = jump;
                }
            }

        }
        return successors;
    }

    public Vertex jump(Vertex vertex, int dx, int dy) {

        int x = vertex.getX();
        int y = vertex.getY();
        //System.out.println("Current X: "+x + " "+"Direction X: " +dx+ " " +"Current Y: "+y+ " "+"Direction Y: "+dy);
        //System.out.println(directionX + " "+ directionY);
        //System.out.println(vertex);
        Vertex next = new Vertex(vertex.getX() + dx, vertex.getY() + dy, Double.MAX_VALUE);

        //System.out.println(next);
        if (map[next.getY()][next.getX()] != '.') {
            //System.out.println("eka");
            return null;
        }
        if (y == end.getY() && x == end.getX()) {
            //System.out.println("PATH FOUND !!!!!!!!!!!!!!!!!!!!!");
            return vertex;
        }

        if (dy == 0) {
            if (map[next.getY() + 1][next.getX()] != '.' && map[next.getY() + 1][next.getX()] == '.'
                    || map[next.getY() - 1][next.getX()] != '.' && map[next.getY() - 1][next.getX()] == '.') {
                //System.out.println("kolmas");
                return next;
            }
        } else if (dx == 0) {
            if (map[next.getY()][next.getX() + 1] != '.' && map[next.getY()][next.getX() + 1] == '.'
                    || map[next.getY()][next.getX() - 1] != '.' && map[next.getY()][next.getX() - 1] == '.') {
                //System.out.println("neljäs");
                return next;
            }
        }

        if (dx != 0 && dy != 0) {
            if (jump(next, 0, dy) != null) {
                //System.out.println("viides");
                return next;
            }
            if (jump(next, dx, 0) != null) {
                //System.out.println("kuudes");
                return next;
            }
        }
        //System.out.println("Hyppy");
        return jump(next, dx, dy);
    }

    public Vertex[] checkNeighbours(Vertex vertex) {
        Vertex[] neighbours = new Vertex[9];
        int x = vertex.getX();
        int y = vertex.getY();
        int parentX = 0;
        int parentY = 0;
        int index = 0;
        int directionX = 0;
        int directionY = 0;
        /*if (predecessor[y][x] == null) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if (map[y + i][x + j] == '.') {
                        neighbours[index] = new Vertex(x + j, y + i, Double.MAX_VALUE);
                        index++;
                    }
                }
            }
            return neighbours;
        } else {

        }*/
        if (predecessor[y][x] != null) {
            parentX = predecessor[y][x].getX();
            parentY = predecessor[y][x].getY();
            directionX = (x - parentX) / Math.max(Math.abs(x - parentX), 1);
            directionY = (y - parentY) / Math.max(Math.abs(y - parentY), 1);
            //System.out.println("OTHER");
        } else {
            directionX = (end.getX() - x) / Math.max(Math.abs(end.getX() - x), 1);
            directionY = (end.getY() - y) / Math.max(Math.abs(end.getY() - y), 1);
            //System.out.println("END");
        }

        //System.out.println(directionX + " " + directionY);
        if (directionX != 0 && directionY != 0) {
            if (map[y][x + directionX] == '.') {
                neighbours[0] = new Vertex(x, y + directionY, Math.sqrt(2));

            }
            if (map[y + directionY][x] == '.') {
                neighbours[1] = new Vertex(x + directionX, y, Math.sqrt(2));

            }
            if (map[y + directionY][x + directionX] == '.') {
                neighbours[2] = new Vertex(x + directionX, y + directionY, Math.sqrt(2));

            }
            if (map[y - directionY][x] != '.') {
                neighbours[3] = new Vertex(x - directionX, y + directionY, Math.sqrt(2));
            }
            if (map[y][x - directionX] != '.') {
                neighbours[4] = new Vertex(x + directionX, y - directionY, Math.sqrt(2));
                //predecessor[x + directionX][y - directionY] = vertex;
            }
        } else {
            if (directionX == 0) {
                if (map[y][x + directionX] == '.') {
                    neighbours[0] = new Vertex(x, y + directionY, 1);
                    //predecessor[x][y + directionY] = vertex;
                }
                if (map[y + 1][x] != '.') {
                    neighbours[1] = new Vertex(x + 1, y + directionY, 1);
                    //predecessor[x][y + directionY] = vertex;
                }
                if (map[y - 1][x] != '.') {
                    neighbours[2] = new Vertex(x - 1, y + directionY, 1);
                    //predecessor[x - 1][y + directionY] = vertex;
                }
            } else {

                if (map[y + directionY][x] == '.') {
                    neighbours[0] = new Vertex(x + directionX, y, 1);
                    //predecessor[x + directionX][y] = vertex;
                }
                if (map[y][x + 1] != '.') {
                    neighbours[1] = new Vertex(x + directionX, y + 1, 1);
                    //predecessor[x + directionX][y + 1] = vertex;
                }
                if (map[y][x - 1] != '.') {
                    neighbours[2] = new Vertex(x + directionX, y - 1, 1);
                    //predecessor[x + directionX][y - 1] = vertex;
                }
            }
        }
        return neighbours;
    }

    public double jumpDistance(Vertex from, Vertex to, int dx, int dy) {

        int differenceX = from.getX() - to.getX();
        int differenceY = from.getX() - to.getY();

        if (differenceX < 0) {
            differenceX = -differenceX;
        }
        if (differenceY < 0) {
            differenceY = -differenceY;
        }
        if (dx == 0 || dy == 0) {
            return differenceX + differenceY;
        } else {
            return differenceX * Math.sqrt(2);
        }
    }

    public double distanceToEnd(Vertex current, Vertex end) {
        double differenceX = current.getX() - end.getX();
        double differenceY = current.getY() - end.getY();

        double estimate = Math.sqrt((differenceX * differenceX) + (differenceY * differenceY));

        return estimate;
    }

    public Vertex[] printPath(Vertex start, Vertex end) {
        int index = 1;
        Vertex[] path = new Vertex[262144];
        Vertex current = end;
        path[0] = end;
        distance[end.getY()][end.getX()] = 0;

        while (current.getX() != start.getX() && current.getY() != start.getY()) {
            //System.out.println(current);
            Vertex previousJump = predecessor[current.getY()][current.getX()];
            //System.out.println(previousJump);
            //System.out.println("DirectionX: "+directionX+ " "+"DirectionY: "+directionY);
            while (true) {
                if (current.getX() == previousJump.getX() && current.getY() == previousJump.getY()) {
                    break;
                }
                int directionX = (previousJump.getX() - current.getX()) / Math.max(Math.abs(previousJump.getX() - current.getX()), 1);
                int directionY = (previousJump.getY() - current.getY()) / Math.max(Math.abs(previousJump.getY() - current.getY()), 1);
                current = new Vertex(current.getX() + directionX, current.getY() + directionY, 0);
                
               /* double weight = 0;
                if(directionX != 0 && directionY != 0) {
                    weight = Math.sqrt(2);
                }else {
                    weight = 1;
                }
                if(distance[current.getY()+directionY][current.getX()+directionX] > distance[current.getY()][current.getX()]+weight) {
                    distance[current.getY()+directionY][current.getX()+directionX] = distance[current.getY()][current.getX()]+weight;
                    System.out.println(distance[current.getY()][current.getX()]+weight);
                }*/
                
                path[index] = current;
                index++;
            }

        }
        return path;
    }
}
