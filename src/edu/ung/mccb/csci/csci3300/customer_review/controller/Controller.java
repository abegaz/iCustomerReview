package edu.ung.mccb.csci.csci3300.customer_review.controller;

import edu.ung.mccb.csci.csci3300.customer_review.model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Random;


public class Controller {

    @FXML TextArea reviewText, captcha;
    @FXML Slider ratingSlider;

    private static Review review = new Review();
    private static Blacklist blacklist = new Blacklist();

    public void saveToDB (ActionEvent actionEvent) {
        if (verifyCaptcha()) {
            String IP = logIP();
            String reviewText = this.reviewText.getText();
            int rating = (int)Math.round(this.ratingSlider.getValue());
            boolean isBlacklisted = blacklist.isBlacklisted(IP); // negated so value is human-expected

            // Print review to console prior to database push
            System.out.println("Submitted Review: "); // DEBUG
            System.out.println("RATING:" + rating);
            System.out.println("IP: " + IP);
            System.out.println("BLACKLISTED: " + isBlacklisted);
            System.out.println("TEXT:\n" + reviewText);

            if (isBlacklisted) { // negated for human readability
                //view.insertFakeReview(IP, "Example Flagged Text", 5);
                review.insertFakeReview(IP, reviewText, rating);
                System.out.println("INFO: Review inserted as fraudulent"); // DEBUG
            } else {
                //review.insertReview(IP, "Example New Reivew Text", 5);
                review.insertReview(IP, reviewText, rating);
                System.out.println("INFO: Review inserted as valid"); // DEBUG
            }

            changeScene(2);
            return;
        }
            changeScene(0);
    }

    private boolean verifyCaptcha () {
        System.out.println("HIGH LOGIC: Verifying Captcha");

        String uCaptcha = captcha.getText(); // TODO: Will this read from the invocation correctly or does the value of the textField need to be passed in manually?
        String cCaptcha = ""; // TODO: removed String for testing purposes, can re-add once captcha is working - Stephen
        System.out.println("Entered Captcha: " + uCaptcha + "\nKey Captcha: " + cCaptcha);

        return uCaptcha.equals(cCaptcha);
    }

    private static String logIP() {
        System.out.println("HIGH LOGIC: Logging (generating) source IP");

        String buildIP = "";
        Random RNG = new Random();
        int chance = RNG.nextInt(100);
        System.out.println("Return blacklisted IP chance: " + chance);

        // 15% chance to get blacklisted IP
        if (chance > 15) {
            for (int i = 0; i < 3; i++) {
                if (buildIP.equals("")) {
                    buildIP = Integer.toString(RNG.nextInt(256));
                }
                buildIP = buildIP + "." + RNG.nextInt(256);
            }
            System.out.println("Non-blacklisted IP generated\nValue: " + buildIP);
        } else {
            int rows = review.tableCount("blacklist");
            int rand = RNG.nextInt(rows);
            buildIP = blacklist.getBlacklistedIP(rand);
            System.out.println("Blacklisted IP generated\nRandom blacklisted ID: " + rand + "\nRandom blacklisted IP: " + buildIP); // DEBUG
        }
        return buildIP;
    }

    private void changeScene (int sceneID) { // TODO: integration testing
        Stage newStage = new Stage();

        switch(sceneID) {
            case 0: { // Captcha error popup
                System.out.println("HIGH LOGIC: Change scene to CAPTCHA ERROR"); // DEBUG
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Captcha");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Captcha. Please try again.");
                alert.showAndWait();
                return;
            }
            case 1: { // review submit page
                System.out.println("HIGH LOGIC: Change scene to SUBMIT REVIEW"); // DEBUG
                try {
                    Parent sceneFile = FXMLLoader.load(getClass().getResource("/edu/ung/mccb/csci/csci3300/customer_review/view/UserReview.fxml"));
                    newStage.setTitle("Submit a Review");
                    newStage.setScene(new Scene(sceneFile, 550, 550)); // TODO: adjust window size as needed
                } catch (Exception e) {
                    System.out.println("ERROR: Unhandled exception caught in scene loading.");
                    e.printStackTrace();
                    System.exit(-1);
                }
                break;
            }
            case 2: { // read reviews page
                System.out.println("HIGH LOGIC: Change scene to READ REVIEWS"); // DEBUG
                try {
                    Parent sceneFile = FXMLLoader.load(getClass().getResource("/edu/ung/mccb/csci/csci3300/customer_review/view/ProductReview.fxml"));
                    newStage.setTitle("Product Reviews");
                    newStage.setScene(new Scene(sceneFile, 550, 550)); // TODO: adjust window size as needed
                } catch (Exception e) {
                    System.out.println("ERROR: Unhandled exception caught in scene loading.");
                    e.printStackTrace();
                    System.exit(-1);
                }
                break;
            }
        }
        newStage.show();
    }
}
