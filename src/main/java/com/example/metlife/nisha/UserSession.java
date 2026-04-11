package com.example.metlife.nisha;

public class UserSession {
    private static String currentUserId;
    private static String currentUserRole; // THE NEW MEMORY SLOT

    public static String getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(String userId) {
        currentUserId = userId;
    }

    public static String getCurrentRole() {
        return currentUserRole;
    }

    public static void setCurrentRole(String role) {
        currentUserRole = role;
    }

    public static void cleanSession() {
        currentUserId = null;
        currentUserRole = null;
    }
    public static void clearSession() {
        setCurrentUserId(null);
        setCurrentRole(null);
    }
}