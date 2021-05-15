/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStructures;

import ImageHandler.Vertex;

/**
 *Custom Minimum Heap
 * 
 * @author pate
 */
public class MinHeap {

    private Vertex[] minHeap;
    private int size;

    public MinHeap() {
        this.size = 0;
        minHeap = new Vertex[25];
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private boolean isLeaf(int pos) {
        if (rightChild(pos) >= size || leftChild(pos) >= size) {
            return true;
        }
        return false;
    }

    private void swap(int first, int second) {
        Vertex tmp;
        tmp = minHeap[first];
        minHeap[first] = minHeap[second];
        minHeap[second] = tmp;
    }

    private void minHeapiPrioritize(int pos) {

        if (!isLeaf(pos)) {

            if (minHeap[pos].compareTo(minHeap[rightChild(pos)]) == 1
                    || minHeap[pos].compareTo(minHeap[leftChild(pos)]) == 1) {

                if (minHeap[rightChild(pos)].compareTo(minHeap[leftChild(pos)]) == -1) {
                    swap(pos, rightChild(pos));
                    minHeapiPrioritize(rightChild(pos));
                } else {
                    swap(pos, leftChild(pos));
                    minHeapiPrioritize(leftChild(pos));
                }
            }
        }
    }

    public void add(Vertex vertex) {
        if (size == minHeap.length) {
            expandHeap();
        }
        minHeap[size] = vertex;
        int current = size;

        while (minHeap[current].compareTo(minHeap[parent(current)]) == -1) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    private void expandHeap() {
        int newSize = minHeap.length * 2;
        minHeap = createNew(minHeap, newSize);
    }

    private Vertex[] createNew(Vertex[] queue, int newSize) {
        Vertex[] vertexCopy = new Vertex[newSize];
        for (int i = 0; i < size; i++) {
            vertexCopy[i] = queue[i];
        }
        return vertexCopy;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Vertex poll() {
        Vertex next = minHeap[0];
        minHeap[0] = minHeap[size - 1];
        size--;
        minHeapiPrioritize(0);
        return next;
    }
}
