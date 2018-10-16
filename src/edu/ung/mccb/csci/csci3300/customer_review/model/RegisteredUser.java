package edu.ung.mccb.csci.csci3300.customer_review.model.RegisteredUser;

import edu.ung.mccb.csci.csci3300.customer_review.model.DatabaseConfigurator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class RegisteredUser { // TODO: refactor this class with narrowed scope
    // This class should cover user registration and login tasks only.
    // Supporting methods like password hashing will be required elsewhere for user editing.

    private static final char[] saltArray = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                             'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','t','y',
                                             '1','2','3','4','5','6','7','8','9','0'};

    // takes new user information and
    public int registerNewUser (String username, String email, String password)
    {
        int affectedRow = 0;
        String query = "INSERT INTO USER" + "(username, email, password, salt)" + "values(?,?,?,?)";

        String passwordSalt = generateSalt(saltArray);
        String hashedPassword = hashPassword(password, passwordSalt);

        try (Connection connect = DatabaseConfigurator.getConnection();
             PreparedStatement sqlStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            sqlStatement.setString(1, username);
            sqlStatement.setString(2, email);
            sqlStatement.setString(3, hashedPassword);
            sqlStatement.setString(4, passwordSalt);

            affectedRow = sqlStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR: operation failed due to SQL exception:\n");
        }
        return affectedRow;
    }

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

    private String generateSalt (char[] charArray) {
        SecureRandom random = new SecureRandom();
        char[] salt = new char[32];
        for (int index = 0; index < 32; index++)
            salt[index] = charArray[random.nextInt(charArray.length)];
        return salt.toString();
    }
}