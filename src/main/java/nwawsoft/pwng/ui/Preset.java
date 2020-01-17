package nwawsoft.pwng.ui;

import nwawsoft.pwng.exceptions.UnhandledCharacterSetException;
import nwawsoft.pwng.exceptions.UnknownLanguageException;
import nwawsoft.pwng.model.characterset.CharacterSet;
import nwawsoft.pwng.model.language.Language;
import nwawsoft.pwng.model.Settings;
import nwawsoft.util.StringFunctions;

import javax.swing.*;
import java.awt.*;

public class Preset extends JFrame {
    // All entries are in their own language and alphabetically
    private JComboBox<String> jcbLanguage = new JComboBox<>();
    // all entries are ordered by amount of chars in set in ascending order
    private JComboBox<String> jcbCharSet = new JComboBox<>();
    private String charSetString;
    private boolean easyBool = false;
    private boolean optimizedBool = true;
    private boolean fullBool = false;
    private JLabel lLanguage;
    private JLabel lCharacterSet;

    public Preset() {
        super();
        Settings s = new Settings();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 310;
        int frameHeight = 156;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("General Settings");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        lLanguage = new JLabel();
        lLanguage.setBounds(0, 5, 150, 28);
        lLanguage.setText("Language");
        lLanguage.setHorizontalAlignment(SwingConstants.CENTER);
        cp.add(lLanguage);
        lCharacterSet = new JLabel();
        lCharacterSet.setBounds(150, 5, 150, 28);
        lCharacterSet.setText("Character set");
        lCharacterSet.setHorizontalAlignment(SwingConstants.CENTER);
        cp.add(lCharacterSet);
        DefaultComboBoxModel<String> jcbLanguageModel = new DefaultComboBoxModel<>();
        jcbLanguage.setModel(jcbLanguageModel);
        jcbLanguage.setBounds(8, 40, 137, 25);
        jcbLanguage.addItem("Deutsch");
        jcbLanguage.addItem("English");
        if (Settings.configFileFound()) { // ToDo: should be configFileValid() or check sooner
            jcbLanguage.setSelectedItem(StringFunctions.toOnlyFirstCharCapital(s.getLanguage().toString()));
        } else {
            jcbLanguage.setSelectedItem("English");
        }
        cp.add(jcbLanguage);
        DefaultComboBoxModel<String> jcbCharSetModel = new DefaultComboBoxModel<>();
        jcbCharSet.setModel(jcbCharSetModel);
        jcbCharSet.setBounds(160, 40, 137, 25);
        if (Settings.configFileFound()) { // ToDo: should be configFileValid() or check sooner
            // ToDo: load
        } else {
            try {
                setEnglishCharSets();
            } catch (UnhandledCharacterSetException e) {
                e.printStackTrace();
            }
        }
        cp.add(jcbCharSet);
        JButton bStart = new JButton();
        bStart.setBounds(96, 80, 105, 33);
        bStart.setText("Start");
        bStart.setMargin(new Insets(2, 2, 2, 2));
        bStart.addActionListener(evt -> {
            try {
                bStart_ActionPerformed();
            } catch (UnknownLanguageException | UnhandledCharacterSetException e) {
                e.printStackTrace();
            }
        });
        jcbLanguage.addActionListener (e -> {
            String langString = (String) jcbLanguage.getSelectedItem();
            if (langString != null) {
                if (langString.equals("English")) {
                    try {
                        setEnglishCharSets();
                        setTitle("General Settings");
                        lLanguage.setText("Language");
                        lCharacterSet.setText("Character set");
                    } catch (UnhandledCharacterSetException ex) {
                        ex.printStackTrace();
                    }
                } else if (langString.equals("Deutsch")) {
                    try {
                        setGermanCharSets();
                        setTitle("Allgemeine Einstellungen");
                        lLanguage.setText("Sprache");
                        lCharacterSet.setText("Zeichensatz");
                    } catch (UnhandledCharacterSetException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        cp.add(bStart);
        setVisible(true);
    }

    private void setEnglishCharSets() throws UnhandledCharacterSetException {
        charSetString = (String)jcbCharSet.getSelectedItem();
        setCharSetBools();
        clearOldSets();
        jcbCharSet.addItem("EASY_ENGLISH");
        jcbCharSet.addItem("OPTIMIZED");
        jcbCharSet.addItem("FULL");
        if (easyBool) {
            jcbCharSet.setSelectedItem("EASY_ENGLISH");
        } else if (optimizedBool) {
            jcbCharSet.setSelectedItem("OPTIMIZED");
        } else if (fullBool) {
            jcbCharSet.setSelectedItem("FULL");
        } else {
            throw new UnhandledCharacterSetException();
        }
    }

    private void setGermanCharSets() throws UnhandledCharacterSetException {
        charSetString = (String)jcbCharSet.getSelectedItem();
        setCharSetBools();
        clearOldSets();
        jcbCharSet.addItem("EASY_GERMAN");
        jcbCharSet.addItem("OPTIMIZED");
        jcbCharSet.addItem("FULL");
        if (easyBool) {
            jcbCharSet.setSelectedItem("EASY_GERMAN");
        } else if (optimizedBool) {
            jcbCharSet.setSelectedItem("OPTIMIZED");
        } else if (fullBool) {
            jcbCharSet.setSelectedItem("FULL");
        } else {
            throw new UnhandledCharacterSetException();
        }
    }

    private void clearOldSets() {
        jcbCharSet.removeAllItems();
    }

    private void setCharSetBools() {
        if (charSetString != null) {
            easyBool = charSetString.equals("EASY_GERMAN") ||
                    charSetString.equals("EASY_ENGLISH");
            optimizedBool = charSetString.equals("OPTIMIZED");
            fullBool = charSetString.equals("FULL");
        }
    }

    private void bStart_ActionPerformed() throws UnknownLanguageException, UnhandledCharacterSetException {
        String langString = (String) jcbLanguage.getSelectedItem();
        String charSetString = (String) jcbCharSet.getSelectedItem();
        Settings.save(langString, charSetString);
        Language l;
        CharacterSet cs;
        if (langString != null) {
            if (langString.equals("English")) {
                l = Language.ENGLISH;
            } else if (langString.equals("Deutsch")) {
                l = Language.GERMAN;
            } else {
                throw new UnknownLanguageException();
            }
            if (charSetString != null) {
                switch (charSetString) {
                    case "EASY_ENGLISH":
                        cs = CharacterSet.EASY_ENGLISH;
                        break;
                    case "EASY_GERMAN":
                        cs = CharacterSet.EASY_GERMAN;
                        break;
                    case "OPTIMIZED":
                        cs = CharacterSet.OPTIMIZED;
                        break;
                    case "FULL":
                        cs = CharacterSet.FULL;
                        break;
                    default:
                        throw new UnhandledCharacterSetException();
                }
              new MainFrame("Password Next Gen", l, cs);
              this.dispose();
            }
        }
    }
}
