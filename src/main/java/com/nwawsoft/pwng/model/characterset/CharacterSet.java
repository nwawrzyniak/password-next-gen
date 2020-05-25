package com.nwawsoft.pwng.model.characterset;

/**
 * ToDo: Doc
 */
public class CharacterSet {
    private String set;
    private String name;

    /**
     * Wrapper for CharacterSet(String, String).
     *
     * @param set
     */
    public CharacterSet(final String set) {
        new CharacterSet(set, "");
    }

    public CharacterSet(final String set, final String name) {
        this.set = set;
        this.name = name;
    }

    public String getSet() {
        return set;
    }

    public String getName() {
        return name;
    }
}
