package com.example.view;

import com.example.nextpublicholiday.Holiday;
import com.example.nextpublicholiday.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class MainView {
    private MainController controller;
    private final ListView<String> holidayListView;
    private final ProgressIndicator loadingIndicator;
    private final TextField dateTextField; // Renamed to match FXML
    private final Button fetchButton; // Renamed to match FXML

    public MainView() {
        this.controller = null;
        this.holidayListView = new ListView<>();
        this.loadingIndicator = new ProgressIndicator();
        this.dateTextField = new TextField();
        this.fetchButton = new Button("Fetch Holidays");
        loadingIndicator.setVisible(false);
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public Scene createScene() {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10px");

        Label titleLabel = new Label("Upcoming Holidays in Canada");

        // Input field for date
        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(
                new Label("Date (yyyy-mm-dd):"), dateTextField
        );

        fetchButton.setOnAction(this::handleFetchButtonClick);

        root.getChildren().addAll(titleLabel, inputBox, fetchButton, holidayListView, loadingIndicator);

        return new Scene(root, 400, 300);
    }

    private void handleFetchButtonClick(ActionEvent event) {
        loadingIndicator.setVisible(true);

        String date = dateTextField.getText();

        try {
            // Validate the date format (you may want to add more robust validation)
            if (isValidDateFormat(date)) {
                controller.fetchHolidaysByUserInput();
            } else {
                displayError("Invalid date format. Please enter a date in the format yyyy-mm-dd.");
            }
        } catch (NumberFormatException e) {
            displayError("Invalid date format. Please enter a date in the format yyyy-mm-dd.");
        }
    }

    private boolean isValidDateFormat(String date) {
        // Implement date format validation logic
        // For simplicity, you can use regex or a date parsing library
        // Here's a simple regex example:
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public void displayError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void clearHolidays() {
        holidayListView.getItems().clear();
    }

    public void displayHolidays(List<Holiday> holidays) {
        loadingIndicator.setVisible(false);

        ObservableList<String> holidayInfo = FXCollections.observableArrayList();
        for (Holiday holiday : holidays) {
            // Customize this according to your Holiday class structure
            String displayString = holiday.getName() + " on " + holiday.getDate();
            holidayInfo.add(displayString);
        }
        holidayListView.setItems(holidayInfo);
    }

    public TextField getDateTextField() {
        return dateTextField;
    }
}
