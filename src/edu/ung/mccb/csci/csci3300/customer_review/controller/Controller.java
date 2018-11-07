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
    static Review review = new Review();
    static Blacklist blacklist = new Blacklist();

    public void saveToDB (ActionEvent actionEvent) { // TODO: Completely refactor to remove java.sql.* invocations and call provided DatabaseHelper methods
        String query = "INSERT INTO REVIEW" + "(reviewText, rating, IP)" + "values(?,?,?)";

        if (verifyCaptcha()) {
            IP = logIP();
            boolean isValid = blacklist.isBlacklisted(IP);

            /* Testing values for nullpointer   // !!! Do not edit this. I am using this info for testing !!! Stephen
            System.out.println("Review: ");
            System.out.println();
            System.out.println("RATING:" + rating.getValue());
            System.out.println();
            System.out.println("IP: " + IP);
            System.out.println();
            System.out.println("TEXT: " + reviewText.getText());*/

            /* Method call for inserting review into database */

            if (blacklist.isBlacklisted(IP)){
                review.insertFakeReview(IP, "Example Flagged Text", 5);
                //review.insertFakeReview(IP, reviewText.getText(), (int)Math.round(rating.getValue()));  TODO: this can be re-added to code once it can read from FXML
            }
            else
                review.insertReview(IP, "Example New Reivew Text", 5);
                //review.insertReview(IP, reviewText.getText(), (int)Math.round(rating.getValue())); TODO: this can be re-added to code once it can read from FXML

            /* !!! End of Testing Code !!! */
            changeScene(2);
            return;
        } else {
            changeScene(0);
        }
    }

    public boolean verifyCaptcha () {
        String uCaptcha = captcha.getText();
        String cCaptcha = ""; // TODO: removed String for testing purposes, can re-add once captcha is working - Stephen
        if (uCaptcha.equals(cCaptcha)){
        return true;
        }
        else{
            return false;

        }
    }

    public static String logIP() {
        String buildIP = "";
        Random RNG = new Random();

        /* 15% chance to get blacklisted IP */
        int chance = RNG.nextInt(100);
        //System.out.println("Blacklisted IP chance: " + chance);
        if (chance > 15) {
            for (int i = 0; i < 3; i++) {
                if (buildIP.equals("")) {
                    buildIP = Integer.toString(RNG.nextInt(256));
                }
                buildIP = buildIP + "." + RNG.nextInt(256);
            }
        }
        else{
            int rows = review.tableCount("blacklist");
            int rand = RNG.nextInt(rows);
            //System.out.println("Random blacklisted ID: " + rand);
            buildIP = blacklist.getBlacklistedIP(rand);
            //System.out.println("Random blacklisted IP: " + buildIP);
        }
        return buildIP;
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
