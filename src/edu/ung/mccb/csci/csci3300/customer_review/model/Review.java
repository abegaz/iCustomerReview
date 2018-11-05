package edu.ung.mccb.csci.csci3300.customer_review.model;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Review { // TODO: What purpose does this class actually serve if all its active methods are being outsourced to model.DatabaseHelper??

    private static Connection connect;
    private static Statement statement;
    private static PreparedStatement preparedStatement;
    private static ResultSet results;
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
                connect = DatabaseConfigurator.getConnection();
                preparedStatement = connect.prepareStatement(query);
                results = preparedStatement.executeQuery();
                printResults(results);

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /* Read a selected Review based upon the primary key */
    public void readReview(int ID) {
        String query = "Select * from review where reviewID = ?";

        try {
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            results = preparedStatement.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /* Prints results to console */
    public void printResults (ResultSet results){
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
        System.out.printf("%-5s%-15s%-10s%-10s%-10s","ID","IP Address","Rating","Valid","Review");
        System.out.println();
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-10s%-10s%-10s",reviewID,postingIP,rating,isValid,reviewText);
        System.out.println();
    }

    /* Returns the count number of table inputed */
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

    /* Pull review from database and then flag postingIP as blacklisted
    public void insertBlacklist(int ID){



        try {
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,x);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "INSERT INTO blacklist" + "(ipAddress)" + "values(?)";


    }*/

    /* Insert a new blacklisted IP to database */
    public void insertBlacklist(String ip){
        String query = "INSERT INTO blacklist" + "(ipAddress)" + "values(?)";
        boolean input = false;
        do {
            if (isValidIP(ip)) {
                try {
                    connect = DatabaseConfigurator.getConnection();
                    preparedStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, ip);
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                input = true;
            } else {
                System.out.print("Please enter a valid IP: ");
                ip = scan.nextLine();
            }
        } while (!input);
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
