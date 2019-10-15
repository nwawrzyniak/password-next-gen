package nwawsoft.pwng;

import nwawsoft.pwng.exceptions.UnknownLanguageException;
import nwawsoft.pwng.model.Language;
import nwawsoft.pwng.ui.GUI;

public class Starter {
    public static void main(String[] args) {
        try {
            new GUI("Password Next Gen", Language.ENGLISH); // complete overhaul still in progress
        } catch (UnknownLanguageException e) {
            e.printStackTrace();
        }
    }
}
