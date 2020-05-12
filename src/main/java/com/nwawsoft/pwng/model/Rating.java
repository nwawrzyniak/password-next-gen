package com.nwawsoft.pwng.model;

import com.nwawsoft.pwng.exceptions.LogicErrorException;
import com.nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import nwawsoft.util.natives.CharFunctions;
import nwawsoft.util.natives.StringFunctions;
import nwawsoft.util.tools.DebugPrinter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Provides functions to rate the safety level of any String to be used as a password.
 */
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
     * @throws UnknownCharacterTypeException if a char is neither a standard letter, a digit or a special character.
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
                throw new UnknownCharacterTypeException(currentChar);
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
                        throw new UnknownCharacterTypeException(typeMemory);
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
                    throw new UnknownCharacterTypeException(currentChar);
                }
            }
        }
        return (counter >= 8);
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
        String password = input.toLowerCase();
        try {
            reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream
                    ("/dictionary_min_5.txt")));
            while ((currentLine = reader.readLine()) != null) {
                if (password.contains(currentLine)) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Checks whether the given String fulfills the criteria of a level 1 password.
     *
     * @param input a password to check.
     * @return true if password level is at least 1. Else false.
     */
    public boolean level1Criteria(final String input) {
        return (input.length() >= 6);
    }

    /**
     * Checks whether the given String fulfills the criteria of a level 2 password.
     *
     * @param input a password to check.
     * @return true if password level is at least 2. Else false.
     * @throws LogicErrorException never
     */
    public boolean level2Criteria(final String input) throws LogicErrorException {
        return (input.length() >= 8 && hasTypes(input, 2));
    }

    /**
     * Checks whether the given String fulfills the criteria of a level 3 password.
     *
     * @param input a password to check.
     * @return true if password level is at least 3. Else false.
     * @throws LogicErrorException never
     */
    public boolean level3Criteria(final String input) throws LogicErrorException {
        return (input.length() >= 10 && hasTypes(input, 3));
    }

    /**
     * Checks whether the given String fulfills the criteria of a level 4 password.
     *
     * @param input a password to check.
     * @return true if password level is at least 4. Else false.
     * @throws LogicErrorException never
     */
    public boolean level4Criteria(final String input) throws LogicErrorException {
        return (input.length() >= 12 && hasTypes(input, 4));
    }

    /**
     * Checks whether the given String fulfills the criteria of a level 5 password.
     *
     * @param input a password to check.
     * @return true if password level is at least 5. Else false.
     * @throws LogicErrorException           never
     * @throws UnknownCharacterTypeException if a char in input is neither a letter, digit or special character.
     */
    public boolean level5Criteria(final String input) throws UnknownCharacterTypeException, LogicErrorException {
        return (input.length() >= 14 && has8changes(input) && hasTypes(input, 4));
    }

    /**
     * Calculates the password level for a given String and returns it.
     *
     * @param input the password to rate.
     * @return a level between 0 and 5.
     */
    public int getPasswordLevel(final String input) {
        int levelValue = 5;
        try {
            if (!level5Criteria(input)) {
                levelValue--;
                if (!level4Criteria(input)) {
                    levelValue--;
                    if (!level3Criteria(input)) {
                        levelValue--;
                        if (!level2Criteria(input)) {
                            levelValue--;
                            if (!level1Criteria(input)) {
                                levelValue--;
                            }
                        }
                    }
                }
            }
        } catch (UnknownCharacterTypeException | LogicErrorException e) {
            e.printStackTrace();
        }
        return levelValue;
    }
}
