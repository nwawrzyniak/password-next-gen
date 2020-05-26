package com.nwawsoft.pwng.model.characterset;

import com.nwawsoft.util.datastructures.StringList;

/**
 * Represents a CharacterSet.
 * CharacterSets have a list of characters they contain and a name and may have a countryCode and suffixes.
 */
public class CharacterSet {
    private String chars;
    private String name;
    private String countryCode;
    private StringList suffixes;

    /**
     * Minimal CharacterSet.
     * Basically a wrapper for CharacterSet(String, String, String, String[]) for testing and backup loading.
     *
     * @param chars a String containing all allowed characters for this set (may not be null, empty or only one
     *              character long).
     */
    public CharacterSet(final String chars) {
        new CharacterSet(chars, "UNNAMED", null, null);
    }

    /**
     * Instantiates a new CharacterSet.
     *
     * @param chars a String containing all allowed characters for this set (may not be null, empty or only one
     *              character long).
     * @param name the name of the CharacterSet (may not be null or empty).
     * @param countryCode the countryCode of the CharacterSet (may be null or empty).
     * @param suffixes the suffix(es) of the CharacterSet (may be null or empty).
     */
    public CharacterSet(final String chars, final String name, final String countryCode, final StringList suffixes) {
        this.chars = chars;
        this.name = name;
        this.countryCode = countryCode;
        this.suffixes = suffixes;
    }

    public String getChars() {
        return chars;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public StringList getSuffixes() {
        return suffixes;
    }
}
