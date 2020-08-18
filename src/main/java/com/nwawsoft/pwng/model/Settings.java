package com.nwawsoft.pwng.model;

import com.nwawsoft.pwng.exceptions.UnknownLanguageException;
import com.nwawsoft.pwng.model.characterset.CharacterSetLoader;
import com.nwawsoft.pwng.model.language.Language;
import com.nwawsoft.pwng.model.language.Languagizer;
import com.nwawsoft.pwng.model.characterset.CharacterSet;
import com.nwawsoft.util.jar.ProtocolFunctions;

import java.io.*;

/**
 * Manages the saving and loading of settings.
 */
public class Settings {
    private Language l;
    private CharacterSet cs;
    private boolean showPresetMask;

    // ToDo: Doc
    public Settings() {
        if (configFileFound()) {
            if (configFileValid()) {
                load();
            }
        } else {
            setDefaults();
        }
    }

    /**
     * Saves the current settings to a file "~/.pwng/settings.ini".
     *
     * @param language the specified language.
     * @param cs the specified character set.
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
        return configFileFound();
    }

    /**
     * Sets the software defaults to the English language, a character set "Standard" containing [a..z],[A..Z],[0..9]
     * and showPresetMask true.
     */
    private void setDefaults() {
        if (l == null) {
            l = Language.ENGLISH;
        }
        if (cs == null) {
            cs = new CharacterSet("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
                    "Standard", null, null, null);
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
                    cs = CharacterSetLoader.load(this, currentLine.substring(currentLine.lastIndexOf("=") + 1),
                            ProtocolFunctions.isInJar(this));
                } else if (currentLine.startsWith("SHOW_PRESET_MASK")) {
                    showPresetMask = Boolean.parseBoolean(currentLine.substring(currentLine.lastIndexOf("=") + 1));
                }
            }
        } catch (IOException | UnknownLanguageException e) {
            e.printStackTrace();
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
