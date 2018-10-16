package edu.ung.mccb.csci.csci3300.customer_review.controller;
import edu.ung.mccb.csci.csci3300.customer_review.model.RegisteredUser;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.security.SecureRandom;


public class Controller {
    @FXML
    TextField email, username, password, cpassword;
    static final String AlphaNum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom randomSalt = new SecureRandom();

    String genreateRandomSalt( int len ){
        StringBuilder sb = new StringBuilder(len);
        for( int i = 0; i < len; i++ )
            sb.append( AlphaNum.charAt( randomSalt.nextInt(AlphaNum.length()) ));
        return sb.toString();
    }

    //pending name based on whats called in view

    public void createuser(ActionEvent actionEvent) throws Exception{

        RegisteredUser user = new RegisteredUser();
        String userPassword =  password.getText();
        String confirmUserPassword =  cpassword.getText();
        boolean isValid= validatePassword(userPassword, confirmUserPassword);

        if (isValid) {
              //is this needed?

            String salt = user.generateSalt();
            String hashAndSaltedPassword = user.hashPassword(userPassword, salt);

            int resutl = user.registerNewUser(username.getText(),email.getText(),hashAndSaltedPassword,salt);
            // System.out.println("The salted hash code for the plaintext " + password.getText() + " is " + hashAndSaltedPassword);

            // waiting for the Register user page (fxml) to be created
            
            Stage primaryStage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/edu/ung/mccb/csci/csci3300/customer_review/view/*"));
            primaryStage.setTitle("iCustomerReview");
            primaryStage.setScene(new Scene(root, 500, 450));
            primaryStage.show();

        }
        else
        {
            message ();
        }

    }

    private static boolean validatePassword(String password, String cPassword) {
        if (password.equals(cPassword)) {
            // Password excepts ! as a special character 
            //regex
            if ((password.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+!=]).*$")))
                return true;
            else
                return false;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password mismatch");
            alert.setHeaderText("Please re-enter the password");
            alert.setContentText("The Password must mach, please Please re-enter the password");
            return false;
        }
    }
    private void message ()
    {
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
    
     //pending name based on whats called in view
    
    /*public void *BUTTON NAME IS NOT SET*(ActionEvent actionEvent) {
        Model model = new Model();
        boolean isRegistred = model.verifyLogin(password.getText(), username.getText());
        if(isRegistred)
        {
            System.out.println("User login Successful!");
        }
    }*/
}
