package com.exam.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for hashing and verifying passwords using BCrypt.
 * Work factor is set to 12 for a strong yet performant hash.
 */
public class PasswordUtil {

    private static final int BCRYPT_ROUNDS = 12;

    /** Hashes a plaintext password and returns the BCrypt hash string. */
    public static String hashPassword(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt(BCRYPT_ROUNDS));
    }

    /** Checks whether the plaintext matches the stored BCrypt hash. */
    public static boolean checkPassword(String plaintext, String storedHash) {
        if (plaintext == null || storedHash == null) return false;
        return BCrypt.checkpw(plaintext, storedHash);
    }
}
