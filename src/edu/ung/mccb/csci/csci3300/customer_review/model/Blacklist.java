package edu.ung.mccb.csci.csci3300.customer_review.model;

import javax.xml.crypto.Data;
import java.sql.*;

public class Blacklist {
    private Review rv = new Review();
    private PreparedStatement preparedStatement;
    private Connection connect;
    private ResultSet results;

    private int ipListID;
    private String ipAddress;

    /* Check IP of post to see if it's been blacklisted */
    public boolean isBlacklisted (String IP) {
        int count = 0;
        String query = "SELECT Count(*) FROM `blacklist` WHERE `ipAddress` = ?";

        try {
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, IP);
            results = preparedStatement.executeQuery();

            if (!results.next()) {
                System.out.println("ResultSet in empty in Java");
            } else {
                count = results.getInt("Count(*)");
            }

            System.out.print("Number of blacklisted entries: " + count);
            if (count > 0)
                return true;
            else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /* Returns a blacklisted IP */
    public String getBlacklistedIP (int ID){
        String query = "SELECT ipAddress FROM blacklist WHERE ipListID = ?";
        try {
            connect = DatabaseConfigurator.getConnection();
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            results = preparedStatement.executeQuery();
            if (!results.next()) {
                System.out.println("ResultSet in empty in Java");
            } else {
                ipAddress = results.getString("ipAddress");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ipAddress;
    }

    /* Insert a new blacklisted IP to database */
    public void insertBlacklist(String ip){
        String query = "INSERT INTO blacklist" + "(ipAddress)" + "values(?)";
        try {
            rv.connectionHelper(query, ip, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Finds reviews from database that are blacklisted */
    public void findBlacklistedReviews(int ID){
        String query = "SELECT reviewID FROM review, blacklist WHERE postingIP = ipAddress";
        try {
            getBlacklistResults(rv.connectionHelper(query,"", -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rv.printResults(results);
    }
    /* Gets results from query for Review table */
    public void getBlacklistResults(ResultSet results){
        try {
            if (!results.next()) {
                System.out.println("ResultSet in empty in Java");
            } else {
                ipListID = results.getInt("ipListID");
                ipAddress = results.getString("ipAddress");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Prints results to console */
    public void printResults (ResultSet results){
        System.out.printf("%-10s%-5s","ID","IP Address");
        System.out.println();
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-10s%-5s",ipListID,ipAddress);
        System.out.println();
    }
}
