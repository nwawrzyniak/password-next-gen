package com.nwawsoft.pwng.model;

import com.nwawsoft.pwng.exceptions.UnknownLanguageException;
import com.nwawsoft.pwng.model.characterset.CharacterSet;
import com.nwawsoft.pwng.model.characterset.CharacterSetLoader;
import com.nwawsoft.pwng.model.characterset.DefaultCharacterSet;
import com.nwawsoft.pwng.model.language.Language;
import com.nwawsoft.pwng.model.language.Languagizer;

import java.io.*;

/**
 * Manages the saving and loading of settings.
 */
public class Settings {
    public static final int GENERATOR_MAX_TRIES_UNTIL_LOWER_CRITERIA = 5;
    public static final int GENERATOR_MIN_PASSWORD_LENGTH = 14;
    public static final int GENERATOR_BONUS_MAX_LENGTH = 4;

    private Language l;
    private CharacterSet cs;
    private boolean showPresetMask;

    // ToDo: Doc
    public Settings() {
        if (!configFileFound() || !configFileValid()) {
            createDefaults();
        }
        load();
    }

    public static void createDefaults() {
        try {
            File d = new File(System.getProperty("user.home") + "/.pwng");
            if (!d.exists()) {
                if (!d.mkdir()) {
                    throw new IOException();
                }
            }
            File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("LANG=English\n");
            bw.write("CHARSET=en_easy\n");
            bw.write("SHOW_PRESET_MASK=true\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Saves the current settings to a file "~/.pwng/settings.ini".
     *
     * @param language the specified language.
     * @param cs       the specified character set.
     */
    public static void save(final String language, final CharacterSet cs) {
        try {
            File d = new File(System.getProperty("user.home") + "/.pwng");
            if (!d.exists()) {
                if (!d.mkdir()) {
                    throw new IOException();
                }
            }
            File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("LANG=" + language + "\n");
            bw.write("CHARSET=" + cs.getFileName() + "\n");
            bw.write("SHOW_PRESET_MASK=" + false + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns whether a config file is found at the default path ("~/.pwng/settings.ini").
     *
     * @return true if file was found. Else false.
     */
    public static boolean configFileFound() {
        File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
        return f.exists();
    }

    // ToDo: Doc
    public static boolean configFileValid() {
        // ToDo: Rewrite
        // aka ToDo: Validate 4 real.
        return configFileFound();
    }

    /**
     * Sets the software defaults to the English language, a character set containing [a..z],[A..Z],[0..9]
     * and showPresetMask true.
     */
    private void setDefaults() {
        if (l == null) {
            l = Language.ENGLISH;
        }
        if (cs == null) {
            cs = new DefaultCharacterSet();
        }
        showPresetMask = true;
    }

    /**
     * Loads the settings from "~/.pwng/settings.ini".
     */
    public void load() {
        try {
            File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.startsWith("LANG")) {
                    l = Languagizer.toLanguage(currentLine.substring(currentLine.lastIndexOf("=") + 1));
                } else if (currentLine.startsWith("CHARSET")) {
                    cs = CharacterSetLoader.load(this, currentLine.substring(currentLine.lastIndexOf("=") + 1));
                } else if (currentLine.startsWith("SHOW_PRESET_MASK")) {
                    showPresetMask = Boolean.parseBoolean(currentLine.substring(currentLine.lastIndexOf("=") + 1));
                }
            }
        } catch (IOException | UnknownLanguageException | NullPointerException e) {
            System.err.println("Error on Settings load:");
            e.printStackTrace();
            System.err.println("Falling back to default settings...");
            setDefaults();
        }
    }

    public Language getLanguage() {
        return l;
    }

    public CharacterSet getCharacterSet() {
        return cs;
    }

    public boolean getShowPresetMask() {
        return showPresetMask;
    }
}
