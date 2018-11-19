package edu.ung.mccb.csci.csci3300.customer_review;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/ung/mccb/csci/csci3300/customer_review/view/UserReview.fxml"));
        root.getStylesheets().add("edu/ung/mccb/csci/csci3300/customer_review/css/main.css");
        primaryStage.setTitle("Review Submission");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {

        Application.launch(args);



        }
}
