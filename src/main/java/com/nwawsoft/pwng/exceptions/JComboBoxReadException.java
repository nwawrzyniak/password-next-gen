package com.nwawsoft.pwng.exceptions;

import nwawsoft.util.tools.DebugPrinter;

public class JComboBoxReadException {
    public JComboBoxReadException(final String jcb) {
        DebugPrinter.dp("The following JComboBox gave null when it shouldn't: ");
        DebugPrinter.dp(jcb);
    }
}
