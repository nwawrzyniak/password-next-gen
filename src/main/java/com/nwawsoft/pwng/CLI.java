package com.nwawsoft.pwng;

import com.nwawsoft.pwng.exceptions.LogicErrorException;
import com.nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import com.nwawsoft.pwng.model.BuildData;
import com.nwawsoft.pwng.model.Generator;
import com.nwawsoft.pwng.model.Rating;
import com.nwawsoft.pwng.model.Settings;
import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.natives.StringArrayFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
      try {
        System.out.print(new Generator(s.getCharacterSet()).create());
      } catch (LogicErrorException | UnknownCharacterTypeException e) {
        e.printStackTrace();
      }
    } else if (StringArrayFunctions.contains(rateAliases, args[0])) {
      System.out.println("Please enter the password to rate:");
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String pw = "";
      try {
        pw = reader.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.print(t.getLevelSecurityLevel() + new Rating().getPasswordLevel(pw));
      // ToDo: dictionary check.
      //  copy/refactor procedure from GUI version
    } else if (StringArrayFunctions.contains(helpAliases, args[0])) {
      if (args.length == 1) {
        System.out.println("\n" +
            "pwng version " + BuildData.getFullVersion() + "\n" +
            "\n" +
            "Usage:\n" +
            "java -jar pwng.jar [option]\n" +
            "\n" +
            " Where options are:\n" +
            "  -r   rate password\n" +
            "  -g   generate password\n" +
            "  -h   help\n" +
            "\n" +
            "The default action is to start the application in GUI mode.");
      } else {
        if (StringArrayFunctions.contains(rateAliases, args[1])) {
          System.out.println("\n" +
              "pwng password rating function.\n" +
              "\n" +
              "Usage: java -jar pwng.jar -r");
        } else if (StringArrayFunctions.contains(generateAliases, args[1])) {
          System.out.println("\n" +
              "pwng password generating function.\n" +
              "\n" +
              "Usage: java -jar pwng.jar -g");
        } else {
          System.err.println("Can't give help about unknown option \"" + args[1] + "\".");
          System.exit(-1);
        }
      }
    }
  }
}
