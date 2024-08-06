package com.saikos.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Editor extends Application {
    //variables
    public static Pane root;
    public static TextArea textArea;
    public static AnchorPane anchorPane;
    public static Text text;
    public static Label countSymbol;

    @Override
    public void start(Stage stage){
        //Object
        countSymbol = new Label();

        text = new Text();
        textArea = new TextArea();

        root = new Pane();
        anchorPane = new AnchorPane();

        Scene scene = new Scene(root, 830, 650);

        Menu editMenu = new Menu("Edit");
        MenuItem setThemeBlack = new MenuItem("Set theme Black");
        MenuItem setThemeWhite = new MenuItem("Set theme White");
        MenuItem setFontItem = new MenuItem("Set font Book Antiqua");

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem saveFileItem = new MenuItem("Save file");
        MenuItem openFileItem = new MenuItem("Open file");

        //Open file
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

        //Save file
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

        setFontItem.setOnAction(actionEvent -> {
            textArea.setFont(Font.font("Book Antiqua"));
            text.setFont(Font.font("Book Antiqua"));
        });

        setThemeBlack.setOnAction(actionEvent -> {
            root.setStyle("-fx-background-color: grey;");
            setThemeBlack.setStyle("-fx-background-color: darkgrey;");
            setThemeWhite.setStyle("-fx-background-color: darkgrey;");
            editMenu.setStyle("-fx-background-color: darkgrey;");
            fileMenu.setStyle("-fx-background-color: darkgrey;");
            saveFileItem.setStyle("-fx-background-color: darkgrey;");
            openFileItem.setStyle("-fx-background-color: darkgrey;");
        });

        setThemeWhite.setOnAction(actionEvent -> {
            root.setStyle("-fx-background-color: white;");
            setThemeBlack.setStyle("-fx-background-color: white;");
            setThemeWhite.setStyle("-fx-background-color: white;");
            editMenu.setStyle("-fx-background-color: white;");
            fileMenu.setStyle("-fx-background-color: white;");
            saveFileItem.setStyle("-fx-background-color: white;");
            openFileItem.setStyle("-fx-background-color: white;");
        });

        textArea.setOnKeyTyped(actionEvent -> {
            text.setText(textArea.getText());
        });

        text.setX(710);
        text.setY(40);

        textArea.setPrefSize(700, 600);
        textArea.setLayoutY(27);

        //init
        anchorPane.getChildren().add(menuBar);
        root.getChildren().addAll(textArea, text, countSymbol, anchorPane);
        fileMenu.getItems().addAll(saveFileItem, openFileItem);
        editMenu.getItems().addAll(setThemeWhite, setThemeBlack, setFontItem);
        menuBar.getMenus().addAll(fileMenu, editMenu);

        stage.getIcons().add(new Image(String.valueOf(this.getClass().getResource("TexteditorIcon.png"))));
        stage.setResizable(false);
        stage.setTitle("Text Editor");
        stage.setScene(scene);
        stage.show();
    }
}