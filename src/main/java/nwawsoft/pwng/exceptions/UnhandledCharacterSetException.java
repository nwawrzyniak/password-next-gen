package nwawsoft.pwng.exceptions;

import nwawsoft.pwng.model.characterset.CharacterSet;
import nwawsoft.util.tools.DebugPrinter;

public class UnhandledCharacterSetException extends Exception {
    public UnhandledCharacterSetException(final CharacterSet cs) {
        DebugPrinter.dp("Unhandled CharacterSet: " + cs.toString());
        DebugPrinter.dp("(for) " + cs);
    }

    public UnhandledCharacterSetException() {
        DebugPrinter.dp("Unhandled CharacterSet.");
    }
}
