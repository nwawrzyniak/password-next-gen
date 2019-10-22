package nwawsoft.pwng.model;

import nwawsoft.util.CharArrayFunctions;
import nwawsoft.util.MutatedVowels;

public class CharacterSetFactory {
    private static final int AMOUNT_ONE_TO_NINE = 9;
    private static final int AMOUNT_ZERO_TO_NINE = 10;
    private static final int AMOUNT_CAPITALS_A_TO_I = 8;
    private static final int AMOUNT_CAPITALS_J_TO_N = 5;
    private static final int AMOUNT_CAPITALS_P_TO_Z = 11;
    private static final int AMOUNT_SMALLS_A_TO_K = 11;
    private static final int AMOUNT_SMALLS_M_TO_Z = 14;
    private static final int AMOUNT_SET_EASY_GERMAN = 45;
    private static final int AMOUNT_SET_EASY_ENGLISH = 43;

    private static final int ASCII_ZERO = 48;
    private static final int ASCII_ONE = 49;
    private static final int ASCII_CAPITAL_A = 65;
    private static final int ASCII_CAPITAL_H = 72;
    private static final int ASCII_CAPITAL_J = 74;
    private static final int ASCII_CAPITAL_N = 78;
    private static final int ASCII_CAPITAL_P = 80;
    private static final int ASCII_CAPITAL_Z = 90;
    private static final int ASCII_SMALL_A = 97;
    private static final int ASCII_SMALL_K = 107;
    private static final int ASCII_SMALL_M = 109;
    private static final int ASCII_SMALL_Z = 122;

    private static final int EASY_GERMAN_DIGIT_OFFSET = 7;
    private static final int EASY_GERMAN_LETTER_OFFSET_1 = 16;
    private static final int EASY_GERMAN_LETTER_OFFSET_2 = 27;

    private static final int EASY_ENGLISH_DIGIT_OFFSET = 9;
    private static final int EASY_ENGLISH_LETTER_OFFSET_1 = 18;
    private static final int EASY_ENGLISH_LETTER_OFFSET_2 = 29;

    private final CharacterSet cs;

    public CharacterSetFactory(CharacterSet cs) {
        this.cs = cs;
    }

    public char[] getCharacterSet() {
        char[] set = null;
        switch (cs) {
            case OLD:
                set = new char[33];
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
                // ToDo a-z, A-Z, 0-9
                break;
            case EASY_GERMAN: // contains the easiest to reach keys on a QWERTZ keyboard. These are +, #, -, ., ,,
                // <, +, the digits from 1 to 9 (no 0 because it can look like an O), the small letters from a to z
                // without l (because it can look like a one or an upper case i), the mutated vowels ä, ö, ü and the ß.
                set = new char[AMOUNT_SET_EASY_GERMAN];
                set[0] = '+';
                set[1] = '#';
                set[2] = '-';
                set[3] = '.';
                set[4] = ',';
                set[5] = '<';
                set[6] = '+';
                for (int i = 0; i < AMOUNT_ONE_TO_NINE; i++) {
                    set[EASY_GERMAN_DIGIT_OFFSET + i] = (char) (ASCII_ONE + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_A_TO_K; i++) {
                    set[EASY_GERMAN_LETTER_OFFSET_1 + i] = (char) (ASCII_SMALL_A + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_M_TO_Z; i++) {
                    set[EASY_GERMAN_LETTER_OFFSET_2 + i] = (char) (ASCII_SMALL_M + i);
                }
                set[41] = MutatedVowels.ae;
                set[42] = MutatedVowels.oe;
                set[43] = MutatedVowels.ue;
                set[44] = MutatedVowels.ss;
                break;
            case EASY_ENGLISH: // contains the easiest to reach keys on a QWERTY keyboard. These are +, #, -, ., ,,
                // <, +, the digits from 1 to 9 (no 0 because it can look like an O) and the small letters from a to z
                // without l (because it can look like a one or an upper case i).
                // ToDO: check special characters
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
                CharArrayFunctions.print(set, true);
                break;
            case FULL:
                set = new char[1000];
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
                // ToDo
                break;
        }
        return set;
    }
}
