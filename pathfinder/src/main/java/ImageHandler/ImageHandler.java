/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageHandler;

import Dijkstra.Dijkstra;
import Dijkstra.Vertex;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImageHandler {

    public WritableImage drawableImage;
    int height, width;
    Color black, red;
    Dijkstra algo = new Dijkstra();

    public void ImageHandler() {

    }

    public void setWriteableImage(Image image) throws InterruptedException {
        black = Color.BLACK;
        red = Color.RED;
        
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

    public void drawPath(int x, int y, Vertex[] vertex) {
        
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

        for (Vertex draw : path) {
            if (draw == null) {
                break;
            }
            writer.setColor(draw.getY(), draw.getX(), red);
        }

    }

    public Color readPixelColor(int x, int y) {

        PixelReader reader = drawableImage.getPixelReader();
        Color color = reader.getColor(x, y);

        return color;
    }

}
