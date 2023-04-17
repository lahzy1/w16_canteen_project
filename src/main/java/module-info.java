module com.example.w16_canteen_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.w16_canteen_project to javafx.fxml;
    exports com.example.w16_canteen_project;
    exports Model;
}