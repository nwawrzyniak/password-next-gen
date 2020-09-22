package com.nwawsoft.pwng;

import com.nwawsoft.pwng.exceptions.LogicErrorException;
import com.nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import com.nwawsoft.pwng.model.BuildData;
import com.nwawsoft.pwng.model.Generator;
import com.nwawsoft.pwng.model.Rating;
import com.nwawsoft.pwng.model.Settings;
import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.natives.StringArrayFunctions;

public class CLI {
    public CLI(final String[] args, final Settings s, final Translation t) {
        // ToDo: Error handling with args length / array bounds
        //  in this whole class.
        String[] generateAliases = {"generate",
                "-g", "--g", "g", "/g",
                "-G", "--G", "G", "/G"};
        String[] rateAliases = {"rate",
                "-r", "--r", "r", "/r",
                "-R", "--R", "R", "/R"};
        String[] helpAliases = {"help",
                "h", "-h", "--h", "/h",
                "H", "-H", "--H", "/H",
                "/?", "-?", "--?", "?"};
        if (StringArrayFunctions.contains(generateAliases, args[0])) {
            // ToDo: make this work for spaces, special characters and so on. everything after the indicator alias
            //  should be treated literal.
            try {
                System.out.println("\n" + new Generator(s.getCharacterSet()).create());
            } catch (LogicErrorException | UnknownCharacterTypeException e) {
                e.printStackTrace();
            }
        } else if (StringArrayFunctions.contains(rateAliases, args[0])) {
            System.out.println("\n" + t.getLevelSecurityLevel() +
                    new Rating().getPasswordLevel(args[1]));
            // ToDo: dictionary check.
            //  Maybe just copy/refactor procedure from GUI version?
        } else if (StringArrayFunctions.contains(helpAliases, args[0])) {
            if (args.length == 1) {
                System.out.println("\n" +
                        "pwng version " + BuildData.getFullVersion() + "\n" +
                        "\n" +
                        "Usage:\n" +
                        "pwng [-r password] [-g]\n" +
                        "\n" +
                        " Where options are:\n" +
                        "  -r   rate password\n" +
                        "  -g   generate password\n" +
                        "\n" +
                        "The default action is to start the application in GUI mode.");
            } else {
                // ToDo: Case: User wants help about an option (help -g, help -r, ...)
            }
        }
    }
}
