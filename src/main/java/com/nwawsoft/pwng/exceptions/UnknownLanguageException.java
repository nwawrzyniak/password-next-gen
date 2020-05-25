package com.nwawsoft.pwng.exceptions;

import com.nwawsoft.pwng.model.language.Language;
import com.nwawsoft.util.tools.DebugPrinter;

public class UnknownLanguageException extends Exception {
    public UnknownLanguageException(final Language l) {
        DebugPrinter.dp("Unknown language: " + l.toString());
        DebugPrinter.dp("(for) " + l);
    }

    public UnknownLanguageException() {
        DebugPrinter.dp("Unknown language.");
    }
}
