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

   public void saveToDB (ActionEvent actionEvent) {
        // TODO: method body
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
