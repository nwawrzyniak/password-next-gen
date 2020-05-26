package com.nwawsoft.pwng.model.characterset;

import com.nwawsoft.util.datastructures.StringList;

/**
 * ToDo: Doc
 */
public class CharacterSet {
    private String chars;
    private String name;
    private String countryCode;
    private StringList suffixes;

    /**
     * Wrapper for CharacterSet(String, String).
     *
     * @param chars
     */
    public CharacterSet(final String chars) {
        new CharacterSet(chars, "UNNAMED", null, null);
    }

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
