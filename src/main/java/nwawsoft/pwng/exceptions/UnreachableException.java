package nwawsoft.pwng.exceptions;

import nwawsoft.util.DebugPrinter;

public class UnreachableException extends Exception {
    public UnreachableException() {
        DebugPrinter.dp("You should really never reach this.");
    }
}
