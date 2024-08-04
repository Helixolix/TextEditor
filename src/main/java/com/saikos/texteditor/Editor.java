package com.saikos.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

public class Editor extends Application {
    public static Pane root;
    public static TextArea textArea;
    public static AnchorPane anchorPane;

    @Override
    public void start(Stage stage) throws Exception {

        textArea = new TextArea();
        root = new Pane();
        anchorPane = new AnchorPane();
        AnchorPane.setTopAnchor(textArea, 0.0);
        Menu fileMenu = new Menu("File");
        MenuItem saveFileItem = new MenuItem("Save file");
        MenuItem openFileItem = new MenuItem("Open file");
        fileMenu.getItems().addAll(saveFileItem, openFileItem);

        Menu editMenu = new Menu("Edit");
        MenuItem setFontItem = new MenuItem("Set Font");
        editMenu.getItems().addAll(setFontItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu);

        saveFileItem.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Editor", "*.txt"));
            fileChooser.getInitialDirectory();
            File file = fileChooser.showSaveDialog(stage);


            try {
                file.createNewFile();
                file.mkdirs();

                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(textArea.getText());
                fileWriter.close();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });






        Scene scene = new Scene(root, 800, 600);
        textArea.setPrefSize(800, 600);
        textArea.setLayoutY(27);
        root.getChildren().add(textArea);
        anchorPane.getChildren().add(menuBar);
        root.getChildren().add(anchorPane);

        stage.setResizable(false);
        stage.setTitle("Text Editor");
        stage.setScene(scene);
        stage.show();
    }
}
