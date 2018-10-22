package edu.ung.mccb.csci.csci3300.customer_review.controller;

import edu.ung.mccb.csci.csci3300.customer_review.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller {
    @FXML
    TextField email, username, displayName, password, cpassword;

    private static int accountID = -1; // This variable is how the entire program tracks the current logged in user. THIS IS IMPORTANT.

    public void registerUser (ActionEvent actionEvent) throws Exception {

        RegisteredUser user = new RegisteredUser();
        boolean isValid= validatePassword(password.getText(), cpassword.getText());

        if (isValid) {
            user.registerNewUser(username.getText(),email.getText(),displayName.getText(),password.getText());

            // logUserIn(); TODO: auto-log in user and send them to the logged-in side of front-end

            Stage primaryStage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/edu/ung/mccb/csci/csci3300/customer_review/view/UserLogin.fxml"));
            primaryStage.setTitle("iCustomerReview");
            primaryStage.setScene(new Scene(root, 500, 450));
            primaryStage.show();
        }
        else {
            message ();
        }
    }

    public void logUserIn (ActionEvent actionEvent) {
        RegisteredUser user = new RegisteredUser(username.getText());
        if (user.verifyLogin(username.getText(), password.getText())) {
            accountID = user.getAccount_ID();

            // code here for loading a new FXML window TODO: add user front-end for logged-in users and load it here.
            return;
        }
        accountID = -1;
        return;
    }

    private static boolean validatePassword (String password, String cPassword) {
        if (password.equals(cPassword)) {
            // Password excepts ! as a special character
            //regex
            if ((password.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+!=]).*$")))
                return true;
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("The password entered does not meet the password requirements. Please see character requirements for more information.");
                alert.showAndWait();
                return false;
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password mismatch");
            alert.setHeaderText("Please re-enter the password");
            alert.setContentText("The Password must mach, please Please re-enter the password");
            alert.showAndWait();
            return false;
        }
    }

    private static void message () {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Requirements");
        alert.setHeaderText("The password entered here  is invalid");
        alert.setContentText("The Password should be at least 8 characters long.\n" +
                "Password should contain at least one lowercase letter .\n" +
                "Password should contain at least one uppercase letter .\n" +
                "Password should contain at least one digit .\n" +
                "Password should have at least special character.\n ");

        alert.showAndWait();
    }
}
