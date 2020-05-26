package com.nwawsoft.pwng.model.characterset;

/**
 * ToDo: Doc
 */
public class Suffixes {
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
