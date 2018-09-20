package edu.ung.mccb.csci.csci3300.fakeapples.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {

    public DatabaseController () {
        String database = "DBNAME"; // TODO: get DB name
    }

    public String getEntry (String table, String attribute, String keyValue) {
        int affectedRow = 0;
        String lookup = "SELECT * FROM " + table + " WHERE " + attribute + " = '" + keyValue + "'";
        ResultSet result = null;

        try {
            Connection connect = DatabaseConnect.getConnection();
            Statement sqlString = connect.createStatement();
            result = sqlString.executeQuery (lookup);
            return result.getString(attribute);
        }
        catch (Exception someError) { System.out.println("There was an error connecting to the database.\n" + someError); }

    return "NULL";
    }



    private static class DatabaseConnect {

        private static final String USERNAME = "tamirat"; // TODO: get the actual credentials for this.
        private static final String PASSWORD = "fnv7Qysi7hkO5uf9";
        private static final String CONN_STRING = "jdbc:mysql://localhost/sepstarterdb";

        // connection  method that connects us to the MySQL database
        public static Connection getConnection() throws SQLException {
            //System.out.println("Connected to student database successfully!");
            return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
        }

        // method that displays our errors in more detail if connection fails
        public static void displayException(SQLException ex){

            System.err.println("Error Message: " + ex.getMessage());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("SQL Status: " + ex.getSQLState());
        }
    }
}