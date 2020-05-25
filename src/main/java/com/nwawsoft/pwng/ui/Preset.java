package com.nwawsoft.pwng.ui;

import com.nwawsoft.pwng.exceptions.UnhandledCharacterSetException;
import com.nwawsoft.pwng.model.language.Language;
import com.nwawsoft.pwng.model.Settings;
import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.natives.StringFunctions;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;

public class Preset extends JFrame {
    // All entries are in their own language and alphabetically
    private final JComboBox<String> jcbLanguage = new JComboBox<>();
    // all entries are ordered by amount of chars in set in ascending order
    private final JComboBox<String> jcbCharSet = new JComboBox<>();
    private final JLabel jlblLanguage;
    private final JLabel jlblCharacterSet;
    private final JButton jbtnStart;
    private String charSetString;
    private boolean easyBool = false;
    private boolean optimizedBool = true;
    private boolean fullBool = false;
    private Settings s;
    private Translation t;

    public Preset(final Settings s, final Translation t) {
        super();
        this.s = s;
        this.t = t;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 320;
        int frameHeight = 156;
        setSize(frameWidth, frameHeight);
        ComponentFunctions.center(this);
        setTitle(t.getPresetGeneralSettingsTitle());
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        jlblLanguage = new JLabel();
        jlblLanguage.setBounds(0, 5, 150, 28);
        jlblLanguage.setText(t.getPresetLanguage());
        jlblLanguage.setHorizontalAlignment(SwingConstants.CENTER);
        cp.add(jlblLanguage);
        jlblCharacterSet = new JLabel();
        jlblCharacterSet.setBounds(150, 5, 150, 28);
        jlblCharacterSet.setText(t.getPresetCharacterSet());
        jlblCharacterSet.setHorizontalAlignment(SwingConstants.CENTER);
        cp.add(jlblCharacterSet);
        DefaultComboBoxModel<String> jcbLanguageModel = new DefaultComboBoxModel<>();
        jcbLanguage.setModel(jcbLanguageModel);
        jcbLanguage.setBounds(8, 40, 137, 25);
        jcbLanguage.addItem("Deutsch");
        jcbLanguage.addItem("English");
        String currentLang = null;
        if (Settings.configFileFound()) { // ToDo: should be configFileValid() or check sooner
            currentLang = StringFunctions.toOnlyFirstCharCapital(s.getLanguage().toString());
            jcbLanguage.setSelectedItem(currentLang);
        } else {
            jcbLanguage.setSelectedItem("English");
        }
        cp.add(jcbLanguage);
        DefaultComboBoxModel<String> jcbCharSetModel = new DefaultComboBoxModel<>();
        jcbCharSet.setModel(jcbCharSetModel);
        jcbCharSet.setBounds(160, 40, 137, 25);
        if (Settings.configFileFound() && currentLang != null) { // ToDo: should be configFileValid() or check sooner
            // ToDo
            String currentCharacterSet = s.getCharacterSet().toString();
            if (currentLang.equals("Deutsch") || currentLang.equals("German")) {
                setGermanCharSets();
            } else if (currentLang.equals("English")) {
                setEnglishCharSets();
            }
            jcbCharSet.setSelectedItem(currentCharacterSet);
        } else {
            setEnglishCharSets();
        }
        setUniversalCharSets();
        cp.add(jcbCharSet);
        jbtnStart = new JButton();
        jbtnStart.setBounds(96, 80, 105, 33);
        jbtnStart.setText(t.getPresetStart());
        jbtnStart.setMargin(new Insets(2, 2, 2, 2));
        jbtnStart.addActionListener(evt -> jbtnStart_ActionPerformed());
        jcbLanguage.addActionListener(e -> adjustUILanguage());
        cp.add(jbtnStart);
        setVisible(true);
    }

    private void adjustUILanguage() {
        // ToDo minimal
        String langString = (String) jcbLanguage.getSelectedItem();
        if (langString != null) {
            if (langString.equals("English")) {
                setEnglishCharSets();
                t = new Translation(Language.ENGLISH);
            } else if (langString.equals("Deutsch")) {
                setGermanCharSets();
                t = new Translation(Language.GERMAN);
            }
        }
        setTitle(t.getPresetGeneralSettingsTitle());
        jlblLanguage.setText(t.getPresetLanguage());
        jlblCharacterSet.setText(t.getPresetCharacterSet());
        jbtnStart.setText(t.getPresetStart());
    }

    private void setEnglishCharSets() {
        // ToDo
        charSetString = (String) jcbCharSet.getSelectedItem();
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
            try {
                throw new UnhandledCharacterSetException();
            } catch (UnhandledCharacterSetException e) {
                e.printStackTrace();
            }
        }
    }

    private void setGermanCharSets() {
        // ToDo
        charSetString = (String) jcbCharSet.getSelectedItem();
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
            try {
                throw new UnhandledCharacterSetException();
            } catch (UnhandledCharacterSetException e) {
                e.printStackTrace();
            }
        }
    }

    private void setUniversalCharSets() {
        // ToDo
        charSetString = (String) jcbCharSet.getSelectedItem();
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
            try {
                throw new UnhandledCharacterSetException();
            } catch (UnhandledCharacterSetException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearOldSets() {
        jcbCharSet.removeAllItems();
    }

    private void setCharSetBools() {
        // ToDo
        if (charSetString != null) {
            easyBool = charSetString.equals("EASY_GERMAN") ||
                    charSetString.equals("EASY_ENGLISH");
            optimizedBool = charSetString.equals("OPTIMIZED");
            fullBool = charSetString.equals("FULL");
        }
    }

    private void jbtnStart_ActionPerformed() {
        String langString = (String) jcbLanguage.getSelectedItem();
        String charSetString = (String) jcbCharSet.getSelectedItem();
        Settings.save(langString, charSetString);
        if (langString != null) {
            if (charSetString != null) {
                s = new Settings();
                new MainFrame(s, new Translation(s.getLanguage()));
                this.dispose();
            }
        }
    }
}
