package nwawsoft.pwng.model.characterset;

/**
 * Enum
 */
public enum CharacterSet {
    /**
     * Contains the most characters out of all sets. These are all commonly used special characters, the digits from 0
     * to 9, all lower case letters and all upper case letters.
     */
    FULL,

    /**
     * Contains the most easily typeable characters on a QWERTZ keyboard. These are + # - . , < + the digits from 1 to
     * 9, the small letters from a to z without l, the mutated vowels ä, ö, ü and the ß.
     */
    EASY_GERMAN,

    /**
     * Contains the most easily typeable characters on a QWERTY keyboard. These are - = \ [ ] ' . , / the digits from 1
     * to 9 and the small letters from a to z without l.
     */
    EASY_ENGLISH,

    /**
     * Contains easy to type characters that are well-distinguishable. These are all commonly used special characters
     * [without ^ ´ ` \ ' and the white space], the digits from 1 to 9, lower case letter without l and upper case
     * letters without I and O.
     */
    OPTIMIZED
}
