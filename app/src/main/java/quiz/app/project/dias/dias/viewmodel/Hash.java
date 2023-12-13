/**
 * Utility class for hashing passwords using the SHA-256 algorithm.
 */
package quiz.app.project.dias.dias.viewmodel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    /**
     * Hash the provided password using SHA-256 algorithm.
     *
     * @param password The password to be hashed.
     * @return The hashed password as a hexadecimal string.
     */
    public static String hashPassword(String password) {
        try {
            // Create an instance of the SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convert the password string to bytes
            byte[] passwordBytes = password.getBytes();

            // Compute the hash of the password bytes
            byte[] hashedBytes = digest.digest(passwordBytes);

            // Convert the hashed bytes to a hexadecimal representation
            StringBuilder builder = new StringBuilder();
            for (byte b : hashedBytes) {
                builder.append(String.format("%02x", b));
            }

            // Return the hashed password as a string
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}