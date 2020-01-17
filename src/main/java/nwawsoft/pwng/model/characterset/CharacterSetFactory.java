package nwawsoft.pwng.model.characterset;

import static nwawsoft.util.MutatedVowels.*;

/**
 * This is a factory class. To understand this class read the documentation of the enum CharacterSet.
 */
public class CharacterSetFactory {
    // general constants
    private static final int AMOUNT_ONE_TO_NINE = 9;
    private static final int AMOUNT_ZERO_TO_NINE = 10;
    private static final int AMOUNT_CAPITALS_A_TO_H = 8;
    private static final int AMOUNT_CAPITALS_J_TO_N = 5;
    private static final int AMOUNT_CAPITALS_P_TO_Z = 11;
    private static final int AMOUNT_SMALLS_A_TO_K = 11;
    private static final int AMOUNT_SMALLS_M_TO_Z = 14;
    private static final int AMOUNT_ALPHABET = 26;
    private static final int AMOUNT_SET_EASY_GERMAN = 44;
    private static final int AMOUNT_SET_EASY_ENGLISH = 43;
    private static final int AMOUNT_SET_FULL = 95;
    private static final int AMOUNT_SET_OPTIMIZED = 86;

    private static final int ASCII_ZERO = 48;
    private static final int ASCII_ONE = 49;
    private static final int ASCII_CAPITAL_A = 65;
    private static final int ASCII_CAPITAL_J = 74;
    private static final int ASCII_CAPITAL_P = 80;
    private static final int ASCII_SMALL_A = 97;
    private static final int ASCII_SMALL_M = 109;

    // set specific constants
    private static final int FULL_DIGIT_OFFSET = 33;
    private static final int FULL_LETTER_OFFSET_1 = 43;
    private static final int FULL_LETTER_OFFSET_2 = 69;

    private static final int EASY_GERMAN_DIGIT_OFFSET = 6;
    private static final int EASY_GERMAN_LETTER_OFFSET_1 = 15;
    private static final int EASY_GERMAN_LETTER_OFFSET_2 = 26;

    private static final int EASY_ENGLISH_DIGIT_OFFSET = 9;
    private static final int EASY_ENGLISH_LETTER_OFFSET_1 = 18;
    private static final int EASY_ENGLISH_LETTER_OFFSET_2 = 29;

    private static final int OPTIMIZED_DIGIT_OFFSET = 28;
    private static final int OPTIMIZED_LETTER_OFFSET_1 = 37;
    private static final int OPTIMIZED_LETTER_OFFSET_2 = 45;
    private static final int OPTIMIZED_LETTER_OFFSET_3 = 50;
    private static final int OPTIMIZED_LETTER_OFFSET_4 = 61;
    private static final int OPTIMIZED_LETTER_OFFSET_5 = 72;

