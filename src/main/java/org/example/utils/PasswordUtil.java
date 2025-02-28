package org.example.utils;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // Hash password using BCrypt
    public static String getHashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // 12 rounds (cost factor)
    }

    // Verify password against stored hash
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
