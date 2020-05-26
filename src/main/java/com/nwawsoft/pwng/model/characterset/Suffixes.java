package com.nwawsoft.pwng.model.characterset;

/**
 * Supplies functions that provide the suffix functionality used in the names of .charset files.
 */
public class Suffixes {
    /**
     * Returns whether the specified CharacterSet conforms to the '-noconfuse' specifications.
     * This means not including any of the following characters:
     * 0 (zero), O (upper 'o'), 1 (one), I (upper 'i'), l (lower 'L'), | (pipe) and ' ' (the white space).
     *
     * @param cs any CharacterSet.
     * @return true if cs conforms with the '-noconfuse' specifications. Else false.
     */
    public static boolean conformsNoConfuse(final CharacterSet cs) {
        String chars = cs.getChars();
        return !chars.contains("0") &&
                !chars.contains("1") &&
                !chars.contains("I") &&
                !chars.contains("O") &&
                !chars.contains("l") &&
                !chars.contains("|") &&
                !chars.contains(" ");
    }

    /**
     * Makes the specified CharacterSet conform with the '-noconfuse' specifications.
     * This means it will no longer including any of the following characters:
     * 0 (zero), O (upper 'o'), 1 (one), I (upper 'i'), l (lower 'L'), | (pipe) and ' ' (the white space).
     *
     * @param cs any CharacterSet.
     * @return a '-noconfuse' conforming charset.
     */
    public static String forceNoConfuse(final CharacterSet cs) {
        if (conformsNoConfuse(cs)) {
            return cs.getChars();
        } else {
            String out = cs.getChars();
            out = out.replaceAll("0", "");
            out = out.replaceAll("1", "");
            out = out.replaceAll("I", "");
            out = out.replaceAll("O", "");
            out = out.replaceAll("l", "");
            out = out.replaceAll("\\|", "");
            out = out.replaceAll(" ", "");
            return out;
        }
    }
}
