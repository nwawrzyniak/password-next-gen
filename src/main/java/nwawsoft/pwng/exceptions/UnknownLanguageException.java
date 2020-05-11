package nwawsoft.pwng.exceptions;

import nwawsoft.pwng.model.language.Language;
import nwawsoft.util.tools.DebugPrinter;

public class UnknownLanguageException extends Exception {
    public UnknownLanguageException(final Language l) {
        DebugPrinter.dp("Unknown language: " + l.toString());
        DebugPrinter.dp("(for) " + l);
    }

    public UnknownLanguageException() {
        DebugPrinter.dp("Unknown language.");
    }
}
