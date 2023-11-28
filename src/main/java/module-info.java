module com.example.nextpublicholiday {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nextpublicholiday to javafx.fxml;
    exports com.example.nextpublicholiday;
}