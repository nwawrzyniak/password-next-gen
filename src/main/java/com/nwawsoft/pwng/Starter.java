package com.nwawsoft.pwng;

import com.nwawsoft.pwng.model.Settings;
import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.pwng.ui.Preset;
import com.nwawsoft.pwng.ui.MainFrame;

public class Starter {

    public static void main(String[] args) {
        if (args.length == 0) { // GUI mode
            Settings s = new Settings();
            Translation t = new Translation(s.getLanguage());
            if (s.getShowPresetMask()) {
                new Preset(s, t);
            } else {
                new MainFrame(s, t);
            }
        } else { // CLI mode
            // ToDo
        }
    }
}
