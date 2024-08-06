package com.saikos.texteditor;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.effect.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.Optional;

public class Editor extends Application {
    public static Pane root;
    public static TextArea textArea;
    public static AnchorPane anchorPane;
    public static Text text;
    public static Label countSymbol;

    @Override
    public void start(Stage stage) throws Exception {
        countSymbol = new Label();

        text = new Text();
        textArea = new TextArea();

        root = new Pane();
        anchorPane = new AnchorPane();

        Menu fileMenu = new Menu("File");
        MenuItem saveFileItem = new MenuItem("Save file");
        MenuItem openFileItem = new MenuItem("Open file");

        openFileItem.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Editor", "*.txt"));
            File file = fileChooser.showOpenDialog(stage);
            try {
                FileReader files = new FileReader(file.getAbsoluteFile());
                int c;
                textArea.clear();
                while( (c = files.read()) != -1) {
                    textArea.setText(textArea.getText() + (char) c);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        fileMenu.getItems().addAll(saveFileItem, openFileItem);

        Menu editMenu = new Menu("Edit");
        MenuItem setTheme = new MenuItem("Set theme");

        setTheme.setOnAction(actionEvent -> {

        });

        editMenu.getItems().add(setTheme);

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

        textArea.setOnKeyTyped(actionEvent -> {
            text.setText(textArea.getText());
        });


        Scene scene = new Scene(root, 830, 650);
        text.setX(710);
        text.setY(40);

        textArea.setPrefSize(700, 600);
        textArea.setLayoutY(27);

        anchorPane.getChildren().add(menuBar);
        root.getChildren().add(textArea);
        root.getChildren().add(text);
        root.getChildren().add(countSymbol);
        root.getChildren().add(anchorPane);

        stage.setResizable(false);
        stage.setTitle("Text Editor");
        stage.setScene(scene);
        stage.show();
    }
}
