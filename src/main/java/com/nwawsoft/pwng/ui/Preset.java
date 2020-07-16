package com.nwawsoft.pwng.ui;

import com.nwawsoft.pwng.model.characterset.CharacterSet;
import com.nwawsoft.pwng.model.characterset.CharacterSetLoader;
import com.nwawsoft.pwng.model.language.Language;
import com.nwawsoft.pwng.model.Settings;
import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.natives.StringFunctions;
import com.nwawsoft.util.tools.ResourceLoader;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;

public class Preset extends JFrame {
    // All entries are in their own language and alphabetically
    private final JComboBox<String> jcbLanguage = new JComboBox<>();
    private final JComboBox<String> jcbCharSet = new JComboBox<>();
    private final JLabel jlblLanguage;
    private final JLabel jlblCharacterSet;
    private final JButton jbtnStart;
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
        String currentLang;
        if (Settings.configFileValid()) {
            currentLang = StringFunctions.toOnlyFirstCharCapital(s.getLanguage().toString());
            jcbLanguage.setSelectedItem(currentLang);
        } else {
            jcbLanguage.setSelectedItem("English");
        }
        cp.add(jcbLanguage);
        DefaultComboBoxModel<String> jcbCharSetModel = new DefaultComboBoxModel<>();
        jcbCharSet.setModel(jcbCharSetModel);
        jcbCharSet.setBounds(160, 40, 137, 25);
        setCharSets();
        jcbCharSet.setSelectedItem(0);
        cp.add(jcbCharSet);
        jbtnStart = new JButton();
        jbtnStart.setBounds(96, 80, 105, 33);
        jbtnStart.setText(t.getPresetStart());
        jbtnStart.setMargin(new Insets(2, 2, 2, 2));
        jbtnStart.addActionListener(evt -> jbtnStart_ActionPerformed());
        jcbLanguage.addActionListener(e -> adjustUILanguage());
        cp.add(jbtnStart);
        ApplicationIcon ai = new ApplicationIcon();
        this.setIconImages(ai.getApplicationIcon());
        setVisible(true);
    }

    private void adjustUILanguage() {
        String langString = (String) jcbLanguage.getSelectedItem();
        if (langString != null) {
            if (langString.equals("English")) {
                t = new Translation(Language.ENGLISH);
            } else if (langString.equals("Deutsch") || langString.equals("German")) {
                t = new Translation(Language.GERMAN);
            }
        }
        setCharSets();
        setTitle(t.getPresetGeneralSettingsTitle());
        jlblLanguage.setText(t.getPresetLanguage());
        jlblCharacterSet.setText(t.getPresetCharacterSet());
        jbtnStart.setText(t.getPresetStart());
    }

    private void setCharSets() {
        clearOldSets();
        String countryCode = t.getLanguage().getCountryCode();
        String[] sets = ResourceLoader.getFileNames("charsets");
        for (String set : sets) {
            CharacterSet temp = CharacterSetLoader.load(this, set);
            if (temp.hasCountryCode()) {
                if (set.startsWith(countryCode)) {
                    jcbCharSet.addItem(set);
                }
            } else {
                jcbCharSet.addItem(set);
            }
        }
        jcbCharSet.setSelectedItem(0);
    }

    private void clearOldSets() {
        jcbCharSet.removeAllItems();
    }

    private void jbtnStart_ActionPerformed() {
        String langString = (String) jcbLanguage.getSelectedItem();
        String charSetName = (String) jcbCharSet.getSelectedItem();
        CharacterSet cs = CharacterSetLoader.load(this, charSetName);
        Settings.save(langString, cs);
        if (langString != null) {
            if (charSetName != null) {
                s = new Settings();
                new MainFrame(s, new Translation(s.getLanguage()));
                this.dispose();
            }
        }
    }
}
