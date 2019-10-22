package nwawsoft.pwng.exceptions;

import nwawsoft.pwng.model.CharacterSet;
import nwawsoft.util.DebugPrinter;

public class UnhandledCharacterSetException extends Exception {
    public UnhandledCharacterSetException(CharacterSet cs) {
        DebugPrinter.dp("Unhandled CharacterSet: " + cs.toString());
        DebugPrinter.dp("(for) " + cs);
    }
}
