package edu.ung.mccb.csci.csci3300.customer_review.model;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Review {

    private Connection connect;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet results;
    private Scanner scan = new Scanner(System.in);

    private int reviewID;
    private String reviewText;
    private String postingIP;
    private int rating;
    private boolean isValid;

    public Review () {
        // TODO: Constructor body
    }

    public Review (String text, String IP, int rating) {
        // TODO: constructor body
    }

    public void insertReview() {
        // TODO: method body
    }

    /* Insert a new Review to the Database */
    public void insertReview(String ip, String text, int rating) {
        String query = "INSERT INTO `review`" + "(`postingIP`,`reviewTextArea`,`rating`)" + "values(?,?,?)";

        try {
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, ip);
            preparedStatement.setString(2, text);
            preparedStatement.setInt(3, rating);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Insert new Review flagged as fake */
    public void insertFakeReview(String ip, String text, int rating){
        String query = "INSERT INTO `review`" + "(`postingIP`,`reviewText`,`rating`,`isValid`)" + "values(?,?,?,?)";

        try {
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, ip);
            preparedStatement.setString(2, text);
            preparedStatement.setInt(3, rating);
            preparedStatement.setInt(4, 0);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* Read all the Reviews */
    public void readReview() {
            String query = "Select * from review where isValid = 1";
        try {
            getReviewResults(connectionHelper(query, "", -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        printResults(results);
    }

    /* Read a selected Review based upon the primary key */
    public void readReview(int ID) {
        String query = "Select * from review where reviewID = ?";
        try {
            getReviewResults(connectionHelper(query, "", ID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        printResults(results);
    }

    /* Read all Review(s) based upon the rating */
    public void readRatings (int rating) {
        String query = "Select * from review where rating = ?";
        try {
            getReviewResults(connectionHelper(query, "", rating));
        } catch (Exception e) {
            e.printStackTrace();
        }
        printResults(results);
    }

    /* Read all Review(s) based upon the postingIP */
    public ResultSet readReview(String IP) {
        String query = "Select * from review where postingIP = ?";
        try {
            getReviewResults(connectionHelper(query, IP, -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        printResults(results);
        return results;
    }

    /* Flag a review as fake and blacklist the IP */
    public void flagReview(String IP){
        String query = "UPDATE review SET isValid = false WHERE postingIP = ?";
        updateDatabase(query, IP, -1);
    }

    /* Commonality method for database connection */
    public ResultSet connectionHelper (String query, String s, int i) throws Exception {
        try {
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query);

            if (s != "" && i == -1){
                preparedStatement.setString(1, s);
                preparedStatement.executeQuery();
            }
            else if (s == "" && i != -1){
                preparedStatement.setInt(1, i);
                preparedStatement.executeQuery();
            }
            else if (s == "" && i == -1);
                preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        };
        return results;
    }

    /* Updates Database with prepared statement, requires different execute */
    public void updateDatabase(String query, String s, int i){
        try{
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query);

            if (s != "" && i == -1) {
                preparedStatement.setString(1, s);
                preparedStatement.executeUpdate();
            }
            else if (i != -1 && s == "") {
                preparedStatement.setInt(1, i);
                preparedStatement.executeUpdate();
            }
            else if (s == "" && i == -1) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }

    /* Gets results from query for Review table */
    public ArrayList<ArrayList<String>> getReviewResults(ResultSet results){
        ArrayList<String> reviewTexts = new ArrayList<String>();
        ArrayList<String> reviewRatings = new ArrayList<String>();
        ArrayList<ArrayList<String>> reviews = new ArrayList<ArrayList<String>>();

        try {
            if (!results.next()) {
                System.out.println("ResultSet in empty in Java");
            } else {
                while (results.next()){
                    //reviewID = results.getInt("reviewID");
                    //postingIP = results.getString("postingIP");
                    reviewText = results.getString("reviewText");
                    rating = results.getInt("rating");
                    //isValid = results.getBoolean("isValid");
                    printWebResults(results);

                    reviewTexts.add(reviewText);
                    reviewRatings.add(String.valueOf(rating));
                }
                reviews.add(reviewTexts);
                reviews.add(reviewRatings);

                return reviews;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    /* Prints results to console */
    public void printResults (ResultSet results){
        //System.out.printf("%-5s%-15s%-10s%-10s%-10s","ID","IP Address","Rating","Valid","Review");
        //System.out.println();
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-10s%-10s%-10s",reviewID,postingIP,rating,isValid,reviewText);
        System.out.println();
    }

    /* Prints ONLY rating and text for the public, notValid reviews are kept hidden */
    public void printWebResults (ResultSet results){
        //System.out.printf("%-5s%-15s%-10s%-10s%-10s","ID","IP Address","Rating","Valid","Review");
        //System.out.println();
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-5s%-15s",rating,reviewText);
        System.out.println();
    }

    /* Returns the count number of a specified table for range checking*/
    public int tableCount(String table){
        int count = 0;
        String query = "Select COUNT(*) from " + table;
        try {
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query);
            results = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (!results.next()) {
                System.out.println("ResultSet in empty in Java");
            } else {
                count = results.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Total rows in " + table + " = " + count);
        return count;
    }

    public String toString(){
        String query = "Select * from review where isValid = 1";
        try {
            getReviewResults(connectionHelper(query, "", -1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String reviewString = "";

        ArrayList<ArrayList<String>> reviews = getReviewResults(results);

        ArrayList<String> reviewTexts = reviews.get(0);
        ArrayList<String> reviewRatings = reviews.get(1);

        for(int i = 0; i < reviewTexts.size(); i++) {
            System.out.println(reviewRatings.get(i) + reviewTexts.get(i));
            reviewString.concat(reviewRatings.get(i) + ": " + reviewTexts.get(i) + "\n");
        }

        return reviewString;
    }

    /* Verifies IP address is a valid IP */
    public static boolean isValidIP(String ip){
        Pattern pat = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher match = pat.matcher(ip);
        return match.find();
    }

    /* toString */
    @Override
    public String toString() {
        return "Review{" + rating + " " + reviewText + "}";
    }

    // accessor/mutator methods
    public int getReviewID () {
        return reviewID;
    }

    public void setReviewID (int reviewID) {
        this.reviewID = reviewID;
    }

    public String getReviewText () {
        return reviewText;
    }

    public void setReviewText (String reviewText) {
        this.reviewText = reviewText;
    }

    public String getIP () { return postingIP; }

    public void setIP (String IP) {
        this.postingIP = IP;
    }

    public int getRating () {
        return rating;
    }

    public void setRating (int rating) {
        this.rating = rating;
    }

    public boolean getIsValid () {
        return isValid;
    }

    public void setIsValid (boolean isValid) {
        this.isValid = isValid;
    }
}
