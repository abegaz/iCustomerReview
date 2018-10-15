package edu.ung.mccb.csci.csci3300.customer_review.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserData {
    private int account_ID;
    private String username;
    private String hashedPassword;
    private String firstName;
    private String lastName;
    private String displayName; // TODO: add any additional vars stored about users to this data object

    public void setAccountIDbyUsername (String username) {
        String query = "SELECT accountID FROM USER WHERE username = '" + username + "'";
        try {
            Connection connect = DatabaseConfigurator.getConnection();
            Statement sqlStatement = connect.createStatement();
            ResultSet result = sqlStatement.executeQuery(query);

            account_ID = result.getInt("accountID");
        }
        catch (SQLException e) {
            System.out.println("Operation failed due to SQL exception:\n" + e);
        }
        return;
    }

    public void getDataFromUser () {
        String query = "SELECT * FROM USER WHERE accountID = '" + account_ID + "'";

        try {
            Connection connect = DatabaseConfigurator.getConnection();
            Statement sqlStatement = connect.createStatement();
            ResultSet result = sqlStatement.executeQuery(query);

            username = result.getString("username");
            hashedPassword = result.getString("hashedPassword"); // TODO: double-check table column names and correct as necessary
            firstName = result.getString("firstName");
            lastName = result.getString("lastName");
            displayName = result.getString("displayName");
        }
        catch (SQLException e) {
            System.out.println("Operation failed due to SQL exception:\n" + e);
        }
        return;
    }

    public void assignDataToUser () {
        String query = "UPDATE USER SET username='" + username + "',hashedPassword='" + hashedPassword + "',firstName='" + firstName + "',lastName='" + lastName + "',displayName='" + displayName + "'WHEREaccountID='" + account_ID + "'";

        try {
            Connection connect = DatabaseConfigurator.getConnection();
            Statement sqlStatement = connect.createStatement();
            sqlStatement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Operation failed due to SQL exception:\n" + e);
        }
        return;
    }

    public int getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(int account_ID) {
        this.account_ID = account_ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}