package edu.ung.mccb.csci.csci3300.customer_review.model;

import java.sql.*;
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
        String query = "INSERT INTO `review`" + "(`postingIP`,`reviewText`,`rating`)" + "values(?,?,?)";

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
    /* Read all the Reviews */
    public void readReview() {
            String query = "Select * from review";
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

    /* Commonality method for database connection */
    public ResultSet connectionHelper (String query, String s, int i) throws Exception {
        try {
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query);

            if (s != "" && i == -1)
                preparedStatement.setString(1, s);
            else if (i != -1 && s == "")
                preparedStatement.setInt(1, i);
            else if (s == "" && i == -1);
        } catch (SQLException e) {
            e.printStackTrace();
        };
        return results = preparedStatement.executeQuery();
    }

    /* Gets results from query for Review table */
    public void getReviewResults(ResultSet results){
        try {
            if (!results.next()) {
                System.out.println("ResultSet in empty in Java");
            } else {
                reviewID = results.getInt("reviewID");
                postingIP = results.getString("postingIP");
                reviewText = results.getString("reviewText");
                rating = results.getInt("rating");
                isValid = results.getBoolean("isValid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Prints results to console */
    public void printResults (ResultSet results){
        System.out.printf("%-5s%-15s%-10s%-10s%-10s","ID","IP Address","Rating","Valid","Review");
        System.out.println();
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-10s%-10s%-10s",reviewID,postingIP,rating,isValid,reviewText);
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
        System.out.println(count);
        return count;
    }

    /* Verifies IP address is a valid IP */
    public static boolean isValidIP(String ip){
        Pattern pat = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
        Matcher match = pat.matcher(ip);
        return match.find();
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
