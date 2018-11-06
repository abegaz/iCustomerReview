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
    TextField reviewText, captcha;
    Slider rating;
    private String IP;

    public void saveToDB (ActionEvent actionEvent) { // TODO: Completely refactor to remove java.sql.* invocations and call provided DatabaseHelper methods
        String query = "INSERT INTO REVIEW" + "(reviewText, rating, IP)" + "values(?,?,?)";

        if (verifyCaptcha()) {
            IP = logIP();
            boolean isValid = validateIP(IP);

            /* Testing values for nullpointer   !!! Do not edit this method. I am using this info for testing !!!
            System.out.println("Review: ");
            System.out.println();
            //System.out.println("RATING:" + rating.getValue());
            System.out.println();
            System.out.println("IP: " + IP);
            System.out.println();
            System.out.println("TEXT: " + reviewText.getText());*/

            /* Method call for inserting review into database */
            Review review = new Review();
            Blacklist blacklist = new Blacklist();

            review.readReview();

            if (blacklist.isBlacklisted(IP)){
                review.insertReview("1.1.1.1", "Example Flagged Text", 5);
                review.flagReview("1.1.1.1");
            }
            else
                review.insertReview("1.1.1.1", "Example New Reivew Text", 5);

            //review.insertReview(IP, reviewText.getText(), (int)Math.round(rating.getValue()));
            changeScene(2);
            return;
        } else {
            changeScene(0);
        }
    }

    public boolean verifyCaptcha () {
        String uCaptcha = captcha.getText();
        String cCaptcha = "1j93k9L";
        if (uCaptcha.equals(cCaptcha)){
        return true;
        }
        else{
            return false;

        }
    }

    public static String logIP() { // TODO: create 1 in 10 chance of returning a blacklist IP from database ... for testing purposes
        String buildIP = "";
        Random RNG = new Random();

        for (int i = 0; i < 3; i++) {
            if (buildIP.equals("")) {
                buildIP = Integer.toString(RNG.nextInt(256));
            }
            buildIP = buildIP + "." + RNG.nextInt(256);
        }
        return buildIP;
    }

    public boolean validateIP (String IP) {
        // TODO: method body (pending blacklist retrieval method from DatabaseHelper)
        return false; // TEMPORARY
    }

    public void changeScene (int sceneID) { // TODO: integration testing
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
                try {
                    Parent sceneFile = FXMLLoader.load(getClass().getResource("edu/ung/mccb/csci/csci3300/customer_review/view/UserReview.fxml"));
                    newStage.setTitle("Submit a Review");
                    newStage.setScene(new Scene(sceneFile, 550, 550)); // TODO: adjust window size as needed
                } catch (IOException e) {
                    System.out.println("ERROR: Unhandled exception caught in scene loading.");
                    e.printStackTrace();
                    System.exit(-1);
                }

                break;
            }
            case 2: { // read reviews page
                try {
                    Parent sceneFile = FXMLLoader.load(getClass().getResource("edu/ung/mccb/csci/csci3300/customer_review/view/Products.fxml"));
                    newStage.setTitle("Product Reviews");
                    newStage.setScene(new Scene(sceneFile, 550, 550)); // TODO: adjust window size as needed
                } catch (IOException e) {
                    System.out.println("ERROR: Unhandled exception caught in scene loading.");
                    e.printStackTrace();
                    System.exit(-1);
                }

                break;
            }
        }
        newStage.show();
        return;
    }
}
