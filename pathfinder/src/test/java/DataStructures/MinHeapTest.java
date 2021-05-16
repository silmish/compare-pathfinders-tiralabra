/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import ImageHandler.Vertex;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pate
 */
public class MinHeapTest {
    
    public MinHeapTest() {
    }

    /**
     * Test of add method, of class MinHeap.
     */
    @Test
    public void testAdd() {
        Vertex vertex = new Vertex(0, 0, 0);
        MinHeap instance = new MinHeap();
        instance.add(vertex);
        assertEquals(1, instance.getSize());
    }

    /**
     * Test of isEmpty method, of class MinHeap.
     */
    @Test
    public void testIsEmpty() {
        MinHeap instance = new MinHeap();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of poll method, of class MinHeap.
     */
    @Test
    public void testPoll() {
        MinHeap instance = new MinHeap();
        Vertex expResult = new Vertex(0, 0, 0);
        instance.add(expResult);
        Vertex result = instance.poll();
        assertEquals(expResult.compareTo(result), 0);
    }
    
    @Test
    public void testPollMinDistanceFirst() {
        MinHeap instance = new MinHeap();
        Vertex first = new Vertex(0, 0, 3);
        Vertex second = new Vertex(0, 0, 4);
        Vertex third = new Vertex(0, 0, 6);
        Vertex fourth = new Vertex(0, 0, 1);
        
        instance.add(first);
        instance.add(second);
        instance.add(third);
        instance.add(fourth);
        
        Vertex poll = instance.poll();
        
        assertEquals(1, poll.getDistance(),0.0);
        
    }
}
