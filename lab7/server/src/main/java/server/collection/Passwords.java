package server.database;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.security.MessageDigest;

/**
 * A utility class to hash passwords and check passwords vs hashed values. It uses a combination of hashing and unique
 * salt. The algorithm used is PBKDF2WithHmacSHA1 which, although not the best for hashing password (vs. bcrypt) is
 * still considered robust and <a href="https://security.stackexchange.com/a/6415/12614"> recommended by NIST </a>.
 * The hashed value has 256 bits.
 */
public class Passwords {
    private static final Random RANDOM = new SecureRandom();

    /**
     * Static utility class
     */
    private Passwords() { }

    /**
     * Returns a random salt to be used to hash a password.
     *
     * @return a 16 bytes random salt
     */
    public static byte[] getSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static String bytesToString(byte[] bytes) {
        BigInteger no = new BigInteger(1, bytes);
        return no.toString(16);
    }

    /**
     * Returns a salted and hashed password using the provided hash.
     * @param password the password to be hashed
     * @param salt     a 16 bytes salt, ideally obtained with the getNextSalt method
     * @return the hashed password with a pinch of salt
     */
    public static byte[] hash(byte[] password, byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD2");

            byte[] saltedPassword = new byte[password.length + salt.length];
            System.arraycopy(password, 0, saltedPassword, 0, password.length);
            System.arraycopy(salt, 0, saltedPassword, password.length, salt.length);

            byte[] passwordDigest =  messageDigest.digest(saltedPassword);
            return passwordDigest;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage());
        }
    }

    /**
     * Returns true if the given password and salt match the hashed value, false otherwise.<br>
     * @param password     the password to check
     * @param salt         the salt used to hash the password
     * @param expectedHash the expected hashed value of the password
     * @return true if the given password and salt match the hashed value, false otherwise
     */
    public static boolean isExpectedPassword(byte[] password, byte[] salt, byte[] expectedHash) {
        byte[] passwordHash = hash(password, salt);
        if (passwordHash.length != expectedHash.length) {
            return false;
        }
        for (int i = 0; i < passwordHash.length; i++) {
            if (passwordHash[i] != expectedHash[i]) {
                return false;
            }
        }
        return true;
    }
}