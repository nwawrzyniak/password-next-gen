package com.nwawsoft.pwng;

import com.nwawsoft.pwng.exceptions.*;
import com.nwawsoft.pwng.model.*;
import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.natives.StringArrayFunctions;
import com.nwawsoft.util.tools.ClipboardManager;

import java.io.*;

public class CLI {
  public CLI(final String[] args, final Settings s, final Translation t) {
    String[] generateAliases = {"generate", "--generate",
        "-g", "--g", "g", "/g",
        "-G", "--G", "G", "/G"};
    String[] rateAliases = {"rate", "--rate",
        "-r", "--r", "r", "/r",
        "-R", "--R", "R", "/R"};
    String[] helpAliases = {"help", "--help",
        "-h", "--h", "h", "/h",
        "-H", "--H", "H", "/H",
        "-?", "--?", "?", "/?"};
    String[] clipBoardAliases = {"copy", "--copy", "clipboard", "--clipboard",
        "-c", "--c", "c", "/c",
        "-C", "--C", "C", "/C"};
    String[] quietAliases = {"quiet", "--quiet",
        "-q", "--q", "q", "/q",
        "-Q", "--Q", "Q", "/Q"};
    String[] versionAliases = {"version", "--version",
        "-v", "--v", "v", "/v",
        "-V", "--V", "V", "/V"};

    Generator g = new Generator(s.getCharacterSet());

    if (StringArrayFunctions.contains(generateAliases, args[0])) {
      if (args.length == 1) {
        try {
          System.out.print(g.create());
          System.exit(0);
        } catch (LogicErrorException | UnknownCharacterTypeException e) {
          e.printStackTrace();
        }
      } else {
        if (args.length == 2) {
          if (StringArrayFunctions.contains(clipBoardAliases, args[1])) {
            try {
              String pw = g.create();
              ClipboardManager.copyToClipboard(pw);
              System.out.println(pw);
              System.exit(0);
            } catch (LogicErrorException | UnknownCharacterTypeException e) {
              e.printStackTrace();
            }
          } else if (StringArrayFunctions.contains(quietAliases, args[1])) {
            try {
              ClipboardManager.copyToClipboard(g.create());
              System.exit(0);
            } catch (LogicErrorException | UnknownCharacterTypeException e) {
              e.printStackTrace();
            }
          } else {
            System.err.println("Argument " + args[1] + " is either undefined or does not work with -g.");
            System.exit(-1);
          }
        } else {
          System.err.println("Too many arguments specified." +
              "\n" +
              "The option -g may be used with -c or -q but not with both or with other options.");
          System.exit(-1);
        }
      }
    } else if (StringArrayFunctions.contains(clipBoardAliases, args[0])) {
      if (args.length == 2) {
        if (StringArrayFunctions.contains(generateAliases, args[1])) {
          try {
            String pw = g.create();
            ClipboardManager.copyToClipboard(pw);
            System.out.println(pw);
            System.exit(0);
          } catch (LogicErrorException | UnknownCharacterTypeException e) {
            e.printStackTrace();
          }
        } else {
          System.err.println("Option -c may only be used with -g and no other arguments.");
          System.exit(-1);
        }
      } else {
        System.err.println("Too few or too many arguments specified. Option -c may only be used with -g and no other arguments.");
        System.exit(-1);
      }
    } else if (StringArrayFunctions.contains(versionAliases, args[0])) {
      System.out.println("Version " + BuildData.getFullVersion());
    } else if (StringArrayFunctions.contains(quietAliases, args[0])) {
      if (args.length == 2) {
        if (StringArrayFunctions.contains(generateAliases, args[1])) {
          try {
            ClipboardManager.copyToClipboard(g.create());
            System.exit(0);
          } catch (LogicErrorException | UnknownCharacterTypeException e) {
            e.printStackTrace();
          }
        } else {
          System.err.println("Option -q may only be used with -g and no other arguments.");
          System.exit(-1);
        }
      } else {
        System.err.println("Too few or too many arguments specified. Option -q may only be used with -g and no other arguments.");
        System.exit(-1);
      }
    } else if (StringArrayFunctions.contains(rateAliases, args[0])) {
      if (args.length == 1) {
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
      } else {
        if (args[1] != null) {
          System.out.print(t.getLevelSecurityLevel() + new Rating().getPasswordLevel(args[1]));
          // ToDo: dictionary check.
          //  copy/refactor procedure from GUI version
        }
      }
    } else if (StringArrayFunctions.contains(helpAliases, args[0])) {
      if (args.length == 1) {
        System.out.println("\nUsage:\n\n" +
            "  java -jar pwng.jar [options]\n" +
            "\n" +
            "  Where options are:\n" +
            "    -r   rate password\n" +
            "    -g   generate password\n" +
            "    -h   help (-h -x shows help for option x)\n" +
            "    -c   copy to clipboard (only for -g)\n" +
            "    -q   quiet (like -c but does not show password)\n" +
            "    -v   show version\n" +
            "\n" +
            "  The default action (no arguments) is to start the application in GUI mode.");
      } else {
        if (StringArrayFunctions.contains(rateAliases, args[1])) {
          System.out.println("pwng password rating function.\n" +
              "\n" +
              "Usage: java -jar pwng.jar -r [PASSWORD]\n" +
              "Where [PASSWORD] is optional.\n" +
              "If no password was specified you may enter it via prompt on the standard input stream (stdin).\n" +
              "If the password you want to rate contains spaces, brackets, quotes or other special characters you " +
              "will likely have to use -r without an additional argument.");
        } else if (StringArrayFunctions.contains(generateAliases, args[1])) {
          System.out.println("pwng password generating function.\n" +
              "\n" +
              "Usage: java -jar pwng.jar -g");
        } else if (StringArrayFunctions.contains(clipBoardAliases, args[1])) {
          System.out.println("Copies the generated password directly to your clipboard.\n" +
              "May only be used in combination with -g.\n" +
              "\n" +
              "Usage: java -jar pwng.jar -g -c");
        } else if (StringArrayFunctions.contains(quietAliases, args[1])) {
          System.out.println("Copies the generated password directly to your clipboard and does not print it.\n" +
              "May only be used in combination with -g.\n" +
              "\n" +
              "Usage: java -jar pwng.jar -g -q");
        } else if (StringArrayFunctions.contains(versionAliases, args[1])) {
          System.out.println("pwng version print function.\n" +
              "\n" +
              "Usage: java -jar pwng.jar -v");
        } else if (StringArrayFunctions.contains(helpAliases, args[1])) {
          System.out.println("Soooooo... You want help for the help function. Suuuuure...\n\n" +
              "Usage: java -jar pwng.jar -h [-x]\n" +
              "Where -x may be any other existing option.\n" +
              "Use -h (without an additional option) to see a list of all options.");
        } else {
          System.err.println("Can't give help about unknown option " + args[1] + ".");
          System.exit(-1);
        }
      }
    } else {
      System.err.println("Invalid arguments.");
      System.err.println("Maybe you should try the -h option first?");
      System.exit(-1);
    }
  }
}
