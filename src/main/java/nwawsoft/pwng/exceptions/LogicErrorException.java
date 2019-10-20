package nwawsoft.pwng.exceptions;

import nwawsoft.util.DebugPrinter;

public class LogicErrorException extends Exception {
    public LogicErrorException(String errorMessage) {
        DebugPrinter.dp("The following logical fallacy has occurred: ");
        DebugPrinter.dp(errorMessage);
    }
}
