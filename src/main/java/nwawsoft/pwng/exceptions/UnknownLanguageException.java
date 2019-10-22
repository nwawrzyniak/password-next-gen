package nwawsoft.pwng.exceptions;

import nwawsoft.pwng.model.Language;
import nwawsoft.util.DebugPrinter;

public class UnknownLanguageException extends Exception {
    public UnknownLanguageException(Language l) {
        DebugPrinter.dp("Unknown language: " + l.toString());
        DebugPrinter.dp("(for) " + l);
    }
}
