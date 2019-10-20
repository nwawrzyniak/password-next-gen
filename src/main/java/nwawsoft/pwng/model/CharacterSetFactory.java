package nwawsoft.pwng.model;

public class CharacterSetFactory {
    private static final int AMOUNT_ONE_TO_NINE = 9;
    private static final int AMOUNT_ZERO_TO_NINE = 10;
    private static final int AMOUNT_CAPITALS_A_TO_I = 8;
    private static final int AMOUNT_CAPITALS_J_TO_N = 5;
    private static final int AMOUNT_CAPITALS_P_TO_Z = 11;
    private static final int AMOUNT_SMALLS_A_TO_K = 11;
    private static final int AMOUNT_SMALLS_M_TO_Z = 14;

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

    private static final int EASY_GERMAN_DIGIT_OFFSET = 7; // the set index for digits in the easy german set
    private static final int EASY_GERMAN_LETTER_OFFSET_1 = 17; // the first set index for letters in the easy german set
    private static final int EASY_GERMAN_LETTER_OFFSET_2 = 28; // the second set index for letters in the easy german set

    private final CharacterSet cs;
    private char[] specialCharacters;

    public CharacterSetFactory(CharacterSet cs) {
        this.cs = cs;
    }


    public char[] getCharacterSet() {
        char[] set = null;
        switch (cs) {
            case OLD:
                set = new char[33];
                set[0]=' ';
                set[1]='!';
                set[2]='"';
                set[3]='#';
                set[4]='$';
                set[5]='%';
                set[6]='&';
                set[7]='\'';
                set[8]='(';
                set[9]=')';
                set[10]='*';
                set[11]='+';
                set[12]=',';
                set[13]='-';
                set[14]='.';
                set[15]='/';
                set[16]=':';
                set[17]=';';
                set[18]='<';
                set[19]='=';
                set[20]='>';
                set[21]='?';
                set[22]='@';
                set[23]='[';
                set[24]='\\';
                set[25]=']';
                set[26]='^';
                set[27]='_';
                set[28]='`';
                set[29]='{';
                set[30]='|';
                set[31]='}';
                set[32]='~';
                // ToDo a-z, A-Z, 0-9
                break;
            case EASY_GERMAN:
                set = new char[300];
                set[0] = '+';
                set[1] = '#';
                set[2] = '-';
                set[3] = '.';
                set[4] = ',';
                set[5] = '<';
                set[6] = '+';
                for (int i = 0; i < AMOUNT_ONE_TO_NINE; i++) {
                    set[EASY_GERMAN_DIGIT_OFFSET + i] = (char)(ASCII_ONE + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_A_TO_K; i++) {
                    set[EASY_GERMAN_LETTER_OFFSET_1 + i] = (char)(ASCII_SMALL_A + i);
                }
                for (int i = 0; i < AMOUNT_SMALLS_M_TO_Z; i++) {
                    set[EASY_GERMAN_LETTER_OFFSET_2 + i] = (char)(ASCII_SMALL_M + i);
                }
                break;
            case EASY_ENGLISH:
                // ToDo
                break;
            case FULL:
                set = new char[1000];
                set[0]=' ';
                set[1]='!';
                set[2]='"';
                set[3]='#';
                set[4]='$';
                set[5]='%';
                set[6]='&';
                set[7]='\'';
                set[8]='(';
                set[9]=')';
                set[10]='*';
                set[11]='+';
                set[12]=',';
                set[13]='-';
                set[14]='.';
                set[15]='/';
                set[16]=':';
                set[17]=';';
                set[18]='<';
                set[19]='=';
                set[20]='>';
                set[21]='?';
                set[22]='@';
                set[23]='[';
                set[24]='\\';
                set[25]=']';
                set[26]='^';
                set[27]='_';
                set[28]='`';
                set[29]='{';
                set[30]='|';
                set[31]='}';
                set[32]='~';
                // ToDo
                break;
            case MINIMALISTIC:
                // ToDo
                break;
            case OPTIMIZED:
                set = new char[300];
                // ToDo
                break;
        }
        return set;
    }
}
