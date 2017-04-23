package com.jobdirectory.DataObjects;

/**
 * Created by nam on 22-Apr-17.
 */

public class CurrentLanguage {
    private static String currentLanguage = "en";

    public static String getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(String currentLanguage) {
        CurrentLanguage.currentLanguage = currentLanguage;
    }
}
