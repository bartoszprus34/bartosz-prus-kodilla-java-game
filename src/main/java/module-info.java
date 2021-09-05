module com.kodilla.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.kodilla.game to javafx.fxml;
    exports com.kodilla.game;
}