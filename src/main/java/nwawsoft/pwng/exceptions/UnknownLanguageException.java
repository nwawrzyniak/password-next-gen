package nwawsoft.pwng.exceptions;

import nwawsoft.pwng.model.Language;

public class UnknownLanguageException extends Exception {
    public UnknownLanguageException(Language l) {
        System.err.println("Unknown language: " + l);
    }
}
