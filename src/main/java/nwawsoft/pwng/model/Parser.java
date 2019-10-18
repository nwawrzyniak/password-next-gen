package nwawsoft.pwng.model;

import nwawsoft.util.CharFunctions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Parser {
    Random rand;
    char[] szArray = new char[33];

    public Parser(CharacterSet cs) {
        // ToDo load special characters ["OLD"].
        CharacterSetFactory csf = new CharacterSetFactory(cs);
        szArray = csf.getCharacterSet();
    }

    public boolean has2(String input) {
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSZ = false;
        boolean hasDigit = false;
        char[] inputArray = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            if (CharFunctions.isLowerCaseLetter(inputArray[i])) {
                hasLower = true;
            }
            if (CharFunctions.isUpperCaseLetter(inputArray[i])) {
                hasUpper = true;
            }
            if (CharFunctions.isSpecialCharacter(inputArray[i])) {
                hasSZ = true;
            }
            if (CharFunctions.isDigit(inputArray[i])) {
                hasDigit = true;
            }
        }
        if (hasLower && hasUpper) {
            return true;
        }
        if (hasLower && hasSZ) {
            return true;
        }
        if (hasLower && hasDigit) {
            return true;
        }
        if (hasUpper && hasSZ) {
            return true;
        }
        if (hasUpper && hasDigit) {
            return true;
        }
        if (hasSZ && hasDigit) {
            return true;
        }
        return false;
    }

    public boolean has3(String input) {
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSZ = false;
        boolean hasDigit = false;
        char[] inputArray = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            if (CharFunctions.isLowerCaseLetter(inputArray[i])) {
                hasLower = true;
            }
            if (CharFunctions.isUpperCaseLetter(inputArray[i])) {
                hasUpper = true;
            }
            if (CharFunctions.isSpecialCharacter(inputArray[i])) {
                hasSZ = true;
            }
            if (CharFunctions.isDigit(inputArray[i])) {
                hasDigit = true;
            }
        }
        if (hasLower && hasUpper && hasDigit) {
            return true;
        }
        if (hasLower && hasUpper && hasSZ) {
            return true;
        }
        if (hasLower && hasSZ && hasDigit) {
            return true;
        }
        if (hasUpper && hasSZ && hasDigit) {
            return true;
        }
        return false;
    }

    public boolean hasEverything(String input) {
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSZ = false;
        boolean hasDigit = false;
        char[] inputArray = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            if (CharFunctions.isLowerCaseLetter(inputArray[i])) {
                hasLower = true;
            }
            if (CharFunctions.isUpperCaseLetter(inputArray[i])) {
                hasUpper = true;
            }
            if (CharFunctions.isSpecialCharacter(inputArray[i])) {
                hasSZ = true;
            }
            if (CharFunctions.isDigit(inputArray[i])) {
                hasDigit = true;
            }
        }
        if (hasLower && hasUpper && hasSZ && hasDigit) {
            return true;
        }
        return false;
    }

    public boolean has8changes(String pLine) {
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

    public boolean parse1(String pLine) {
        if (pLine.length() >= 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean parse2(String pLine) {
        if (pLine.length() >= 8) {
            if (has2(pLine)) {
                return true;
            }
        }
        return false;
    }


    public boolean parse3(String pLine) {
        if (pLine.length() >= 10) {
            if (has3(pLine)) {
                return true;
            }
        }
        return false;
    }

    public boolean parse4(String pLine) {
        if (pLine.length() >= 12) {
            if (hasEverything(pLine)) {
                return true;
            }
        }
        return false;
    }

    public boolean parse5(String pLine) {
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

    public String createLevel5() {
        rand = new Random();
        String output = "";
        int wantedLength = rand.nextInt(5) + 14;
        char[] symbol = new char[1];
        int lastUse = -1;

        while (output.length() < wantedLength) {
            int use = rand.nextInt(4);
            symbol[0] = 'Ö';
            if ((use == 0) && (lastUse != 0)) {
                int a = rand.nextInt(26) + 97;
                symbol = Character.toChars(a);
            }
            if ((use == 1) && (lastUse != 1)) {
                int a = rand.nextInt(26) + 65;
                symbol = Character.toChars(a);
            }
            if ((use == 2) && (lastUse != 2)) {
                int a = rand.nextInt(10) + 48;
                symbol = Character.toChars(a);
            }
            if ((use == 3) && (lastUse != 3)) {
                int a = rand.nextInt(5) + 91;
                symbol = Character.toChars(a);
            }
            if (use != lastUse) {
                output = output + symbol[0];
            }
            lastUse = use;
        }
        if (hasEverything(output)) {
            return output;
        } else {
            return createLevel5();
        }
    }
}
