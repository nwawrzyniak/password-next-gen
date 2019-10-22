package nwawsoft.pwng;

import nwawsoft.pwng.model.CharacterSet;
import nwawsoft.pwng.model.Language;
import nwawsoft.pwng.ui.GUI;

public class Starter {
    public static void main(String[] args) {
        new GUI("Password Next Gen", Language.ENGLISH, CharacterSet.OPTIMIZED); // complete overhaul still in progress
    }
}
