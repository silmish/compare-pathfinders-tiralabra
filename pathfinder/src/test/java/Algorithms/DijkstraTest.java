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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pate
 */
public class DijkstraTest {

    public DijkstraTest() {
    }

    /**
     * Test of findPath method, of class Dijkstra.
     */
    @Test
    public void testFindPath() throws IOException {
        File map = new File("src/main/java/Images/Map4.map");
        FileHandler handler = new FileHandler();
        Dijkstra dijkstraAlgo = new Dijkstra();
        handler.countRows(map);
        dijkstraAlgo.map = handler.createMap(map);
        dijkstraAlgo.distance = handler.initiateDistanceArray();
        dijkstraAlgo.predecessor = handler.initiateParentCount();
        int x = 429;
        int y = 113;
        dijkstraAlgo.findPath(x, y);
        assertTrue(dijkstraAlgo.distance[295][428] < Double.MAX_VALUE);
    }

    /**
     * Test of printDistance method, of class Dijkstra.
     */
    @Test
    public void testPrintDistance() throws IOException {
        File map = new File("src/main/java/Images/Map4.map");
        FileHandler handler = new FileHandler();
        Dijkstra dijkstraAlgo = new Dijkstra();
        handler.countRows(map);
        dijkstraAlgo.map = handler.createMap(map);
        dijkstraAlgo.distance = handler.initiateDistanceArray();
        dijkstraAlgo.predecessor = handler.initiateParentCount();
        int x = 429;
        int y = 113;
        dijkstraAlgo.findPath(x, y);
        double expResult = 556.1;
        double result = dijkstraAlgo.printDistance(295, 428);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of round method, of class Dijkstra.
     */
}
