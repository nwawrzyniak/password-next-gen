package com.nwawsoft.pwng.model.characterset;

import java.io.*;

/**
 * Provides functions to load a CharacterSet from a String in a file in the /charsets/ directory.
 */
public class CharacterSetLoader {
    /**
     * ToDo: This line of doc
     *
     * @param context any instantiated object whose Class has a reference to the resource directory.
     * @param charsetFileName the file name of the character set file without file extension.
     * @return the character set as a String.
     */
    public static String load(final Object context, final String charsetFileName) {
        String cs = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getClass().getResourceAsStream
                    ("/charsets/" + charsetFileName + ".charset")));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (!currentLine.equals("") && !currentLine.equals("\n")) {
                    cs = currentLine;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cs;
    }

    /**
     * Wrapper for load(Object, String).
     * Requires no context since this can only be called via an instantiated CharacterSetLoader.
     * @see com.nwawsoft.pwng.model.characterset load(Object, String).
     *
     * @param charsetFileName the file name of the character set file without file extension.
     * @return the character set as a String.
     */
    public String load(final String charsetFileName) {
        return load(this, charsetFileName);
    }
}
