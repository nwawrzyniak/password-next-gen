package com.nwawsoft.pwng.model.language;

import com.nwawsoft.pwng.exceptions.UnknownLanguageException;

public class Languagizer {
    /**
     * Creates a Language (@see the Language enum) from a String.
     *
     * @param language a String representation of any supported language.
     * @return a corresponding entry from the Language enum.
     * @throws UnknownLanguageException if language is unknown or unsupported.
     */
    public static Language toLanguage(final String language) throws UnknownLanguageException {
        String workString = language.toLowerCase();
        if (workString.contains("\n") || workString.contains("\r")) {
            workString = workString.replaceAll("\n", "");
            workString = workString.replaceAll("\r", "");
        }
        workString = workString.trim();
        if (workString.equals("english") ||
                workString.equals("en")) {
            return Language.ENGLISH;
        } else if (workString.equals("deutsch") ||
                workString.equals("german") ||
                workString.equals("de")) {
            return Language.GERMAN;
        } else {
            throw new UnknownLanguageException();
        }
    }
}
