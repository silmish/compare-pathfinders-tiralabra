/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

import java.util.PriorityQueue;

/**
 *
 * @author pate
 */
public class Dijkstra {

    public char[][] map; // <-- käyttöliittymään
    public double[][] distance; // loput metodiin
    public Vertex[][] predecessor;
    public boolean[] visited; // boolean taulukko, vaihda tähän
    PriorityQueue<Vertex> priorityQueue; // Vaihda selkeempi nimi

    public void findPath(int x, int y) {
   
        priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new Vertex(x, y, 0));
        distance[x][y] = 0;
        predecessor[x][y] = null;

        while (!priorityQueue.isEmpty()) {

            Vertex currentVertex = priorityQueue.poll();
            //Lisää visited checki, muuten continue.
            checkNeighbours(currentVertex);

        }

    }

    public void checkNeighbours(Vertex vertex) { // Lisää parametrit, mitä tarvitsee

        //Lisää X ja Y tähän, refactoroi
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0 || vertex.getX() + i < 0 || vertex.getY() + j < 0 || vertex.getX() + i > map.length - 1) {
                    continue;
                }
                if (map[vertex.getX() + i][vertex.getY() + j] == '.') {
                    double weight = 1;
                    if (i != 0 && j != 0) {
                        weight = Math.sqrt(2);
                    }

                    if (distance[vertex.getX() + i][vertex.getY() + j] > distance[vertex.getX()][vertex.getY()] + weight) {
                        distance[vertex.getX() + i][vertex.getY() + j] = distance[vertex.getX()][vertex.getY()] + weight;

                        Vertex adjacentVertex = new Vertex(vertex.getX() + i, vertex.getY() + j, distance[vertex.getX() + i][vertex.getY() + j]);
                        predecessor[vertex.getX() + i][vertex.getY() + j] = new Vertex(vertex.getX(), vertex.getY(), 0);
                        priorityQueue.add(adjacentVertex);

                    }
                }

            }

        }

    }

    public void printDistance(int x, int y) {
        System.out.println("Distance from source to target is: " + distance[x][y]);
    }

    public void printPath(int x, int y) {

        while(true) {
            //Piirrä kuvaan tässä
            System.out.println(predecessor[predecessor[x][y].getX()][predecessor[x][y].getY()]);
            Vertex current = predecessor[x][y];
            if(current == null){
                System.out.println("Found path");
                break;
            }
            x=current.getX();
            y=current.getY();
                    
        }
    }

}