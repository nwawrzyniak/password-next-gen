package com.nwawsoft.pwng.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UnsafePasswordSaver {
    public static void save(final String fileName, final String password) {
        try {
            File d = new File(System.getProperty("user.home") + "/.pwng");
            if (!d.exists()) {
                if (!d.mkdir()) {
                    throw new IOException();
                }
            }
            File f = new File(System.getProperty("user.home") + "/.pwng/" + fileName + ".txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(password);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFileName(String input) {
        for(int i = 0; i < input.length(); ++i) {
            char currentChar = input.charAt(i);
            if ((currentChar < 'A' || currentChar > 'Z') &&
                    (currentChar < 'a' || currentChar > 'z') &&
                    (currentChar < '0' || currentChar > '9') &&
                    currentChar != '.' &&
                    currentChar != '_' &&
                    currentChar != '-' &&
                    !containsOnlyDots(input)) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsOnlyDots(String string) {
        if (string != null) {
            for(int i = 0; i < string.length(); ++i) {
                if (string.charAt(i) != '.') {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
