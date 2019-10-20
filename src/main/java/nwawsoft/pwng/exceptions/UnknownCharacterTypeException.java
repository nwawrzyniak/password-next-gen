package nwawsoft.pwng.exceptions;

import nwawsoft.util.DebugPrinter;

public class UnknownCharacterTypeException extends Exception {
    public UnknownCharacterTypeException() {
        DebugPrinter.dp("Unknown character type (neither a lower case letter, upper case letter, digit or special " +
                "character.");
    }
}
