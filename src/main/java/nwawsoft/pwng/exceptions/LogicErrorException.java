package nwawsoft.pwng.exceptions;

import nwawsoft.util.tools.DebugPrinter;

public class LogicErrorException extends Exception {
    public LogicErrorException(final String errorMessage) {
        DebugPrinter.dp("The following logic error has occurred: ");
        DebugPrinter.dp(errorMessage);
    }
}
