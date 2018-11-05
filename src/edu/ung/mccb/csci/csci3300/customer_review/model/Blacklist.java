package edu.ung.mccb.csci.csci3300.customer_review.model;

import java.sql.*;

public class Blacklist {
    private Review rv = new Review();
    private PreparedStatement preparedStatement;
    private ResultSet results;

    private int ipListID;
    private String ipAddress;

    /* Check IP of post to see if it's been blacklisted */
    public boolean isBlacklisted (String IP) {
        String query = "SELECT * FROM blacklist WHERE ipAddress =?";
        try {
            rv.connectionHelper(query, IP, -1);
            if (results.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    /* Flag a review as fake and blacklist the IP */



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