package nwawsoft.pwng.model.characterset;

import nwawsoft.pwng.exceptions.UnhandledCharacterSetException;

public class CharacterSetizer {
    public static CharacterSet toCharacterSet(final String characterSetString) throws UnhandledCharacterSetException {
        switch (characterSetString) {
            case "EASY_ENGLISH":
                return CharacterSet.EASY_ENGLISH;
            case "OPTIMIZED":
                return CharacterSet.OPTIMIZED;
            case "FULL":
                return CharacterSet.FULL;
            case "EASY_GERMAN":
                return CharacterSet.EASY_GERMAN;
            default:
                throw new UnhandledCharacterSetException();
        }
    }
}
