package com.nwawsoft.pwng.model;

import com.nwawsoft.pwng.exceptions.UnhandledCharacterSetException;
import com.nwawsoft.pwng.exceptions.UnknownLanguageException;
import com.nwawsoft.pwng.model.language.Language;
import com.nwawsoft.pwng.model.language.Languagizer;
import com.nwawsoft.pwng.model.characterset.CharacterSet;

import java.io.*;

/**
 * Manages the saving and loading of setting.
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

    // ToDo: Doc
    public static void save(final String language, final String charSet) {
        try {
            File d = new File(System.getProperty("user.home") + "/.pwng");
            boolean dirCreated = d.mkdir();
            File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("LANG=" + language + "\n");
            bw.write("CHARSET=" + charSet + "\n");
            bw.write("SHOW_PRESET_MASK=" + false + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns whether a config file is found at the default path ($user.home$/.pwng/settings.ini).
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
        return true;
    }

    private void setDefaults() {
        if (l == null) {
            l = Language.ENGLISH;
        }
        // ToDo: Load charset
    }

    // ToDo: Doc
    public void load() {
        try {
            File f = new File(System.getProperty("user.home") + "/.pwng/settings.ini");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.startsWith("LANG")) {
                    l = Languagizer.toLanguage(currentLine.substring(currentLine.lastIndexOf("=") + 1));
                    System.out.println(currentLine.substring(currentLine.lastIndexOf("=") + 1));
                } else if (currentLine.startsWith("CHARSET")) {
                    // ToDo: load character set
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
