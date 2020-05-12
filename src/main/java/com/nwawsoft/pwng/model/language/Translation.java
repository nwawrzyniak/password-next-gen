package com.nwawsoft.pwng.model.language;

import com.nwawsoft.pwng.exceptions.UnknownLanguageException;
import com.nwawsoft.pwng.model.BuildData;

import static nwawsoft.util.realworldlanguage.MutatedVowels.*;

public class Translation {
    private final Language l;

    // Preset window related text
    private String presetGeneralSettingsTitle;
    private String presetLanguage;
    private String presetCharacterSet;
    private String presetStart;

    // MainFrame window related text
    private String title;
    private String longTitle;
    private String mainGenerate;
    private String mainImprovements;

    // Menu bar related text
    private String menuLanguageConfig;
    private String menuOptions;
    private String menuChecklist;
    private String menuHidden;
    private String helpTitle;
    private String helpAboutTitle;
    private String helpSecurityLevelsTitle;

    // Security levels window related text
    private String helpSecurityLevelsLongTitle;
    private String helpPasswordLevel1;
    private String helpPasswordLevel2;
    private String helpPasswordLevel3;
    private String helpPasswordLevel4;
    private String helpPasswordLevel5;
    private String helpCharacterTypes;

    // About window related text
    private String aboutText;

    // Password rating related text
    private String levelSecurityLevel;
    private String levelBad;
    private String levelDictionary;

