module javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports Views.UI;
    opens Views.UI to javafx.graphics;
    exports Views;
    opens Views to javafx.graphics;
    exports;
    opens to
}