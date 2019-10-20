package nwawsoft.pwng.model;

import nwawsoft.pwng.exceptions.LogicErrorException;
import nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import nwawsoft.util.CharFunctions;
import nwawsoft.util.DebugPrinter;
import nwawsoft.util.StringFunctions;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Rating {

    private final static String LOWER = "lower";
    private final static String UPPER = "upper";
    private final static String DIGIT = "digit";
    private final static String SPECIAL = "special";

    /**
     * Checks whether the specified String has at least 8 changes of character types.
     *
     * @param input the String to check.
     * @return true if at least 8 character type changes are present. Else false.
     */
    public static boolean has8changes(final String input) throws UnknownCharacterTypeException {
        int counter = 1;
        String typeMemory;
        char currentChar;
        if (input.length() >= 2) {
            currentChar = input.charAt(0);
            if (CharFunctions.isLowerCaseLetter(currentChar)) {
                typeMemory = LOWER;
            } else if (CharFunctions.isUpperCaseLetter(currentChar)) {
                typeMemory = UPPER;
            } else if (CharFunctions.isDigit(currentChar)) {
                typeMemory = DIGIT;
            } else if (CharFunctions.isSpecialCharacter(currentChar)) {
                typeMemory = SPECIAL;
            } else {
                throw new UnknownCharacterTypeException();
            }
            for (int i = 1; i < input.length(); i++) {
                currentChar = input.charAt(i);
                switch (typeMemory) {
                    case LOWER:
                        if (!CharFunctions.isLowerCaseLetter(currentChar)) {
                            counter++;
                        }
                        break;
                    case UPPER:
                        if (!CharFunctions.isUpperCaseLetter(currentChar)) {
                            counter++;
                        }
                        break;
                    case DIGIT:
                        if (!CharFunctions.isDigit(currentChar)) {
                            counter++;
                        }
                        break;
                    case SPECIAL:
                        if (!CharFunctions.isSpecialCharacter(currentChar)) {
                            counter++;
                        }
                        break;
                    default:
                        throw new UnknownCharacterTypeException();
                }
                if (CharFunctions.isLowerCaseLetter(currentChar)) {
                    typeMemory = LOWER;
                } else if (CharFunctions.isUpperCaseLetter(currentChar)) {
                    typeMemory = UPPER;
                } else if (CharFunctions.isDigit(currentChar)) {
                    typeMemory = DIGIT;
                } else if (CharFunctions.isSpecialCharacter(currentChar)) {
                    typeMemory = SPECIAL;
                } else {
                    throw new UnknownCharacterTypeException();
                }
            }
        }
        return counter >= 8;
    }

    /**
     * Checks whether the specified String uses x or more different character types.
     *
     * @param input the String to check.
     * @param x     the amount of character types required to pass.
     * @return true if the required amount of different character types was reached or if x is 0. Else false.
     */
    public boolean hasTypes(final String input, final int x) throws LogicErrorException {
        if (x >= 0 && x <= 4) {
            int counter = 0;
            if (StringFunctions.containsLowerCaseCharacters(input)) {
                counter++;
            }
            if (StringFunctions.containsUpperCaseCharacters(input)) {
                counter++;
            }
            if (StringFunctions.containsDigits(input)) {
                counter++;
            }
            if (StringFunctions.containsSpecialCharacters(input)) {
                counter++;
            }
            return (counter >= x);
        }
        if (x < 0) {
            throw new LogicErrorException("There cannot be a negative amount of character types.");
        }
        DebugPrinter.dp(this, "Returning false because x was bigger than 4. It was " + x + ".");
        return false;
    }

    /**
     * Checks whether the lower cased representation of a specified String is contained in the dictionary file.
     *
     * @param input the String to check.
     * @return true if input does not contain any entry from the dictionary. Else false.
     */
    public boolean dictionaryCheck(final String input) {
        BufferedReader reader;
        String currentLine;
        try {
            reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream
                    ("/dictionary_min_5.txt")));
            while ((currentLine = reader.readLine()) != null) {
                if (input.toLowerCase().contains(currentLine.toLowerCase())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean level1Criteria(String pLine) {
        return pLine.length() >= 5;
    }

    public boolean level2Criteria(String pLine) {
        if (pLine.length() >= 8) {
            try {
                if (hasTypes(pLine, 2)) {
                    return true;
                }
            } catch (LogicErrorException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean level3Criteria(String pLine) {
        if (pLine.length() >= 10) {
            try {
                if (hasTypes(pLine, 3)) {
                    return true;
                }
            } catch (LogicErrorException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean level4Criteria(String pLine) {
        if (pLine.length() >= 12) {
            try {
                if (hasTypes(pLine, 4)) {
                    return true;
                }
            } catch (LogicErrorException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean level5Criteria(String pLine) {
        // ToDo: Rework
        if (pLine.length() >= 14) {
            int lZustand = 0;
            char symbol;
            int lZaehler = 0;
            int wechsel = 0;
            while (lZaehler < pLine.length()) {
                symbol = pLine.charAt(lZaehler);
                if (lZustand == 0) {
                    if (symbol >= 'a' && symbol <= 'z') {
                        lZustand = 1;
                    }
                    if (symbol >= 'A' && symbol <= 'Z') {
                        lZustand = 2;
                    }
                    if (CharFunctions.isSpecialCharacter(symbol)) {
                        lZustand = 3;
                    }
                    if (symbol >= '0' && symbol <= '9') {
                        lZustand = 4;
                    }
                } else if (lZustand == 1) {
                    if (symbol >= 'A' && symbol <= 'Z') {
                        lZustand = 2;
                        wechsel++;
                    }
                    if (CharFunctions.isSpecialCharacter(symbol)) {
                        lZustand = 3;
                        wechsel++;
                    }
                    if (symbol >= '0' && symbol <= '9') {
                        lZustand = 4;
                        wechsel++;
                    }
                } else if (lZustand == 2) {
                    if (symbol >= 'a' && symbol <= 'z') {
                        lZustand = 1;
                        wechsel++;
                    }
                    if (CharFunctions.isSpecialCharacter(symbol)) {
                        lZustand = 3;
                        wechsel++;
                    }
                    if (symbol >= '0' && symbol <= '9') {
                        lZustand = 4;
                        wechsel++;
                    }
                } else if (lZustand == 3) {
                    if (symbol >= 'a' && symbol <= 'z') {
                        lZustand = 1;
                        wechsel++;
                    }
                    if (symbol >= 'A' && symbol <= 'Z') {
                        lZustand = 2;
                        wechsel++;
                    }
                    if (symbol >= '0' && symbol <= '9') {
                        lZustand = 4;
                        wechsel++;
                    }
                } else if (lZustand == 4) {
                    if (symbol >= 'a' && symbol <= 'z') {
                        lZustand = 1;
                        wechsel++;
                    }
                    if (symbol >= 'A' && symbol <= 'Z') {
                        lZustand = 2;
                        wechsel++;
                    }
                    if (CharFunctions.isSpecialCharacter(symbol)) {
                        lZustand = 3;
                        wechsel++;
                    }
                }
                lZaehler = lZaehler + 1;
            }
            if (wechsel >= 8) {
                return lZustand == 1 || lZustand == 2 || lZustand == 3 || lZustand == 4;
            }
            return false;
        }
        return false;
    }
}