    public Translation(final Language l) {
        this.l = l;
        try {
            setTranslation();
        } catch (UnknownLanguageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets all UI Strings to a specific language.
     *
     * @throws UnknownLanguageException if there is no corresponding object in enum Language.
     * @see Language
     */
    private void setTranslation() throws UnknownLanguageException {
        // Universal Strings
        title = "Password Next Gen";
        // Language-specific Strings
        if (l.equals(Language.ENGLISH)) {
            presetGeneralSettingsTitle = "General Settings";
            presetLanguage = "Language";
            presetCharacterSet = "Character set";
            presetStart = "Start";
            longTitle = title + " - Your Password's Next Generator";
            mainGenerate = "Generate";
            mainImprovements = "<HTML>Use lower case letters" +
                    "<BR>" +
                    "<BR>Use upper case letters" +
                    "<BR>" +
                    "<BR>Use special characters" +
                    "<BR>" +
                    "<BR>Use digits" +
                    "<BR>" +
                    "<BR>Use 14 characters" +
                    "<BR>" +
                    "<BR>Have 8 character type changes";
            menuLanguageConfig = "General Settings";
            menuOptions = "Settings";
            menuChecklist = "Checklist";
            menuHidden = "Hide password";
            helpTitle = "Help";
            helpAboutTitle = "About pwng";
            helpSecurityLevelsTitle = "Security levels";
            helpSecurityLevelsLongTitle = "Explanation of security levels";
            helpPasswordLevel1 = "<HTML><u>Password level 1:</u>" +
                    "<BR>  - At least 6 characters</HTML>";
            helpPasswordLevel2 = "<HTML><u>Password level 2:</u>" +
                    "<BR>  - At least 8 characters" +
                    "<BR>  - At least 2 character types</HTML>";
            helpPasswordLevel3 = "<HTML><u>Password level 3:</u>" +
                    "<BR>  - At least 10 characters" +
                    "<BR>  - At least 3 character types</HTML>";
            helpPasswordLevel4 = "<HTML><u>Password level 4:</u>" +
                    "<BR>  - At least 12 characters" +
                    "<BR>  - All 4 character types</HTML>";
            helpPasswordLevel5 = "<HTML><u>Password level 5:</u>" +
                    "<BR>  - At least 14 characters" +
                    "<BR>  - All 4 character types<BR>  - At least 8 changes of character types</HTML>";
            helpCharacterTypes = "<HTML>A \"character type\" means one of these 4 categories: Upper case letters, " +
                    "lower case letters, digits, special characters.</HTML>";
            aboutText = "<HTML>pwng (password-next-gen)" +
                    "<BR>version " + BuildData.getFullVersion() +
                    "<BR>by nwawsoft" +
                    "<BR>" +
                    "<BR>Newest version at" +
                    "<BR><a href=\"https://www.github.com/nwawrzyniak/password-next-gen/\">GitHub</a>" +
                    "<BR>and" +
                    "<BR><a href=\"https://pwng.nwawsoft.com/\">pwng.nwawsoft.com</a></HTML>";
            levelSecurityLevel = "Security level is ";
            levelBad = "The password is horribly bad.";
            levelDictionary = "Parts of your password are in the dictionary.";
        } else if (l.equals(Language.GERMAN)) {
            presetGeneralSettingsTitle = "Allgemeine Einstellungen";
            presetLanguage = "Sprache";
            presetCharacterSet = "Zeichensatz";
            presetStart = "Start";
            longTitle = title + " - Dein n" + ae + "chster Passwort-Generator";
            mainGenerate = "Generieren";
            mainImprovements = "<HTML>Verwenden Sie Kleinbuchstaben" +
                    "<BR>" +
                    "<BR>Verwenden Sie Gro" + ss + "buchstaben" +
                    "<BR>" +
                    "<BR>Verwenden Sie Sonderzeichen" +
                    "<BR>" +
                    "<BR>Verwenden Sie Ziffern" +
                    "<BR>" +
                    "<BR>Verwenden Sie 14 Zeichen" +
                    "<BR>" +
                    "<BR>Verwenden Sie 8 Zeichentypwechsel";
            menuLanguageConfig = "Allgemeine Einstellungen";
            menuOptions = "Einstellungen";
            menuChecklist = "Checkliste";
            menuHidden = "Passwort verstecken";
            helpTitle = "Hilfe";
            helpAboutTitle = uE + "ber pwng";
            helpSecurityLevelsTitle = "Sicherheitsstufen";
            helpSecurityLevelsLongTitle = "Erkl" + ae + "rung der Sicherheitsstufen";
            helpPasswordLevel1 = "<HTML><u>Passwortstufe 1:</u>" +
                    "<BR>  - Mindestens 6 Zeichen</HTML>";
            helpPasswordLevel2 = "<HTML><u>Passwortstufe 2:</u>" +
                    "<BR>  - Mindestens 8 Zeichen" +
                    "<BR>  - Mindestens 2 Zeichentypen</HTML>";
            helpPasswordLevel3 = "<HTML><u>Passwortstufe 3:</u>" +
                    "<BR>  - Mindestens 10 Zeichen" +
                    "<BR>  - Mindestens 3 Zeichentypen</HTML>";
            helpPasswordLevel4 = "<HTML><u>Passwortstufe 4:</u>" +
                    "<BR>  - Mindestens 12 Zeichen" +
                    "<BR>  - Alle Zeichentypen</HTML>";
            helpPasswordLevel5 = "<HTML><u>Passwortstufe 5:</u>" +
                    "<BR>  - Mindestens 14 Zeichen" +
                    "<BR>  - Alle 4 Zeichentypen" +
                    "<BR>  - Mindestens 8 Wechsel zwischen Zeichentypen</HTML>";
            helpCharacterTypes = "<HTML>Es gibt folgende 4 Zeichentypen: Gro" + ss + "- und Kleinbuchstaben, Zahlen " +
                    "und Sonderzeichen.</HTML>";
            aboutText = "<HTML>pwng (password-next-gen)" +
                    "<BR>Version " + BuildData.getFullVersion() +
                    "<BR>von nwawsoft" +
                    "<BR>" +
                    "<BR>Aktuelleste Version unter" +
                    "<BR><a href=\"https://www.github.com/nwawrzyniak/password-next-gen/\">GitHub</a>" +
                    "<BR>und" +
                    "<BR><a href=\"https://pwng.nwawsoft.com/\">pwng.nwawsoft.com</a></HTML>";
            levelSecurityLevel = "Sicherheitsstufe betr" + ae + "gt ";
            levelBad = "Das Passwort ist furchtbar schlecht.";
            levelDictionary = "Teile Ihres Passworts stehen im W" + oe + "rterbuch.";
        } else {
            throw new UnknownLanguageException(l);
        }
    }

    public Language getLanguage() {
        return l;
    }

    public String getPresetGeneralSettingsTitle() {
        return presetGeneralSettingsTitle;
    }

    public String getPresetLanguage() {
        return presetLanguage;
    }

    public String getPresetCharacterSet() {
        return presetCharacterSet;
    }

    public String getPresetStart() {
        return presetStart;
    }

    public String getTitle() {
        return title;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public String getMainGenerate() {
        return mainGenerate;
    }

    public String getMainImprovements() {
        return mainImprovements;
    }

    public String getMenuLanguageConfig() {
        return menuLanguageConfig;
    }

    public String getMenuOptions() {
        return menuOptions;
    }

    public String getMenuChecklist() {
        return menuChecklist;
    }

    public String getMenuHidden() {
        return menuHidden;
    }

    public String getHelpTitle() {
        return helpTitle;
    }

    public String getHelpAboutTitle() {
        return helpAboutTitle;
    }

    public String getHelpSecurityLevelsTitle() {
        return helpSecurityLevelsTitle;
    }

    public String getHelpSecurityLevelsLongTitle() {
        return helpSecurityLevelsLongTitle;
    }

    public String getHelpPasswordLevel1() {
        return helpPasswordLevel1;
    }

    public String getHelpPasswordLevel2() {
        return helpPasswordLevel2;
    }

    public String getHelpPasswordLevel3() {
        return helpPasswordLevel3;
    }

    public String getHelpPasswordLevel4() {
        return helpPasswordLevel4;
    }

    public String getHelpPasswordLevel5() {
        return helpPasswordLevel5;
    }

    public String getHelpCharacterTypes() {
        return helpCharacterTypes;
    }

    public String getAboutText() {
        return aboutText;
    }

    public String getLevelSecurityLevel() {
        return levelSecurityLevel;
    }

    public String getLevelBad() {
        return levelBad;
    }

    public String getLevelDictionary() {
        return levelDictionary;
    }
}
