package edu.ung.mccb.csci.csci3300.customer_review.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to handle connecting to the database.
 */
public class DatabaseConfigurator {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost/customer_review_db";

    /**
     * Establishes a connection to the database to transmit SQL syntax.
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
       //  System.out.println("User logged in Successfully!");
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }

    /**
     * Displays details about errors when SQL exceptions occur.
     * @param ex SQLException
     */
    public static void displayException(SQLException ex){

        System.err.println("Error Message: " + ex.getMessage());
        System.err.println("Error Code: " + ex.getErrorCode());
        System.err.println("SQL Status: " + ex.getSQLState());

    }
}