    /**
     * Creates a char array from a CharacterSet.
     *
     * @return a char array containing all characters that are allowed during password generation.
     */
    public char[] buildCharacterSet(final CharacterSet cs) {
        char[] set = null;
        switch (cs) {
            case FULL:
                set = new char[AMOUNT_SET_FULL];
                set[0] = ' ';
                set[1] = '!';
                set[2] = '"';
                set[3] = '#';
                set[4] = '$';
                set[5] = '%';
                set[6] = '&';
                set[7] = '\'';
                set[8] = '(';
                set[9] = ')';
                set[10] = '*';
                set[11] = '+';
                set[12] = ',';
                set[13] = '-';
                set[14] = '.';
                set[15] = '/';
                set[16] = ':';
                set[17] = ';';
                set[18] = '<';
                set[19] = '=';
                set[20] = '>';
                set[21] = '?';
                set[22] = '@';
                set[23] = '[';
                set[24] = '\\';
                set[25] = ']';
                set[26] = '^';
                set[27] = '_';
                set[28] = '`';
                set[29] = '{';
                set[30] = '|';
                set[31] = '}';
                set[32] = '~';
                for (int i = 0; i < AMOUNT_ZERO_TO_NINE; i++) {
                    set[FULL_DIGIT_OFFSET + i] = (char) (ASCII_ZERO + i);
                }
                for (int i = 0; i < AMOUNT_ALPHABET; i++) {
                    set[FULL_LETTER_OFFSET_1 + i] = (char) (ASCII_CAPITAL_A + i);
                }
                for (int i = 0; i < AMOUNT_ALPHABET; i++) {
                    set[FULL_LETTER_OFFSET_2 + i] = (char) (ASCII_SMALL_A + i);
                }
                break;
            case EASY_GERMAN:
                set = new char[AMOUNT_SET_EASY_GERMAN];
                set[0] = '+';
                set[1] = '#';
                set[2] = '-';
                set[3] = '.';
                set[4] = ',';
                set[5] = '<';
                for (int i = 0; i < AMOUNT_ONE_TO_NINE; i++) {
                    set[EASY_GERMAN_DIGIT_OFFSET + i] = (char) (ASCII_ONE + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_A_TO_K; i++) {
                    set[EASY_GERMAN_LETTER_OFFSET_1 + i] = (char) (ASCII_SMALL_A + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_M_TO_Z; i++) {
                    set[EASY_GERMAN_LETTER_OFFSET_2 + i] = (char) (ASCII_SMALL_M + i);
                }
                set[40] = ae;
                set[41] = oe;
                set[42] = ue;
                set[43] = ss;
                break;
            case EASY_ENGLISH:
                set = new char[AMOUNT_SET_EASY_ENGLISH];
                set[0] = '-';
                set[1] = '=';
                set[2] = '\\';
                set[3] = '[';
                set[4] = ']';
                set[5] = '\'';
                set[6] = '.';
                set[7] = ';';
                set[8] = '/';
                for (int i = 0; i < AMOUNT_ONE_TO_NINE; i++) {
                    set[EASY_ENGLISH_DIGIT_OFFSET + i] = (char) (ASCII_ONE + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_A_TO_K; i++) {
                    set[EASY_ENGLISH_LETTER_OFFSET_1 + i] = (char) (ASCII_SMALL_A + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_M_TO_Z; i++) {
                    set[EASY_ENGLISH_LETTER_OFFSET_2 + i] = (char) (ASCII_SMALL_M + i);
                }
                break;
            case OPTIMIZED:
                set = new char[AMOUNT_SET_OPTIMIZED];
                set[0] = '~';
                set[1] = '!';
                set[2] = '"';
                set[3] = '#';
                set[4] = '$';
                set[5] = '%';
                set[6] = '&';
                set[7] = '{';
                set[8] = '(';
                set[9] = ')';
                set[10] = '*';
                set[11] = '+';
                set[12] = ',';
                set[13] = '-';
                set[14] = '.';
                set[15] = '/';
                set[16] = ':';
                set[17] = ';';
                set[18] = '<';
                set[19] = '=';
                set[20] = '>';
                set[21] = '?';
                set[22] = '@';
                set[23] = '[';
                set[24] = '|';
                set[25] = ']';
                set[26] = '}';
                set[27] = '_';
                for (int i = 0; i < AMOUNT_ONE_TO_NINE; i++) {
                    set[OPTIMIZED_DIGIT_OFFSET + i] = (char) (ASCII_ONE + i);
                }
                for (int i = 0; i < AMOUNT_CAPITALS_A_TO_H; i++) {
                    set[OPTIMIZED_LETTER_OFFSET_1 + i] = (char) (ASCII_CAPITAL_A + i);
                }
                for (int i = 0; i < AMOUNT_CAPITALS_J_TO_N; i++) {
                    set[OPTIMIZED_LETTER_OFFSET_2 + i] = (char) (ASCII_CAPITAL_J + i);
                }
                for (int i = 0; i < AMOUNT_CAPITALS_P_TO_Z; i++) {
                    set[OPTIMIZED_LETTER_OFFSET_3 + i] = (char) (ASCII_CAPITAL_P + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_A_TO_K; i++) {
                    set[OPTIMIZED_LETTER_OFFSET_4 + i] = (char) (ASCII_SMALL_A + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_M_TO_Z; i++) {
                    set[OPTIMIZED_LETTER_OFFSET_5 + i] = (char) (ASCII_SMALL_M + i);
                }
                break;
        }
        return set;
    }
}
