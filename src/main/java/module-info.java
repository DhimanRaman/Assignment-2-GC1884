module com.example.nextpublicholiday {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;


    opens com.example.nextpublicholiday to javafx.fxml;
    exports com.example.nextpublicholiday;
}