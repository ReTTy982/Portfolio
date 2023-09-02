package edu.pwr;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainWindow extends Application {
    ArrayList<Integer> list = new ArrayList<>();
    Thread thread;
    GridController gridController;
    ScriptController scriptController;
    public static void main(String[] args) {
        launch(args);
    }
    public static List<Integer> generateRandomNumbers(int count, int min, int max) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            randomNumbers.add(randomNumber);
        }

        return randomNumbers;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Font font = Font.font("System Regular",12);



        BorderPane pane = new BorderPane();
        GridPane gridPane = new GridPane();
        FlowPane flowPane = new FlowPane();
        Scene scene = new Scene(pane, 700, 700);

        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        pane.setStyle("-fx-font-family: 'serif'; -fx-font-size: 16;");
        Button newButton = new Button("Nowa Gra");
        Button loadScriptButton = new Button("Zaladuj Skrytp");
        Button stopButton = new Button("Stop");



        pane.setCenter(gridPane);
        pane.setBottom(flowPane);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(10);
        flowPane.getChildren().addAll(newButton,loadScriptButton,stopButton);
        gridPane.setAlignment(Pos.CENTER);

//        Thread thread =  gridController.threadTest(scriptController);
//        thread.start();
//        thread.interrupt();

        // Buttons actions

        newButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridController = new GridController();
                list.clear();
                list.addAll(generateRandomNumbers(140,0,399));
                gridController.createGrid(gridPane,list);
                thread = gridController.threadTest(scriptController);
                thread.start();
            }
        });

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                thread.interrupt();
            }
        });



        loadScriptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Wybierz skrypt do załadowania");
                File userDesktopPath = new File(System.getProperty("user.home") + File.separator + "Desktop");
                fileChooser.setInitialDirectory(userDesktopPath);
                Path scriptPath = fileChooser.showOpenDialog(scene.getWindow()).toPath();



                scriptController = new ScriptController();
                //scriptController.loadScript(Path.of("/Users/paul/Desktop/studia/java/paupac307_javatz_2023/lab12/src/main/java/edu/pwr/skrypt.js"));
                scriptController.loadScript(scriptPath);

            }
        });



        primaryStage.setTitle("Grid of Squares");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);



        primaryStage.show();
    }
}