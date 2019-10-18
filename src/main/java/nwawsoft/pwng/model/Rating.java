package nwawsoft.pwng.model;

import nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import nwawsoft.util.CharFunctions;

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
    public static boolean has8changes(String input) throws UnknownCharacterTypeException {
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
     * Checks whether the lower cased representation of a specified String is contained in the dictionary file.
     *
     * @param input the String to check.
     * @return true if input does not contain any entry from the dictionary. Else false.
     */
    public boolean dictionaryCheck(String input) {
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
}
