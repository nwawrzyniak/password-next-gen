package com.nwawsoft.pwng.model.characterset;

import com.nwawsoft.pwng.model.ResourceLoader;
import com.nwawsoft.pwng.model.language.Language;
import com.nwawsoft.util.datastructures.StringList;

import java.io.*;

/**
 * Provides functions to load a CharacterSet from a file in the /charsets/ resource directory.
 */
public class CharacterSetLoader {
    /**
     * Loads a CharacterSet from a .charset file in the /charsets/ resource directory.
     *
     * @param context any instantiated object whose Class has a reference to the resource directory (usually 'this').
     * @param charsetFileName the file name of the character set file without file extension.
     * @return the character set.
     */
    public static CharacterSet load(final Object context, final String charsetFileName) {
        // PART 1: PARSE FILE NAME
        String countryCode = "";
        String name;
        StringList suffixes = new StringList();
        String chars = "";
        // fetch countryCode
        for (Language cc : Language.values()) {
            if (charsetFileName.startsWith(cc.getCountryCode())) {
                countryCode = cc.getCountryCode().substring(0, 2);
                break;
            }
        }
        // fetch name
        if (!countryCode.equals("")) { // case: has countryCode
            if (charsetFileName.contains("-")) { // case: has countryCode and suffix(es)
                name = charsetFileName.substring(charsetFileName.indexOf("_") + 1, charsetFileName.indexOf("-"));
            } else { // case: has countryCode and no suffix
                name = charsetFileName.substring(charsetFileName.indexOf("_") + 1);
            }
        } else { // case: has no countryCode
            if (charsetFileName.contains("-")) { // case: has no countryCode but suffix(es)
                name = charsetFileName.substring(0, charsetFileName.indexOf("-"));
            } else { // case: has no countryCode and no suffix
                name = charsetFileName;
            }
        }
        // fetch suffixes
        if (charsetFileName.contains("-")) { // case: has suffix(es)
            String[] parts = charsetFileName.split("-");
            if (parts.length >= 2) {
                for (int i = 1; i < parts.length; i++) {
                    suffixes.append(parts[i]);
                }
            }
        }
        // PART 2: PARSE CONTENT
        // fetch chars
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getClass().getResourceAsStream
                    ("/charsets/" + charsetFileName + ".charset")));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (!currentLine.equals("") && !currentLine.equals("\n")) {
                    chars = currentLine;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // assemble everything
        return new CharacterSet(chars, name, countryCode, suffixes, charsetFileName);
    }

    /**
     * Wrapper for load(Object, String).
     * Requires no context since this can only be called via an instantiated CharacterSetLoader.
     *
     * @param charsetFileName the file name of the character set file without file extension.
     * @return the character set.
     */
    public CharacterSet load(final String charsetFileName) {
        return load(this, charsetFileName);
    }

    /**
     * Returns an array containing the names of all .charset files in the /charsets/ resource directory (without file
     * extension '.charset').
     *
     * @return an array containing the names of all .charset files. null if no files were found in /charsets/.
     */
    public static String[] getCharacterSetNames() {
        File[] files = ResourceLoader.getResourceFolderFiles("charsets");
        String[] names = null;
        if (files != null) {
            names = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                names[i] = files[i].getName().substring(0, files[i].getName().lastIndexOf("."));
            }
        }
        return names;
    }
}
