package com.example.nextpublicholiday;

import com.example.model.HolidayService;
import com.example.view.MainView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;

public class MainController {
    private HolidayService holidayService;
    private final MainView mainView;

    @FXML
    private Button fetchButton; // Updated to use meaningful name

    public MainController(HolidayService holidayService, MainView mainView) {
        this.holidayService = holidayService;
        this.mainView = mainView;
    }

    public void initialize() {
        mainView.setController(this);
    }
    @FXML
    public void fetchHolidaysByUserInput() {
        String date = mainView.getDateTextField().getText(); // Assuming you have a getter for dateTextField
        mainView.clearHolidays();

        try {
            List<Holiday> holidays = holidayService.fetchNextPublicHolidays(date);
            mainView.displayHolidays(holidays);
            // You can do further processing or UI updates if needed
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions as needed
            mainView.displayError("Failed to fetch holidays. Please try again.");
        }
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }
}
