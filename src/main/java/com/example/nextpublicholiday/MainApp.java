package com.example.nextpublicholiday;

import com.example.model.HolidayService;
import com.example.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HolidayService holidayService = new HolidayService();
        MainView mainView = new MainView();
        MainController mainController = new MainController(holidayService, mainView);

        mainController.initialize();


        primaryStage.setTitle("Application");
        primaryStage.setScene(mainView.createScene());
        primaryStage.show();
    }
}