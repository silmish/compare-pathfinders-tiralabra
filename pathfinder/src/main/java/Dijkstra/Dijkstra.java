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

    public void findPath(Vertex startVertex) {
        
        startVertex.setDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(startVertex);
        startVertex.setVisited(true);

        while (!priorityQueue.isEmpty()) {

            Vertex currentVertex = priorityQueue.poll();

            for (int i = 0; i < currentVertex.adjacent.length; i++) {
                System.out.println(currentVertex.adjacent[i]);
                if (currentVertex.adjacent[i] != null && currentVertex.adjacent[i].isVisited() == false) {
                    Vertex v = currentVertex.adjacent[i];
                    int newDistance = (int) currentVertex.getDistance() + v.getCost();
                    if (newDistance < v.getDistance()) {
                        priorityQueue.remove(v);
                        v.setDistance(newDistance);
                        v.setPredecessor(currentVertex);
                        priorityQueue.add(v);
                        v.setVisited(true);
                        System.out.println(v.adjacent[i]);
                    }
                }

            }

        }

    }
}
