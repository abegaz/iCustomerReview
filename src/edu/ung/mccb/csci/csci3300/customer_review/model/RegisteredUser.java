package edu.ung.mccb.csci.csci3300.customer_review.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.sql.*;

/**
 * Class that handles user registration and login.
 * <p>
 *     Provided methods register users, hash and salt passwords, and handle login events. This class is a subclass of the object UserData.
 * </p>
 * @see edu.ung.mccb.csci.csci3300.customer_review.model.UserData
 * @author Zachary Jones
 */
public class RegisteredUser extends UserData {
    // This class should cover user registration and login tasks only.
    // Supporting methods like password hashing will be required elsewhere for user editing.

    public static final char[] saltArray = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','t','y',
            '1','2','3','4','5','6','7','8','9','0'};

    /**
     * Creates a new USER entry in the database.
     * <p>
     *     Automatically hashes and salts password, passing both hashed password and the generated salt to the database. SQL injection safe. This method automatically loads available data into a UserData object.
     * </p>
     * @param username String
     * @param email String
     * @param password String
     * @throws SQLException
     */
    public void registerNewUser (String username, String email, String password) throws SQLException
    {
        String query = "INSERT INTO USER" + "(username, email, password, salt)" + "values(?,?,?,?)";

        String passwordSalt = generateSalt();
        super.hashedPassword = hashPassword(password, passwordSalt);

        Connection connect = DatabaseConfigurator.getConnection();
        PreparedStatement sqlStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        sqlStatement.setString(1, username);
        sqlStatement.setString(2, email);
        sqlStatement.setString(3, super.hashedPassword);
        sqlStatement.setString(4, passwordSalt);
        sqlStatement.executeUpdate();

        super.constructNewUser(username, email);
        return;
    }

    /**
     * Hashes password via SHA-256.
     * @param password String
     * @param salt String
     * @return String hashedPassword
     */
    private String hashPassword (String password, String salt) // TODO: relocate to separate class
    {
        String hashedPassword = null;
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            digester.update(password.getBytes(StandardCharsets.UTF_16));
            digester.update(salt.getBytes(StandardCharsets.UTF_16));
            byte[] hashBytes = digester.digest();

            StringBuilder assembler = new StringBuilder();
            for (int i = 0; i < hashBytes.length; i++)
                assembler.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));

            hashedPassword = assembler.toString();
            } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
            return hashedPassword;
    }

    /**
     * Alias with automatic salting.
     * <p>
     *     This method automatically calls {@link #generateSalt() generateSalt} but the salt value is not returned in any form.
     * </p>
     * @param password String
     * @return String hashedPassword
     */
    private String hashPassword (String password) {
        return hashPassword(password, generateSalt());
    }

    /**
     * Generates a salt value based on specified character array and length.
     * @param charArray char[] array of characters to generate salt from
     * @param saltLength int length of salt to generate
     * @return String salt
     */
    private String generateSalt (char[] charArray, int saltLength) {
        SecureRandom random = new SecureRandom();

        char[] salt = new char[saltLength];
        for (int index = 0; index < salt.length; index++)
            salt[index] = charArray[random.nextInt(charArray.length)];
        return salt.toString();
    }

    /**
     * Alias that sets character array to alphanumeric.
     * @param saltLength int
     * @return String salt
     */
    private String generateSalt (int saltLength) {
        return generateSalt(saltArray, saltLength);
    }

    /**
     * Alias that sets salt length to 32 characters.
     * @param charArray char[]
     * @return String salt
     */
    private String generateSalt (char[] charArray) {
        return generateSalt(charArray, 32);
    }

    /**
     * Alias that sets the character array to alphanumeric and salt length to 32 characters.
     * @return String salt
     */
    private String generateSalt () {
        return generateSalt (saltArray, 32);
    }
}