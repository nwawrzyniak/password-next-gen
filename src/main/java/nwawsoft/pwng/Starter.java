package nwawsoft.pwng;

import nwawsoft.pwng.exceptions.UnknownLanguageException;
import nwawsoft.pwng.model.Settings;
import nwawsoft.pwng.model.language.Translation;
import nwawsoft.pwng.ui.MainFrame;
import nwawsoft.pwng.ui.Preset;

public class Starter {

    public static void main(String[] args) {
        Settings s = new Settings();
        Translation t = new Translation(s.getLanguage());
        if (s.getShowPresetMask()) {
            new Preset(s, t);
        } else {
            try {
                new MainFrame(s, t);
            } catch (UnknownLanguageException e) {
                e.printStackTrace();
            }
        }
    }
}
