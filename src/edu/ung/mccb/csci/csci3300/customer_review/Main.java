package edu.ung.mccb.csci.csci3300.customer_review;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/UserReview.fxml"));
        primaryStage.setTitle("Submit a Review");
        primaryStage.setScene(new Scene(root, 550, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
