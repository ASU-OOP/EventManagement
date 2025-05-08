module javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports UI;
    opens UI to javafx.graphics;
}