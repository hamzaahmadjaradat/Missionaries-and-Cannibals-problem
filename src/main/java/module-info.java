module com.example.artificial_intelligence_project1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.artificial_intelligence_project1 to javafx.fxml;
    exports com.example.artificial_intelligence_project1;
}