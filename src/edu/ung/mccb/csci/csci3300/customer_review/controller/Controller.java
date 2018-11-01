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
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller {
    @FXML
    TextField reviewText;
    Slider rating;
    private String IP;
    
///Brisaac is mapping out some logic here nothing here is finite. I know some of these methods are wrong.
   public void saveToDB (ActionEvent actionEvent) throws Exception {
       //Model model = new Model();
       //String userPassword =  password.getText();

       boolean isValid= validatePassword(userPassword, confirmUserPassword);
       /*
       if (verifyCaptcha) {

            //Data will be saved into the DB
           int * = *.saveUserIntoDatabase(reviewField.getText(),*more get methods needed);
           System.out.println("Users review was saved into the database");

          //New stage is generated, shouls this stage go to the display review for the Products fxml

           Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("*")); //route to product page
           primaryStage.setTitle("*");
           primaryStage.setScene(new Scene(root, 500, 500));
           primaryStage.show();

       }

       else
       {
         //message or another window that displays an error message rejecting the review that the user submitted
       }
        */

   }

   public void verifyCaptcha () {
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
   }
}
