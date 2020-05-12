package com.nwawsoft.pwng.model.language;

import com.nwawsoft.pwng.exceptions.UnknownLanguageException;

public class Languagizer {
    /**
     * Creates a Language (@see the Language enum) from a String.
     *
     * @param languageString a String representation of any supported language.
     * @return a corresponding entry from the Language enum.
     * @throws UnknownLanguageException if language is unknown or unsupported.
     */
    public static Language toLanguage(final String languageString) throws UnknownLanguageException {
        String workString = languageString;
        if (workString.contains("\n") || workString.contains("\r")) {
            workString = workString.replaceAll("\n", "");
            workString = workString.replaceAll("\r", "");
            workString = workString.trim();
        }
        if (workString.equals("ENGLISH") ||
                workString.equals("English")) {
            return Language.ENGLISH;
        } else if (workString.equals("GERMAN") ||
                workString.equals("DEUTSCH") ||
                workString.equals("German") ||
                workString.equals("Deutsch")) {
            return Language.GERMAN;
        } else {
            throw new UnknownLanguageException();
        }
    }
}
