package edu.ung.mccb.csci.csci3300.customer_review.controller;

import edu.ung.mccb.csci.csci3300.customer_review.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.Random;

public class Controller {
    @FXML TextArea reviewText,captcha;
    @FXML Text captchaImage,title, pr;
    @FXML Slider ratingSlider;

    private static Review review = new Review();
    private static Blacklist blacklist = new Blacklist();

    public void saveToDB (ActionEvent actionEvent)  {
        if (verifyCaptcha()) {
            String IP = logIP();
            String reviewText = this.reviewText.getText();
            int rating = (int) Math.round(this.ratingSlider.getValue());
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
                changeScene(2);
            } else {
                //review.insertReview(IP, "Example New Reivew Text", 5);
                review.insertReview(IP, reviewText, rating);
                System.out.println("INFO: Review inserted as valid"); // DEBUG
                changeScene(1);
            }

        } else
            changeScene(0);
    }

    public void revealCaptcha(MouseEvent mouseEvent) {
        Captcha text = new Captcha();
        captchaImage.setText(text.generateRandomString());
        captchaImage.setFill(Color.BLACK);
        captchaImage.setFont(Font.font(null,  FontWeight.EXTRA_LIGHT, 40));
        captchaImage.setEffect(new GaussianBlur());
        //captchaImage.setStrokeWidth(10.0);
        captchaImage.setStrikethrough(true);
        // captchaImage.setCaretBias(true);
        /*PerspectiveTransform pt = new PerspectiveTransform();
        pt.setUlx(10.0f);
        pt.setUly(10.0f);
        pt.setUrx(310.0f);
        pt.setUry(40.0f);
        pt.setLrx(310.0f);
        pt.setLry(60.0f);
        pt.setLlx(10.0f);
        pt.setLly(90.0f);
        pt.setUlx(10.0f);
        pt.setUly(10.0f);
        pt.setUrx(310.0f);
        pt.setUry(40.0f);
        pt.setLrx(310.0f);
        pt.setLry(60.0f);
        pt.setLlx(10.0f);
        pt.setLly(90.0f);
        captchaImage.setEffect(pt);*/
    }

    private boolean verifyCaptcha () {
        String uCaptcha = captcha.getText();
        if(captchaImage.getText().equals(uCaptcha)) {
            return true;
        }
        else{
            Captcha text = new Captcha();
            captchaImage.setText(text.generateRandomString());
            captchaImage.setFill(Color.BLACK);
            captchaImage.setFont(Font.font(null,  FontWeight.EXTRA_LIGHT, 40));
            captchaImage.setEffect(new GaussianBlur());
            captchaImage.setStrokeWidth(10.0);
            captchaImage.setStrikethrough(false);
            captchaImage.setCaretBias(true);
            captcha.clear();
            return false;
        }
    }

    private static String logIP() {
        System.out.println("HIGH LOGIC: Logging (generating) source IP");

        String buildIP = "";
        Random RNG = new Random();
        int chance = RNG.nextInt(100);
        System.out.println("Return blacklisted IP chance: " + chance);

        // 15% chance to get blacklisted IP
        if (chance > 50) {
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

    private void changeScene (int sceneID) {
        //committed out stage methods
       // Stage newStage = new Stage();

        switch(sceneID) {
            case 0: { // Captcha error popup
                //System.out.println("HIGH LOGIC: Change scene to CAPTCHA ERROR");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Captcha");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Captcha. Please try again.");
                alert.showAndWait();
                return;
            }
            case 1: { // CONFIRMATION
                //System.out.println("HIGH LOGIC: Change scene to CAPTCHA ERROR");
                Alert submitR = new Alert(Alert.AlertType.CONFIRMATION);
                submitR.setTitle("Successful Submission");
                submitR.setHeaderText(null);
                submitR.setContentText("Review Submitted");
                submitR.showAndWait();
                return;
            }

            case 2: { // REJECTION
                //System.out.println("HIGH LOGIC: Change scene to  ERROR");
                Alert submitR = new Alert(Alert.AlertType.ERROR);
                submitR.setTitle("Submission Rejected");
                submitR.setHeaderText(null);
                submitR.setContentText("Sorry, but you are blacklisted");
                submitR.showAndWait();
                return;
            
            }
    }
        //newStage.show();
    }
}



