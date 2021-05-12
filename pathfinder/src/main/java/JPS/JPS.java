/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPS;

import ImageHandler.ImageHandler;
import ImageHandler.Vertex;
import java.time.Duration;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.time.Instant;

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
    Vertex end = new Vertex(0, 0, 0);
    Vertex start = new Vertex(0, 0, 0);

    public String searchPath(Vertex startVertex, Vertex endVertex) {
        queue = new PriorityQueue<>();
        visited = new boolean[512][512];
        queue.add(startVertex);
        distance[startVertex.getY()][startVertex.getX()] = 0;
        end = endVertex;
        int endX = endVertex.getX();
        int endY = endVertex.getY();
        Vertex[] neighbours = new Vertex[9];
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            //System.out.println(current);
            if (current.getX() == endX && current.getY() == endY) {
                return "Path found";
            }

            if (!visited[current.getY()][current.getX()]) {
                visited[current.getY()][current.getX()] = true;
                neighbours = pruneNeighbours(current);

                //System.out.println(Arrays.toString(neighbours));
                for (int i = 0; i < neighbours.length; i++) {
                    if (neighbours[i] != null) {
                        int directionX = movementDirection(neighbours[i].getX(), current.getX());
                        int directionY = movementDirection(neighbours[i].getY(), current.getY());
                        Vertex jump = jump(current, directionX, directionY);

                        if (jump == null) {
                            continue;
                        }

                        double currentDistance = distance[jump.getY()][jump.getX()];
                        double jumpDistance = jumpDistance(current, jump, directionX, directionY);
                        double newDistance = distance[current.getY()][current.getX()] + jumpDistance;

                        if (newDistance < currentDistance) {
                            distance[jump.getY()][jump.getX()] = newDistance;
                            predecessor[jump.getY()][jump.getX()] = current;
                            jump.setDistance(distanceToEnd(jump,end));
                            queue.add(jump);
                        }
                    }
                }
            }
        }

        return "No path to point";
    }

    public Vertex[] pruneNeighbours(Vertex vertex) {
        Vertex[] neighbours = new Vertex[8];
        int x = vertex.getX();
        int y = vertex.getY();
        int index = 0;

        if (predecessor[y][x] == null) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if (i == 0 && j == 0 || (map[y + i][x + j] != '.')) {
                        continue;
                    }

                    if (y + i == y || x + j == x) {
                        neighbours[index] = new Vertex(x + j, y + i, 1);
                    } else {
                        if (map[y + i][x] == '.' && map[y][x + j] == '.') {
                            neighbours[index] = new Vertex(x + j, y + i, Math.sqrt(2));
                        }
                    }

                    index++;
                }
            }
            return neighbours;
        }
        int directionX = movementDirection(vertex.getX(), predecessor[y][x].getX());
        int directionY = movementDirection(vertex.getY(), predecessor[y][x].getY());

        if (directionX != 0 && directionY != 0) {
            if (map[y + directionY][x] == '.') {
                neighbours[0] = new Vertex(x, y + directionY, 0);
            }
            if (map[y][x + directionX] == '.') {
                neighbours[1] = new Vertex(x + directionX, y, 0);
            }
            if (map[y + directionY][x] == '.' || map[y][x + directionX] == '.'){  //&& map[y + directionY][x + directionX] == '.') {
                neighbours[2] = new Vertex(x + directionX, y + directionY, 0);
            }
            if (map[y][x - directionX] != '.' && map[y + directionY][x] == '.'){ //&& map[y + directionY][x - directionX] == '.') {
                neighbours[3] = new Vertex(x - directionX, y + directionY, 0);
            }
            if (map[y - directionY][x] != '.' && map[y][x + directionX] == '.'){ //&& map[y - directionY][x + directionX] == '.') {
                neighbours[4] = new Vertex(x + directionX, y - directionY, 0);
            }
        } else {
            if (directionX == 0) {
                if (map[y + directionY][x] == '.') {
                    neighbours[0] = new Vertex(x, y + directionY, 0);
                    if (map[y][x + 1] != '.' && map[y + directionY][x + 1] == '.') {
                        neighbours[1] = new Vertex(x + 1, y + directionY, 0);
                    }
                    if (map[y][x - 1] != '.' && map[y + directionY][x - 1] == '.') {
                        neighbours[2] = new Vertex(x - 1, y + directionY, 0);
                    }
                }
            } else {
                if (map[y][x + directionX] == '.') {
                    neighbours[0] = new Vertex(x + directionX, y, 0);
                    if (map[y + 1][x] != '.') {
                        neighbours[1] = new Vertex(x + directionX, y + 1, 0);
                    }
                    if (map[y - 1][x] != '.') {
                        neighbours[2] = new Vertex(x + directionX, y - 1, 0);
                    }
                }
            }
        }
        return neighbours;
    }

    private Vertex jump(Vertex vertex, int dx, int dy) {

        Vertex next = new Vertex(vertex.getX() + dx, vertex.getY() + dy, Double.MAX_VALUE);
        //System.out.println(next);
        int x = next.getX();
        int y = next.getY();

        if (map[y][x] != '.') {
            return null;
        }

        if (x == end.getX() && y == end.getY()) {
            System.out.println("PATH FOUND!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
            return next;
        }

        if (dx != 0 && dy != 0) {
            if ((map[y + dy][x - dx] == '.' && map[y][x - dx] != '.')
                    || (map[y - dy][x + dx] == '.' && map[y - dy][x] != '.')) {
                System.out.println("eka");
                return next;
            }
        } else {
            if (dx != 0) {
                if ((map[y + 1][x + dx] == '.' && map[y + 1][x] != '.')
                        || (map[y - 1][x + dx] == '.' && map[y - 1][x] != '.')) {
                    System.out.println("toka");
                    return next;
                }
            } else {
                if ((map[y + dy][x + 1] == '.' && map[y][x + 1] != '.')
                        || (map[y + dy][x - 1] == '.' && map[y][x - 1] != '.')) {
                    System.out.println("kolmas");
                    return next;
                }
            }
        }
        if (dx != 0 && dy != 0) {

            if (jump(next, 0, dy) != null || jump(next, dx, 0) != null) {
                System.out.println("neljäs");
                return next;
            }
        }
        return jump(next, dx, dy);
    }

    public int movementDirection(int to, int from) {
        int direction = to - from;
        if (direction > 0) {
            return 1;
        } else {
            if (direction < 0) {
                return -1;
            }
        }
        return 0;
    }

    public double distanceToEnd(Vertex current, Vertex end) {
        double differenceX = current.getX() - end.getX();
        double differenceY = current.getY() - end.getY();

        double estimate = Math.sqrt((differenceX * differenceX) + (differenceY * differenceY));

        return estimate;
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

    public Vertex[] printPath(Vertex start, Vertex end) {
        Vertex[] path = new Vertex[100000];
        path[0] = end;
        Vertex current = end;
        int index = 1;

        while (current.getX() != start.getX() && current.getX() != start.getY()) {
            Vertex previousJump = predecessor[current.getY()][current.getX()];
            System.out.println(previousJump);

            while (true) {
                int directionX = movementDirection(previousJump.getX(), current.getX());
                int directionY = movementDirection(previousJump.getY(), current.getY());
                if (current.getX() == previousJump.getX() && current.getY() == previousJump.getY()) {
                    break;
                }
                current = new Vertex(current.getX() + directionX, current.getY() + directionY, 0);
                path[index] = current;
                index++;
            }
        }

        return path;
    }

}
