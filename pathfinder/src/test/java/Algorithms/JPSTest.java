/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import ImageHandler.FileHandler;
import ImageHandler.Vertex;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pate
 */
public class JPSTest {

    public JPSTest() {
    }

    /**
     * Test of findPath method, of class JPS.
     */
    @Test
    public void testFindPath() throws IOException {
        FileHandler handler = new FileHandler();
        JPS jpsAlgo = new JPS();
        File map = new File("src/main/java/Images/Map4.map");
        Vertex startVertex = new Vertex(429, 113, 0);
        Vertex endVertex = new Vertex(295, 428, 0);
        handler.countRows(map);
        jpsAlgo.map = handler.createMap(map);
        jpsAlgo.distance = handler.initiateDistanceArray();
        jpsAlgo.predecessor = handler.initiateParentCount();

        String expResult = "Path found";
        String result = jpsAlgo.findPath(startVertex, endVertex);
        assertEquals(expResult, result);
    }

    /**
     * Test of pruneNeighbours method, of class JPS.
     */
    @Test
    public void testPruneNeighboursCountWithNoParent() throws IOException {
        FileHandler handler = new FileHandler();
        JPS jpsAlgo = new JPS();
        File map = new File("src/main/java/Images/Map4.map");
        Vertex startVertex = new Vertex(429, 113, 0);
        handler.countRows(map);
        jpsAlgo.map = handler.createMap(map);
        jpsAlgo.distance = handler.initiateDistanceArray();
        jpsAlgo.predecessor = handler.initiateParentCount();
        Vertex[] neighbours = jpsAlgo.pruneNeighbours(startVertex);
        int count = 0;
        for (int i = 0; i < neighbours.length; i++) {
            if (neighbours[i] != null) {
                count++;
            }
        }
        assertEquals(count, 8);
    }

    /**
     * Test of movementDirection method, of class JPS.
     */
    @Test
    public void testMovementDirection() {
        int to = 100;
        int from = 99;
        JPS instance = new JPS();
        int expResult = 1;
        int result = instance.movementDirection(to, from);
        assertEquals(expResult, result);
    }

    /**
     * Test of distanceToEnd method, of class JPS.
     */
    @Test
    public void testDistanceToEnd() {
        Vertex startVertex = new Vertex(429, 113, 0);
        Vertex endVertex = new Vertex(295, 428, 0);
        JPS instance = new JPS();
        double expResult = 342.32;
        double result = instance.distanceToEnd(startVertex, endVertex);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of jumpDistance method, of class JPS.
     */
    @Test
    public void testJumpDistance() {
        Vertex from = new Vertex(255, 276, 0);
        Vertex to = new Vertex(288, 315, 0);
        int dx = 1;
        int dy = 1;
        JPS instance = new JPS();
        double expResult = 46.66904755831214;
        double result = instance.jumpDistance(from, to, dx, dy);
        assertEquals(expResult, result, 0.0);
    }

}
