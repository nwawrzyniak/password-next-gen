package nwawsoft.pwng.model.language;

import nwawsoft.pwng.exceptions.UnknownLanguageException;

import static nwawsoft.util.realworldlanguage.MutatedVowels.*;

public class Translation {
    private Language l;

    // Preset window related text
    private String presetGeneralSettingsTitle;
    private String presetLanguage;
    private String presetCharacterSet;
    private String presetStart;

    // MainFrame window related text
    private String title;
    private String longTitle;

    // Help window related text
    private String helpTitle;
    private String helpSecurityLevelsTitle;
    private String helpSecurityLevelsLongTitle;
    private String helpPasswordLevel1;
    private String helpPasswordLevel2;
    private String helpPasswordLevel3;
    private String helpPasswordLevel4;
    private String helpPasswordLevel5;
    private String helpCharacterTypes;

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
            longTitle = title + " - Your Password's Next Generator";
            presetGeneralSettingsTitle = "General Settings";
            presetLanguage = "Language";
            presetCharacterSet = "Character set";
            presetStart = "Start";
            helpTitle = "Help";
            helpSecurityLevelsTitle = "Security levels";
            helpSecurityLevelsLongTitle = "Explanation of security levels";
            helpPasswordLevel1 = "<HTML><u>Password level 1:</u><BR>  - At least 6 characters</HTML>";
            helpPasswordLevel2 = "<HTML><u>Password level 2:</u><BR>  - At least 8 characters" +
                    "<BR>  - At least 2 character types</HTML>";
            helpPasswordLevel3 = "<HTML><u>Password level 3:</u><BR>  - At least 10 characters" +
                    "<BR>  - At least 3 character types</HTML>";
            helpPasswordLevel4 = "<HTML><u>Password level 4:</u><BR>  - At least 12 characters" +
                    "<BR>  - All 4 character types</HTML>";
            helpPasswordLevel5 = "<HTML><u>Password level 5:</u><BR>  - At least 14 characters" +
                    "<BR>  - All 4 character types<BR>  - At least 8 changes of character types</HTML>";
            helpCharacterTypes = "<HTML>A \"character type\" means one of these 4 categories: Upper case letters, " +
                    "lower case letters, digits, special characters.<HTML>";
        } else if (l.equals(Language.GERMAN)) {
            longTitle = title + " - Dein n" + ae + "chster Passwort-Generator";
            presetGeneralSettingsTitle = "Allgemeine Einstellungen";
            presetLanguage = "Sprache";
            presetCharacterSet = "Zeichensatz";
            presetStart = "Start";
            helpTitle = "Hilfe";
            helpSecurityLevelsTitle = "Sicherheitsstufen";
            helpSecurityLevelsLongTitle = "Erkl" + ae + "rung der Sicherheitsstufen";
            helpPasswordLevel1 = "<HTML><u>Passwortstufe 1:</u><BR>  - Mindestens 6 Zeichen</HTML>";
            helpPasswordLevel2 = "<HTML><u>Passwortstufe 2:</u><BR>  - Mindestens 8 Zeichen" +
                    "<BR>  - Mindestens 2 Zeichentypen</HTML>";
            helpPasswordLevel3 = "<HTML><u>Passwortstufe 3:</u><BR>  - Mindestens 10 Zeichen" +
                    "<BR>  - Mindestens 3 Zeichentypen</HTML>";
            helpPasswordLevel4 = "<HTML><u>Passwortstufe 4:</u><BR>  - Mindestens 12 Zeichen" +
                    "<BR>  - Alle Zeichentypen</HTML>";
            helpPasswordLevel5 = "<HTML><u>Passwortstufe 5:</u><BR>  - Mindestens 14 Zeichen" +
                    "<BR>  - Alle 4 Zeichentypen<BR>  - Mindestens 8 Wechsel zwischen Zeichentypen</HTML>";
            helpCharacterTypes = "<HTML>Es gibt folgende 4 Zeichentypen: Gro" + ss + "- und Kleinbuchstaben, Zahlen " +
                    "und Sonderzeichen.<HTML>";
        } else {
            throw new UnknownLanguageException(l);
        }
    }

    public Language getLanguage() {
        return l;
    }

    // Preset window related text
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

    // MainFrame window related text
    public String getTitle() {
        return title;
    }

    public String getLongTitle() {
        return longTitle;
    }

    // Help window related text
    public String getHelpTitle() {
        return helpTitle;
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
}
