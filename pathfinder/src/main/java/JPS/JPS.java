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

    // Jump forward from each neighbour of a jump point
    public Vertex[] jump(Vertex vertex) {

        Vertex[] current = new Vertex[1];

        int x = vertex.getX();
        int y = vertex.getY();
        int parentX = predecessor[x][y].getX();
        int parentY = predecessor[x][y].getY();
        int directionX = 0;
        int directionY = 0;
        int endX = vertex.endVertex.getX();
        int endY = vertex.endVertex.getY();

        // Check if the vertex has a predecessor, if not its starting point and direction determined by end coordinates
        if (predecessor[x][y] == null) {
            directionX = (endX - x) / Math.max(Math.abs(endX - x), 1);
            directionY = (endY - y) / Math.max(Math.abs(endY - y), 1);
        } else {
            directionX = (x - parentX) / Math.max(Math.abs(x - parentX), 1);
            directionY = (y - parentY) / Math.max(Math.abs(y - parentY), 1);
        }

        if (vertex.getX() == endX && vertex.getY() == endY) {
            current[0] = new Vertex(x, y, 0);
            return current;
        }

        // Check for forced neighbour when moving diagonally, if found return current vertex as jump point
        if (directionX != 0 && directionY != 0) {
            if (directionX == 1 && directionY == 1 && map[x - 1][y + 1] == '.' && map[x - 1][y] != '.') {
                current[0] = new Vertex(x, y, 0);
                return current;
            }
            if (directionX == -1 && directionY == 1 && map[x + 1][y + 1] == '.' && map[x + 1][y] != '.') {
                current[0] = new Vertex(x, y, 0);
                return current;
            }
            if (directionX == 1 && directionY == -1 && map[x - 1][y - 1] == '.' && map[x - 1][y] != '.') {
                current[0] = new Vertex(x, y, 0);
                return current;
            }
            if (directionX == -1 && directionY == -1 && map[x + 1][y - 1] == '.' && map[x + 1][y] != '.') {
                current[0] = new Vertex(x, y, 0);
                return current;
            }
        }

        //Movement only along X-axis and return forced neighbours
        if (directionY == 0 && directionX != 0) {
            if (map[x + 1][y + 1] == '.' || map[x - 1][y + 1] == '.' && map[x][y + 1] != '.') {
                current[0] = new Vertex(x, y, 0);
                return current;
            }
            if (map[x + 1][y - 1] == '.' || map[x - 1][y - 1] == '.' && map[x][y - 1] != '.') {
                current[0] = new Vertex(x, y, 0);
                return current;
            }

            // Movement only along Y-axis and return forced neighbours    
        } else {
            if (map[x + 1][y + 1] == '.' || map[x + 1][y - 1] == '.' && map[x + 1][y] != '.') {
                current[0] = new Vertex(x, y, 0);
                return current;
            }
            if (map[x - 1][y - 1] == '.' || map[x - 1][y + 1] == '.' && map[x - 1][y] != '.') {
                current[0] = new Vertex(x, y, 0);
                return current;
            }
        }
        if (map[x+directionX][y+directionY] == '.') {
            Vertex jump = new Vertex(x + directionX, y + directionY, 0);
            jump(jump);
        } 

        return null;
    }

    public Vertex[] checkNeighbours(Vertex vertex) { // add predecessor information to neighbours
        int x = vertex.getX();
        int y = vertex.getY();

        int index = 0;

        Vertex[] neighbours = new Vertex[9];

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

        if (predecessor[x][y] != null) {
            int parentX = predecessor[x][y].getX();
            int parentY = predecessor[x][y].getY();
            int directionX = (x - parentX) / Math.max(Math.abs(x - parentX), 1);
            int directionY = (y - parentY) / Math.max(Math.abs(y - parentY), 1);

            if (directionX != 0 && directionY != 0) {
                if (directionX == 1 && directionY == 1) {
                    if (map[x + 1][y + 1] == '.') {
                        neighbours[0] = new Vertex(x + 1, y + 1, 0);
                    } else if (map[x][y + 1] == '.') {
                        neighbours[1] = new Vertex(x, y + 1, 0);
                    } else if (map[x + 1][y] == '.') {
                        neighbours[2] = new Vertex(x + 1, y, 0);
                    } else if (map[x - 1][y] != '.' && map[x - 1][y + 1] == '.') {
                        neighbours[3] = new Vertex(x - 1, y + 1, 0);
                    }
                    return neighbours;
                }
                if (directionX == -1 && directionY == -1) {
                    if (map[x - 1][y - 1] == '.') {
                        neighbours[0] = new Vertex(x - 1, y - 1, 0);
                    } else if (map[x][y - 1] == '.') {
                        neighbours[1] = new Vertex(x, y - 1, 0);
                    } else if (map[x - 1][y] == '.') {
                        neighbours[2] = new Vertex(x - 1, y, 0);
                    } else if (map[x + 1][y - 1] != '.' && map[x + 1][y - 1] == '.') {
                        neighbours[3] = new Vertex(x + 1, y - 1, 0);
                    }
                    return neighbours;
                }
                if (directionX == 1 && directionY == -1) {
                    if (map[x + 1][y - 1] == '.') {
                        neighbours[0] = new Vertex(x + 1, y - 1, 0);
                    } else if (map[x][y - 1] == '.') {
                        neighbours[1] = new Vertex(x, y - 1, 0);
                    } else if (map[x + 1][y] == '.') {
                        neighbours[2] = new Vertex(x + 1, y, 0);
                    } else if (map[x - 1][y] != '.' && map[x - 1][y - 1] == '.') {
                        neighbours[3] = new Vertex(x - 1, y - 1, 0);
                    }
                    return neighbours;
                }

                if (directionX == -1 && directionY == 1) {
                    if (map[x - 1][y + 1] == '.') {
                        neighbours[0] = new Vertex(x - 1, y + 1, 0);
                    } else if (map[x][y + 1] == '.') {
                        neighbours[1] = new Vertex(x, y + 1, 0);
                    } else if (map[x - 1][y] == '.') {
                        neighbours[2] = new Vertex(x - 1, y, 0);
                    } else if (map[x + 1][y] != '.' && map[x + 1][y + 1] == '.') {
                        neighbours[3] = new Vertex(x + 1, y + 1, 0);
                    }
                    return neighbours;
                }

            }
            if (directionX == 0) {
                if (map[x][y + 1] == '.' && directionY == 1) {
                    neighbours[0] = new Vertex(x, y + 1, 0);
                }
                if (map[x][y - 1] == '.' && directionY == -1) {
                    neighbours[1] = new Vertex(x, y - 1, 0);
                }
                if (map[x + 1][y] != '.' && map[x + 1][y + 1] == '.' && directionY == 1) {
                    neighbours[2] = new Vertex(x + 1, y + 1, 0);
                }
                if (map[x + 1][y] != '.' && map[x + 1][y - 1] == '.' && directionY == -1) {
                    neighbours[3] = new Vertex(x + 1, y - 1, 0);
                }
                if (map[x - 1][y] != '.' && map[x - 1][y + 1] == '.' && directionY == 1) {
                    neighbours[4] = new Vertex(x - 1, y + 1, 0);
                }
                if (map[x - 1][y] != '.' && map[x - 1][y - 1] == '.' && directionY == -1) {
                    neighbours[5] = new Vertex(x - 1, y - 1, 0);
                }
            }

            if (directionY == 0) {
                if (map[x + 1][y] == '.' && directionX == 1) {
                    neighbours[0] = new Vertex(x + 1, y, 0);
                }
                if (map[x - 1][y] == '.' && directionX == -1) {
                    neighbours[1] = new Vertex(x - 1, y, 0);
                }
                if (map[x][y + 1] != '.' && map[x + 1][y + 1] == '.' && directionX == 1) {
                    neighbours[2] = new Vertex(x + 1, y + 1, 0);
                }
                if (map[x][y + 1] != '.' && map[x - 1][y + 1] == '.' && directionX == -1) {
                    neighbours[3] = new Vertex(x - 1, y + 1, 0);
                }
                if (map[x][y - 1] != '.' && map[x + 1][y - 1] == '.' && directionX == 1) {
                    neighbours[4] = new Vertex(x + 1, y - 1, 0);
                }
                if (map[x][y - 1] != '.' && map[x - 1][y - 1] == '.' && directionX == -1) {
                    neighbours[5] = new Vertex(x - 1, y - 1, 0);
                }
            }

        }
        return neighbours;
    }

}
