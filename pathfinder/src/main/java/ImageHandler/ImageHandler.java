/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageHandler;

import Dijkstra.Dijkstra;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Class that handles image reading and updating
 *
 * @author pate
 */
public class ImageHandler {

    public WritableImage drawableImage;
    int height, width;
    Dijkstra algo = new Dijkstra();

    public void ImageHandler() {

    }

    /**
     * Generates an writeable image of the image file that is read.
     *
     * @param image image file that is read
     * @throws InterruptedException
     */
    public void setWriteableImage(Image image) throws InterruptedException {

        height = (int) image.getHeight();
        width = (int) image.getWidth();

        drawableImage = new WritableImage(width, height);

        PixelReader reader = image.getPixelReader();

        PixelWriter writer = drawableImage.getPixelWriter();

        int yCoordinates = 0;
        while (yCoordinates < height) {
            int xCoordinates = 0;
            while (xCoordinates < width) {

                Color color = reader.getColor(xCoordinates, yCoordinates);

                writer.setColor(xCoordinates, yCoordinates, color);

                xCoordinates++;
            }

            yCoordinates++;
        }

    }

    /**
     * Draws the shortest path between start and end Vertex.
     * 
     *
     * @param vertex Array of vertexes between start and end.
     * @param algo Parameter to define which algorithm is used and path color is
     * chosen by it.
     */
    public void drawPath(Vertex[] vertex, String algo) {

        Vertex[] path = vertex;

        PixelReader reader = drawableImage.getPixelReader();

        PixelWriter writer = drawableImage.getPixelWriter();

        int yCoordinates = 0;
        while (yCoordinates < height) {
            int xCoordinates = 0;
            while (xCoordinates < width) {

                Color color = reader.getColor(xCoordinates, yCoordinates);

                writer.setColor(xCoordinates, yCoordinates, color);

                xCoordinates++;
            }

            yCoordinates++;
        }

        Color pathColor = Color.BLUE;

        if ("dijkstra".equals(algo)) {
            pathColor = Color.RED;
        }

        for (Vertex draw : path) {
            if (draw == null) {
                break;
            }
            writer.setColor(draw.getY(), draw.getX(), pathColor);
        }

    }

}
