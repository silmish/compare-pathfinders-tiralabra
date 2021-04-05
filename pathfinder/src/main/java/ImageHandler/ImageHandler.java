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
import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ImageHandler extends Application {

    WritableImage drawableImage;
    WritableImage test;
    int height, width;
    Color black, path;
    int[][] adjecensyMatrix = new int[496][496];
    int index = 0;
    Vertex start;
    Vertex end;

    public void ImageHandler() {

    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        start = new Vertex(100, 170, 0);
        end = new Vertex(248, 248, 0);

        //creating the image object
        FileInputStream stream = new FileInputStream("src/main/java/Images/test5.png");
        Image picture = new Image(stream);
        //Creating wanted colors for pathing
        black = Color.rgb(0, 0, 0);
        path = Color.rgb(255, 0, 0);
        //Creating the image view
        ImageView imageView = new ImageView();
        //initiate(picture);
        setWriteableImage(picture);
        //drawPath(drawableImage);
        //Setting image to the image view
        imageView.setImage(drawableImage);

        HBox hbox = new HBox(imageView);

        Scene scene = new Scene(hbox, 496, 496);
        stage.setTitle("Displaying Image");
        stage.setScene(scene);
        stage.show();

    }

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

        for (int i = 0; i < height - 1; i++) {
            for (int j = 0; j < width - 1; j++) {
                if (!reader.getColor(i, j).equals(black)) {
                    generateVertex(i, j);
                }

            }

        }

        writer.setColor(100, 170, path);
        writer.setColor(100, 171, path);
        writer.setColor(101, 170, path);
        writer.setColor(99, 170, path);
        writer.setColor(100, 169, path);
        writer.setColor(248, 248, path);
        writer.setColor(248, 249, path);
        writer.setColor(249, 248, path);
        writer.setColor(247, 248, path);
        writer.setColor(248, 247, path);
        Dijkstra algo = new Dijkstra();
        

    }

    public void drawPath(WritableImage image) {

    }

    public Color readPixelColor(int x, int y) {

        PixelReader reader = drawableImage.getPixelReader();
        Color color = reader.getColor(x, y);

        return color;
    }

    public int generateCost(int x, int y) {

        int cost = (int) Math.abs((end.getX() - x) + (end.getY() - y));

        return cost;

    }

    public void generateVertex(int x, int y) {

        int cost = generateCost(x, y);

        Vertex vertex = new Vertex(x, y, cost);

        PixelReader reader = drawableImage.getPixelReader();

        if (!reader.getColor(x - 1, y).equals(black)) {
            Vertex adjacent = new Vertex(x - 1, y, cost);
            vertex.adjacent[0] = adjacent;
        } else {
            vertex.adjacent[0] = null;
        }

        if (!reader.getColor(x + 1, y).equals(black)) {
            Vertex adjacent = new Vertex(x + 1, y, cost);
            vertex.adjacent[1] = adjacent;
        } else {
            vertex.adjacent[1] = null;
        }

        if (!reader.getColor(x + 1, y + 1).equals(black)) {
            Vertex adjacent = new Vertex(x + 1, y + 1, cost);
            vertex.adjacent[2] = adjacent;
        } else {
            vertex.adjacent[2] = null;
        }

        if (!reader.getColor(x, y + 1).equals(black)) {
            Vertex adjacent = new Vertex(x, y + 1, cost);
            vertex.adjacent[3] = adjacent;
        } else {
            vertex.adjacent[3] = null;
        }

        if (!reader.getColor(x, y - 1).equals(black)) {
            Vertex adjacent = new Vertex(x, y - 1, cost);
            vertex.adjacent[4] = adjacent;
        } else {
            vertex.adjacent[4] = null;
        }

        if (!reader.getColor(x - 1, y - 1).equals(black)) {
            Vertex adjacent = new Vertex(x - 1, y - 1, cost);
            vertex.adjacent[5] = adjacent;
        } else {
            vertex.adjacent[5] = null;
        }

        if (!reader.getColor(x - 1, y + 1).equals(black)) {
            Vertex adjacent = new Vertex(x - 1, y + 1, cost);
            vertex.adjacent[6] = adjacent;
        } else {
            vertex.adjacent[6] = null;
        }

        if (!reader.getColor(x + 1, y - 1).equals(black)) {
            Vertex adjacent = new Vertex(x + 1, y - 1, cost);
            vertex.adjacent[7] = adjacent;
        } else {
            vertex.adjacent[7] = null;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
