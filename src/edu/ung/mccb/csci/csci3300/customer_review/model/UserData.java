package edu.ung.mccb.csci.csci3300.customer_review.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * An object that represents a user and stores their attributes.
 * <p>
 *     Stores the attributes of a user in instance variables. Attributes are queried from the database via account_ID. account_ID can also be looked up via username. All stored data can be pushed back to the database.
 * </p>
 * @author Zachary Jones
 */
public class UserData {

    protected int account_ID;
    protected String username;
    protected String email;
    protected String hashedPassword;
    protected String firstName;
    protected String lastName;
    protected String displayName; // TODO: add any additional vars stored about users to this data object

    /**
     * Default constructor that loads an empty UserData object.
     */
    public UserData () {}

    /**
     * Overload constructor that takes a username string.
     * <p>loads a UserData object with data based on the results of {@link #setAccountIDbyUsername(String) setAccountIDbyUsername}. This constructor auto-executes {@link #getDataFromUser() getDataFromUser}.</p>
     * @param username Username string of the user record in the database.
     */
    public UserData (String username) {
        setAccountIDbyUsername(username);
        getDataFromUser();
    }

    /**
     * Overload constructor that takes an account_ID integer.
     * <p>Loads a UserData object with data based on the account ID provided. This constructor auto-executes {@link #getDataFromUser() getDataFromUser}.</p>
     * @param account_ID ID number of the user record in the database.
     */
    public UserData (int account_ID) {
        setAccount_ID (account_ID);
        getDataFromUser();


    }
    /**
     * Sets account_ID based on the provided username.
     * @param username Username string of the user record in the database.
     */
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

    /**
     * Pulls the data for the stored account_ID from the database
     * <p>Queries the database for the USER entry based on the stored account_ID. Returned attribute values are assigned to the instance variables of the UserData object.</p>
     */
    public void getDataFromUser () {
        String query = "SELECT * FROM USER WHERE accountID = '" + account_ID + "'";
        try {
            Connection connect = DatabaseConfigurator.getConnection();
            Statement sqlStatement = connect.createStatement();
            ResultSet result = sqlStatement.executeQuery(query);

            username = result.getString("username");
            email = result.getString("email");
            hashedPassword = result.getString("hashedPassword"); // TODO: double-check table column names and correct as necessary
            firstName = result.getString("firstName");
            lastName = result.getString("lastName");
            displayName = result.getString("displayName");
            return;
        } catch (SQLException e) {
            DatabaseConfigurator.displayException(e);
        }
    }

    /**
     * Pushes the data stored in the object to the database entry with corresponding account_ID
     * <p>Updates the USER entry with the corresponding account_ID with the values currently assigned to the instance variables of the UserData object.</p>
     */
    public void assignDataToUser () {
        String query = "UPDATE USER SET username='" + username + "',hashedPassword='" + hashedPassword + "',firstName='" + firstName + "',lastName='" + lastName + "',displayName='" + displayName + "'WHERE accountID='" + account_ID + "'";

        try {
            Connection connect = DatabaseConfigurator.getConnection();
            Statement sqlStatement = connect.createStatement();
            sqlStatement.executeQuery(query);
            return;
        } catch (SQLException e) {
            DatabaseConfigurator.displayException(e);
        }
    }

    /**
     * A supporting method to initialize the UserData data structure following a call of RegisteredUser.registerNewUser()
     * @param username
     * @param email
     */
    protected void constructNewUser (String username, String email) {
        setAccountIDbyUsername(username);
        String query = "SELECT * FROM USER WHERE accountID = '" + account_ID + "'";

        try {
            Connection connect = DatabaseConfigurator.getConnection();
            Statement sqlStatement = connect.createStatement();
            ResultSet result = sqlStatement.executeQuery(query);

            this.username = username;
            this.email = email;
            firstName = result.getString("firstName");
            lastName = result.getString("lastName");
            displayName = result.getString("displayName");
            return;
        } catch (SQLException e) {
            DatabaseConfigurator.displayException(e);
        }
    }

    /**
     * Returns the stored account_ID value.
     * @return int account_ID
     */
    public int getAccount_ID () {
        return account_ID;
    }

    /**
     * Sets the account_ID value.
     * @param account_ID int
     */
    public void setAccount_ID (int account_ID) {
        this.account_ID = account_ID;
        return;
    }

    /**
     * Returns the stored username value.
     * @return String username
     */
    public String getUsername () {
        return username;
    }

    /**
     * Sets the username value.
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
        return;
    }

    /**
     * Returns the email value.
     * @return String email
     */
    public String getEmail () {
        return email;
    }

    /**
     * Sets the email value.
     * @param email
     */
    public void setEmail (String email) {
        this.email = email;
        return;
    }

    /**
     * Returns the password hash value.
     * @return String hashedPassword
     */
    public String getHashedPassword () {
        return hashedPassword;
    }

    /**
     * Sets the password hash value.
     * @param hashedPassword String
     */
    public void setHashedPassword (String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return;
    }

    /**
     * Returns the user first name value.
     * @return String firstName
     */
    public String getFirstName () {
        return firstName;
    }

    /**
     * Sets the user first name value.
     * @param firstName String
     */
    public void setFirstName (String firstName) {
        this.firstName = firstName;
        return;
    }

    /**
     * Returns the user last name value.
     * @return String lastName
     */
    public String getLastName () {
        return lastName;
    }

    /**
     * Sets the user last name value.
     * @param lastName String
     */
    public void setLastName (String lastName) {
        this.lastName = lastName;
        return;
    }

    /**
     * Returns the user display name value.
     * @return String displayName
     */
    public String getDisplayName () {
        return displayName;
    }

    /**
     * Sets the user display name value.
     * @param displayName String
     */
    public void setDisplayName (String displayName) {
        this.displayName = displayName;
        return;
    }
}