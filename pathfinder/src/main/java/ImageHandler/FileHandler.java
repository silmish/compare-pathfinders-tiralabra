/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

/**
 * Reads ASCII files and generates Map data for algorithms
 *
 * @author pate
 */
public class FileHandler {

    public char[][] map;
    public double[][] distance;
    public int row;
    public int column;

    public FileHandler() {

    }

    /**
     * Reads the measurements of the file and gives the Map array pixel amount.
     *
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void countRows(File file) throws FileNotFoundException, IOException {

        try (LineNumberReader reader = new LineNumberReader(new FileReader(file))) {
            int line = 0;
            while (reader.readLine() != null) {
                line = reader.getLineNumber();
                column = reader.readLine().length();
            }
            reader.close();
            row = line + 1;

        }
        map = new char[row + 1][column + 1];
    }
    
    /**
     * Reads the lines and rows of ACII file and generates pixel information
     * @param file
     * @return Array pixel information
     * @throws FileNotFoundException
     * @throws IOException 
     */

    public char[][] createMap(File file) throws FileNotFoundException, IOException {

        char[][] graph = new char[row + 1][column + 1];

        Scanner line = new Scanner(new BufferedReader(new FileReader(file)));

        while (line.hasNextLine()) {
            for (int i = 0; i < row; i++) {

                char[] nextLine = line.nextLine().toCharArray();
                for (int j = 0; j < column; j++) {
                    graph[i][j] = String.copyValueOf(nextLine).charAt(j);
                }
            }

        }

        return graph;
    }
    
    /**
     * Sets up distance array
     * @return Array dimensions
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public double[][] initiateDistanceArray() throws FileNotFoundException, IOException {

        double[][] graph = new double[row + 1][column + 1];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                graph[i][j] = Double.MAX_VALUE;
            }
        }

        return graph;
    }
    
    /**
     * Generates predecessor array dimensions
     * @return array dimensions
     */

    public Vertex[][] initiateParentCount() {
        Vertex[][] graph = new Vertex[row + 1][column + 1];

        return graph;
    }
}
