/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import DataStructures.MinHeap;
import ImageHandler.Vertex;


/**
 * Finds shortest path using Jump Point Search algorithm
 *
 * @author pate
 */
public class JPS {

    public char[][] map;
    public double[][] distance;
    public Vertex[][] predecessor;
    public boolean[][] visited;
    MinHeap queue;
    Vertex end = new Vertex(0, 0, 0);

    /**
     * Starts the algorithm with given start and end Vertex. Polls next Vertex
     * from heap with shortest estimate distance to the end Vertex Adds vertexes
     * to the heap if not visited before or distance is shorter that previously
     * known.
     *
     * @param startVertex Given start Vertex.
     * @param endVertex Given end Vertex.
     * @return
     */
    public String findPath(Vertex startVertex, Vertex endVertex) {
        queue = new MinHeap();
        visited = new boolean[512][512];
        queue.add(startVertex);
        distance[startVertex.getX()][startVertex.getY()] = 0;
        end = endVertex;
        int endX = endVertex.getX();
        int endY = endVertex.getY();
        Vertex[] neighbours = new Vertex[9];
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current.getX() == endX && current.getY() == endY) {
                return "Path found";
            }

            if (!visited[current.getX()][current.getY()]) {
                visited[current.getX()][current.getY()] = true;
                neighbours = pruneNeighbours(current);

                for (int i = 0; i < neighbours.length; i++) {
                    if (neighbours[i] != null) {
                        int directionX = movementDirection(neighbours[i].getX(), current.getX());
                        int directionY = movementDirection(neighbours[i].getY(), current.getY());
                        Vertex jump = jump(current, directionX, directionY);

                        if (jump == null) {
                            continue;
                        }

                        double currentDistance = distance[jump.getX()][jump.getY()];
                        double jumpDistance = jumpDistance(current, jump, directionX, directionY);
                        double newDistance = distance[current.getX()][current.getY()] + jumpDistance;

                        if (newDistance < currentDistance) {
                            distance[jump.getX()][jump.getY()] = newDistance;
                            predecessor[jump.getX()][jump.getY()] = current;
                            jump.setDistance(distanceToEnd(jump, end) + newDistance);
                            queue.add(jump);
                        }
                    }
                }
            }

        }
        return "No path to point";
    }

    /**
     *
     * @param vertex Next Vertex to be pruned for neighbours. Prunes only the
     * neighbours that match the direction where to travel.
     * @return Eligible neighbours that can be traveled to.
     *
     */
    public Vertex[] pruneNeighbours(Vertex vertex) {
        Vertex[] neighbours = new Vertex[8];
        int x = vertex.getX();
        int y = vertex.getY();
        int index = 0;

        if (predecessor[x][y] == null) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {

                    if (i == 0 && j == 0 || (map[x + i][y + j] != '.')) {
                        continue;
                    }

                    if (x + i == y || y + j == x) {
                        neighbours[index] = new Vertex(x + i, y + j, 1);
                    } else {
                        if (map[x + i][y] == '.' && map[x][y + j] == '.') {
                            neighbours[index] = new Vertex(x + i, y + j, Math.sqrt(2));
                        }
                    }

                    index++;
                }
            }
            return neighbours;
        }
        int directionX = movementDirection(vertex.getX(), predecessor[x][y].getX());
        int directionY = movementDirection(vertex.getY(), predecessor[x][y].getY());

        if (directionX != 0 && directionY != 0) {
            if (map[x][y + directionY] == '.') {
                neighbours[0] = new Vertex(x, y + directionY, 0);
            }
            if (map[x + directionX][y] == '.') {
                neighbours[1] = new Vertex(x + directionX, y, 0);
            }
            if (map[x][y + directionY] == '.' || map[x + directionX][y] == '.') {
                neighbours[2] = new Vertex(x + directionX, y + directionY, 0);
            }
            if (map[x - directionX][y] != '.' && map[x][y + directionY] == '.') {
                neighbours[3] = new Vertex(x - directionX, y + directionY, 0);
            }
            if (map[x][y - directionY] != '.' && map[x + directionX][y] == '.') {
                neighbours[4] = new Vertex(x + directionX, y - directionY, 0);
            }
        } else {
            if (directionX == 0) {
                if (map[x][y + directionY] == '.') {
                    neighbours[0] = new Vertex(x, y + directionY, 0);
                }
                if (map[x + 1][y] != '.') {
                    neighbours[1] = new Vertex(x + 1, y + directionY, 0);
                }
                if (map[x - 1][y] != '.') {
                    neighbours[2] = new Vertex(x - 1, y + directionY, 0);
                }
            } else {
                if (map[x + directionX][y] == '.') {
                    neighbours[0] = new Vertex(x + directionX, y, 0);
                }
                if (map[x][y + 1] != '.') {
                    neighbours[1] = new Vertex(x + directionX, y + 1, 0);
                }
                if (map[x][y - 1] != '.') {
                    neighbours[2] = new Vertex(x + directionX, y - 1, 0);
                }
            }
        }
        return neighbours;
    }

    /**
     * Jumps towards the direction defined by Vertex neighbours.
     *
     * @param vertex Current Vertex which is the starting point.
     * @param dx X axis direction where to jump.
     * @param dy Y axis direction where to jump. If no Vertex found that is
     * either end Vertex or forced neighbour, method recurses.
     * @return
     */
    private Vertex jump(Vertex vertex, int dx, int dy) {

        Vertex next = new Vertex(vertex.getX() + dx, vertex.getY() + dy, 0);
        int x = next.getX();
        int y = next.getY();

        if (map[x][y] != '.') {
            return null;
        }

        if (x == end.getX() && y == end.getY()) {
            return next;
        }

        if (dx != 0 && dy != 0) {
            if (map[x][y] != '.' && map[x + dx][y] == '.' && map[x][y + dy] == '.') {
                return next;
            }
        }
        if (dx == 0 || dy == 0) {
            if (dx != 0) {
                if ((map[x + dx][y + 1] == '.' && map[x][y + 1] != '.')
                        || (map[x + dx][y - 1] == '.' && map[x][y - 1] != '.')) {
                    return next;
                }
            } else {
                if ((map[x + 1][y + dy] == '.' && map[x + 1][y] != '.')
                        || (map[x - 1][y + dy] == '.' && map[x - 1][y] != '.')) {
                    return next;
                }
            }
        }
        if (dx != 0 && dy != 0) {

            if (jump(next, 0, dy) != null || jump(next, dx, 0) != null) {
                return next;
            }
        }

        return jump(next, dx, dy);
    }

    /**
     * Defines the direction where to move.
     *
     * @param to Vertex coordinate which moved towards.
     * @param from Vertex coordinate which moved from.
     * @return either 1, -1, 0 depending on the direction.
     */
    public int movementDirection(int to, int from) {
        int direction = to - from;
        if (direction > 0) {
            return 1;
        } else if (direction < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * Estimates distance to end Vertex from current Vertex.
     *
     * @param current Vertex that is being looked at.
     * @param end end Vertex that is chosen.
     * @return Estimate distance to end Vertex.
     */
    public double distanceToEnd(Vertex current, Vertex end) {
        double differenceX = current.getX() - end.getX();
        double differenceY = current.getY() - end.getY();

        double estimate = Math.sqrt((differenceX * differenceX) + (differenceY * differenceY));

        return round(estimate,2);
    }

    /**
     * Defines the distance between Vertex that is currently processed and the found jump point.
     * @param from Current Vertex in process.
     * @param to Jump point that was found.
     * @param dx Direction of X axis where currently moved.
     * @param dy Direction of Y axis where currently moved.
     * @return Distance between from and to Vertexes.
     */
    public double jumpDistance(Vertex from, Vertex to, int dx, int dy) {

        int differenceX = from.getX() - to.getX();
        int differenceY = from.getY() - to.getY();

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
    
    /**
     * Creates path between start and end Vertex, uses parent jump points to create path between jump points.
     * 
     * @param start Start Vertex that is chosen.
     * @param end End Vertex that is chosen.
     * @return Array of the path Vertexes.
     */

    public Vertex[] printPath(Vertex start, Vertex end) {
        Vertex[] path = new Vertex[100000];
        path[0] = end;
        Vertex current = end;
        int index = 1;

        while (current.getX() != start.getX() && current.getY() != start.getY()) {
            Vertex previousJump = predecessor[current.getX()][current.getY()];
            int directionX = movementDirection(previousJump.getX(), current.getX());
            int directionY = movementDirection(previousJump.getY(), current.getY());

            while (true) {

                if (current.getY() == previousJump.getY() && current.getX() == previousJump.getX()) {
                    break;
                }
                current = new Vertex(current.getX() + directionX, current.getY() + directionY, 0);
                path[index] = current;
                index++;
            }
        }

        return path;
    }
    
    /**
     * Prints distance to end Vertex.
     * @param x
     * @param y
     * @return Distance to end Vertex.
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
    private double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
