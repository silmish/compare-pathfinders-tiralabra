/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Dijkstra.Dijkstra;
import ImageHandler.Vertex;
import ImageHandler.FileHandler;
import ImageHandler.ImageHandler;
import JPS.JPS;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author pate
 */
public class GUI extends Application {

    Vertex vertex = new Vertex(0, 0, 0);
    Dijkstra dijkstraAlgo = new Dijkstra();
    JPS jpsAlgo = new JPS();
    FileHandler handler = new FileHandler();
    ImageHandler imageHandler = new ImageHandler();
    Image picture;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        BorderPane border = new BorderPane();

        FileInputStream stream = new FileInputStream("src/main/java/Images/Map4.png");

        picture = new Image(stream, 512, 512, false, false);

        imageHandler.setWriteableImage(picture);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(512);
        imageView.setFitWidth(512);
        imageView.setImage(imageHandler.drawableImage);
        imageView.setPreserveRatio(true);
        HBox hbox = new HBox(imageView);
        hbox.setPadding(new Insets(20));

        //###########################
        //Gridpane
        //###########################
        CheckBox dijkstra = new CheckBox("Dijkstra");
        CheckBox jump = new CheckBox("JPS");

        TextField startX = new TextField();
        startX.setMaxWidth(60);

        TextField startY = new TextField();
        startY.setMaxWidth(60);

        TextField endX = new TextField();
        endX.setMaxWidth(60);

        TextField endY = new TextField();
        endY.setMaxWidth(60);

        TextField distance = new TextField();
        distance.setMaxWidth(60);

        TextField time = new TextField();
        time.setMaxWidth(60);

        Label startXcord = new Label("Start X");
        Label startYcord = new Label("Start Y");
        Label endXcord = new Label("End X");
        Label endYcord = new Label("End Y");
        Label distanceTo = new Label("Distance");
        Label executeTime = new Label("Time");

        CheckBox startBox = new CheckBox("Start coordinates");
        CheckBox endBox = new CheckBox("End coordinates");

        Button start = new Button("Start");
        Button clear = new Button("clear");
        start.setMaxSize(100, 50);
        clear.setMaxSize(100, 50);

        GridPane grid = new GridPane();
        grid.getColumnConstraints().add(new ColumnConstraints(100));

        grid.add(dijkstra, 0, 2, 2, 2);
        grid.add(jump, 0, 4, 4, 4);

        grid.add(startBox, 0, 7, 7, 7);

        grid.add(startXcord, 0, 9, 9, 9);
        grid.add(startX, 1, 9, 9, 9);

        grid.add(startYcord, 0, 11, 11, 11);
        grid.add(startY, 1, 11, 11, 11);

        grid.add(endBox, 0, 13, 13, 13);

        grid.add(endXcord, 0, 15, 15, 15);
        grid.add(endX, 1, 15, 15, 15);

        grid.add(endYcord, 0, 17, 17, 17);
        grid.add(endY, 1, 17, 17, 17);

        grid.add(distanceTo, 0, 20, 20, 20);
        grid.add(distance, 1, 20, 20, 20);

        grid.add(executeTime, 0, 22, 22, 22);
        grid.add(time, 1, 22, 22, 22);

        grid.add(start, 0, 27, 27, 27);
        grid.add(clear, 5, 27, 27, 27);

        grid.setHgap(10);
        grid.setVgap(10);

        border.setCenter(grid);
        border.setLeft(hbox);

        //#######################################################
        //Mouse click events
        //#######################################################
        imageView.setOnMouseClicked(e -> {
            if (startBox.isSelected()) {
                startX.setText(String.valueOf(e.getX()));
                startY.setText(String.valueOf(e.getY()));

                vertex.setStartVertex((int) e.getY(), (int) e.getX());

            }

            if (endBox.isSelected()) {
                endX.setText(String.valueOf(e.getX()));
                endY.setText(String.valueOf(e.getY()));

                vertex.setEndVertex((int) e.getY(), (int) e.getX());

            }

        });

        startBox.setOnMouseClicked(e -> {
            if (startBox.isSelected()) {
                endBox.setSelected(false);
            }
        });

        endBox.setOnMouseClicked(e -> {
            if (endBox.isSelected()) {
                startBox.setSelected(false);
            }
        });

        dijkstra.setOnMouseClicked(e -> {
            if (dijkstra.isSelected()) {
                jump.setSelected(false);
            }
        });
        jump.setOnMouseClicked(e -> {
            if (jump.isSelected()) {
                dijkstra.setSelected(false);
            }
        });

        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (dijkstra.isSelected()) {
                    File map = new File("src/main/java/Images/Map4.map");

                    try {
                        handler.countRows(map);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        dijkstraAlgo.map = handler.createMap(map);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        dijkstraAlgo.distance = handler.initiateDistanceArray();
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dijkstraAlgo.predecessor = handler.initiateParentCount();
                    long start = System.nanoTime();
                    dijkstraAlgo.findPath(vertex.startVertex.getX(), vertex.startVertex.getY());
                    int runTime = (int) (System.nanoTime() - start) / 1000000;
                    time.setText(String.valueOf(runTime) + " ms");
                    distance.setText(String.valueOf(dijkstraAlgo.printDistance(vertex.endVertex.getX(), vertex.endVertex.getY())));
                    imageHandler.drawPath(dijkstraAlgo.printPath(vertex.endVertex.getX(), vertex.endVertex.getY()), "dijkstra");
                    imageView.setImage(imageHandler.drawableImage);
                }
                if (jump.isSelected()) {
                    File map = new File("src/main/java/Images/Map4.map");
                    try {
                        handler.countRows(map);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        jpsAlgo.map = handler.createMap(map);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        jpsAlgo.distance = handler.initiateDistanceArray();
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jpsAlgo.predecessor = handler.initiateParentCount();
                    long start = System.nanoTime();
                    jpsAlgo.searchPath(vertex.startVertex, vertex.endVertex);
                    int runTime = (int) (System.nanoTime() - start) / 1000000;
                    time.setText(String.valueOf(runTime) + " ms");
                    distance.setText(String.valueOf(jpsAlgo.printDistance(vertex.endVertex.getX(), vertex.endVertex.getY())));
                    imageHandler.drawPath(jpsAlgo.printPath(vertex.startVertex, vertex.endVertex), "JPS");
                    imageView.setImage(imageHandler.drawableImage);
                }
            }
        });

        clear.setOnMouseClicked(e -> {
            dijkstra.setSelected(false);
            jump.setSelected(false);
            startX.clear();
            startY.clear();
            endX.clear();
            endY.clear();
            distance.clear();
            startBox.setSelected(false);
            endBox.setSelected(false);
            time.clear();
            try {
                imageHandler.setWriteableImage(picture);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            imageView.setImage(picture);

        });

        Scene scene = new Scene(border, 960, 560, Color.DARKGREY);
        stage.setTitle("Displaying Image");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
