package edu.ung.mccb.csci.csci3300.customer_review.controller;

import edu.ung.mccb.csci.csci3300.customer_review.model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class Controller {
    @FXML
    TextField reviewText;
    Slider rating;
    private String IP; // TODO: Do we want logIP() to set this value directly or just return a string that can be assigned to this value?

    ///Brisaac is mapping out some logic here nothing here is finite. I know some of these methods are wrong.
    public void saveToDB (ActionEvent actionEvent) { // TODO: Decide if method should be outsourced to model package
        String query = "INSERT INTO REVIEW" + "(reviewText, rating, IP)" + "values(?,?,?)";

        if (verifyCaptcha()) {
            IP = logIP();
            boolean isValid = validateIP(IP);

            try {
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
            changeScene(2);
            return;
        } else {
            changeScene(0);
        }
    }

    public boolean verifyCaptcha () {
        // TODO: method body
    }

    public String logIP () {
        // TODO: method body
    }

    public boolean validateIP (String IP) {
        // TODO: method body
    }

    public void changeScene (int sceneID) {
        // TODO: method body
        switch(sceneID) {
            case 0: { // Captcha error popup

                break;
            }
            case 1: { // review submit page
               /* TODO: add scene switch logic for relevant scenes
               Parent root = FXMLLoader.load(getClass().getResource("view/UserRegister.fxml"));
               primaryStage.setTitle("iCustomerReview");
               primaryStage.setScene(new Scene(root, 550, 550));
               primaryStage.show();
                */
                break;
            }
            case 2: { // read reviews page

                break;
            }
            default: { // default (necessary?)

            }
        }
        return;
    }
}
