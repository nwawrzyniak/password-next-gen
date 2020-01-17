package nwawsoft.pwng.exceptions;

import nwawsoft.util.DebugPrinter;

public class UnknownCharacterTypeException extends Exception {
    public UnknownCharacterTypeException(final char c) {
        DebugPrinter.dp("Unknown character type (neither a lower case letter, upper case letter, digit or special " +
                "character.");
        DebugPrinter.dp("This is the character: " + c);
        DebugPrinter.dp("This is the character's integer representation: " + (int)c);
    }

    public UnknownCharacterTypeException() {
        DebugPrinter.dp("Unknown character type (neither a lower case letter, upper case letter, digit or special " +
                "character.");
    }

    public UnknownCharacterTypeException(final String typeName) {
        DebugPrinter.dp("Unknown character type (neither a lower case letter, upper case letter, digit or special " +
                "character.");
        DebugPrinter.dp("It's type is supposedly: " + typeName);
    }
}
