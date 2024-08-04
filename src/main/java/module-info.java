module com.saikos.texteditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.saikos.texteditor to javafx.fxml;
    exports com.saikos.texteditor;
}