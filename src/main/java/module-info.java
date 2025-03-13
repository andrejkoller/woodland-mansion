module com.text_adventure.woodland_mansion {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens com.text_adventure.woodland_mansion to javafx.fxml;
    exports com.text_adventure.woodland_mansion;
}
