package edu.ung.mccb.csci.csci3300.customer_review.controller;

import edu.ung.mccb.csci.csci3300.customer_review.model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Random;
import java.io.IOException;

public class Controller {
    @FXML
    TextField reviewText;
    Slider rating;
    private String IP;

    public void saveToDB (ActionEvent actionEvent) { // TODO: Completely refactor to remove java.sql.* invocations and call provided DatabaseHelper methods
        String query = "INSERT INTO REVIEW" + "(reviewText, rating, IP)" + "values(?,?,?)";

        if (verifyCaptcha()) {
            IP = logIP();
            boolean isValid = validateIP(IP);

            try { // TODO: Are we relocating this to model.Review or to model.DatabaseHelper?
                Connection connect = DatabaseConfigurator.getConnection();
                PreparedStatement sqlStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                sqlStatement.setString(1, reviewText.getText());
                sqlStatement.setString(2, Integer.toString((int)Math.round(rating.getValue())));
                sqlStatement.setString(3, IP);

                sqlStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Operation failed due to SQL Exception:\n");
                DatabaseConfigurator.displayException(e);
            }
            try {
                changeScene(2);
            } catch (IOException e) {
                System.out.println("ERROR: Unhandled exception loading scene from FXML. Application terminated.\n\nPrinting stack trace...");
                e.printStackTrace();
                System.exit(-1);
            }
            return;
        } else {
            try {
                changeScene(0);
            } catch (IOException e) {
                System.out.println("ERROR: Unhandled exception loading scene from FXML. Application terminated.\n\nPrinting stack trace...");
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public boolean verifyCaptcha () {
        // TODO: method body
        return false; // TEMPORARY
    }

    public static String logIP() { // TODO: create 1 in 10 chance of returning a blacklist IP from database ... for testing purposes
        String buildIP = "";
        Random RNG = new Random();

        for (int i = 0; i < 3; i++) { // TODO: quick integration testing
            if (buildIP.equals("")) {
                buildIP = Integer.toString(RNG.nextInt(256));
            }
            buildIP = buildIP + "." + RNG.nextInt(256);
        }
        /*
         * Loop iterates 3 times. First-position and second-position values are generated on pass 1.
         * Third and fourth-position values are generated on passes 2 and 3 respectively.
         */
        System.out.println("Generated IP:" + buildIP); // console print for integration testing
        return buildIP;
    }

    public boolean validateIP (String IP) {
        // TODO: method body (pending blacklist retrieval method from DatabaseHelper)
        return false; // TEMPORARY
    }

    public void changeScene (int sceneID) throws IOException { // TODO: integration testing
        Stage newStage = new Stage();

        switch(sceneID) {
            case 0: { // Captcha error popup
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Captcha");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Captcha. Please try again.");
                alert.showAndWait();
                return;
            }
            case 1: { // review submit page
                Parent sceneFile = FXMLLoader.load(getClass().getResource("view/UserReview.fxml"));
                newStage.setTitle("Submit a Review");
                newStage.setScene(new Scene(sceneFile, 550, 550)); // TODO: adjust window size as needed

                break;
            }
            case 2: { // read reviews page
                Parent sceneFile = FXMLLoader.load(getClass().getResource("view/Products.fxml"));
                newStage.setTitle("Product Reviews");
                newStage.setScene(new Scene(sceneFile, 550, 550)); // TODO: adjust window size as needed

                break;
            }
        }
        newStage.show();
        return;
    }
}
