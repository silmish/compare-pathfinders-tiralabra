/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import Dijkstra.Dijkstra;
import Dijkstra.Vertex;
import ImageHandler.FileHandler;
import ImageHandler.ImageHandler;
import UI.GUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        GUI.main(args);
        //ImageHandler.main(args);
        /*FileHandler handler = new FileHandler();
        Dijkstra algo = new Dijkstra();

        File map = new File("src/main/java/Images/Map1.map");

        handler.countRows(map);
        algo.map = handler.createMap(map);
        algo.distance = handler.initiateDistanceArray(map);
        algo.predecessor = handler.initiateParentCount();

        Vertex start = new Vertex(360, 54, 0);

        algo.findPath(start.getX(), start.getY());
        //algo.printPath(400, 250);
        algo.printDistance(344, 463);*/

    }

}
