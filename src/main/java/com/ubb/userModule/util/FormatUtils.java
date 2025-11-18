package com.ubb.userModule.util;

public class FormatUtils {
    public static boolean isNullOrEmpty(String string) {
        if (string == null) {
            return true;
        }
        return string.isEmpty();
    }
}
