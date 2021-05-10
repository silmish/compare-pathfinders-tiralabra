/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import Dijkstra.Dijkstra;
import JPS.Vertex;
import ImageHandler.FileHandler;
import ImageHandler.ImageHandler;
import UI.GUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import JPS.JPS;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        GUI.main(args);

        /*File map = new File("src/main/java/Images/Map1.map");
        JPS test = new JPS();

        FileHandler handler = new FileHandler();

        handler.countRows(map);

        test.map = handler.createMap(map);
        test.distance = handler.initiateDistanceArray();
        //test.predecessor = handler.initiateParentCount();

        //Vertex start = new Vertex(308, 130, 0);
        //Vertex end = new Vertex(157,198,0);
        
        //System.out.println(end);
        //test.predecessor[start.getX()][start.getY()] = null;
        //System.out.println(Arrays.toString(handler.createMap(map)));
        //System.out.println(Arrays.toString(test.checkNeighbours(start)));
        //test.searchPath(161, 103, end);*/

    }

}
