package com.saikos.texteditor;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Editor extends Application {
    public static Pane root;
    public static TextArea textArea;
    public static AnchorPane anchorPane;

    @Override
    public void start(Stage stage) throws Exception {
        anchorPane = new AnchorPane();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu);



        textArea = new TextArea();
        root = new Pane();
        Scene scene = new Scene(root, 800, 600);

        textArea.setPrefSize(800, 600);
        textArea.setLayoutY(27);

        root.getChildren().add(textArea);
        anchorPane.getChildren().add(menuBar);
        root.getChildren().add(anchorPane);

        stage.setTitle("Text Editor");
        stage.setScene(scene);
        stage.show();
    }
}
