module javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports Views;
    opens Views to javafx.graphics;

    exports Application;
    opens Application to javafx.graphics;
}