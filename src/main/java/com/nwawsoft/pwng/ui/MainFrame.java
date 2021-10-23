package com.nwawsoft.pwng.ui;

import com.nwawsoft.pwng.exceptions.LogicErrorException;
import com.nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import com.nwawsoft.pwng.model.Generator;
import com.nwawsoft.pwng.model.Rating;
import com.nwawsoft.pwng.model.Settings;
import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.natives.StringFunctions;
import com.nwawsoft.util.tools.ClipboardManager;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;

public class MainFrame extends JFrame {
    private final JTextField jtxtInputField;
    private final JPasswordField jpfInputField;
    private final JTextField jtxtOutputField;
    private final JCheckBoxMenuItem jcbmiHidden;
    private final JLabel[] jlblChecks = new JLabel[6];
    private final JLabel jlblMarker;
    private final Settings s;
    private final Translation t;
    private final Generator g;
    private final Rating r;
    private final JButton jbtnLowerToClipboard;
    private final JButton jbtnUpperToClipboard;
    private final JButton jbtnMoveUp;
    private final JButton jbtnClear;
    private JTextField inputContainer;
    private boolean passwordHidden = false;
    private int currentWindowWidth;
    private ImageIcon iiCross;
    private ImageIcon iiCheck;
    private ImageIcon iiMarker;
    private ImageIcon iiBar;
    private ImageIcon iiMoveUp;
    private ImageIcon iiClear;
    private ImageIcon iiToClipboard;
    private int levelValue;

    public MainFrame(final Settings s, final Translation t) {
        super(t.getLongTitle());
        this.s = s;
        this.t = t;
        g = new Generator(s.getCharacterSet());
        r = new Rating();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 560;
        int frameHeight = 280;
        setSize(frameWidth, frameHeight);
        currentWindowWidth = frameWidth;
        ComponentFunctions.center(this);
        Container cp = getContentPane();
        cp.setLayout(null);
        try {
            InputStream iiCrossStream = getClass().getResourceAsStream("/graphics/mainframe/cross.png");
            iiCross = new ImageIcon(ImageIO.read(iiCrossStream));
            InputStream iiCheckStream = getClass().getResourceAsStream("/graphics/mainframe/check.png");
            iiCheck = new ImageIcon(ImageIO.read(iiCheckStream));
            InputStream iiMarkerStream = getClass().getResourceAsStream("/graphics/mainframe/marker.png");
            iiMarker = new ImageIcon(ImageIO.read(iiMarkerStream));
            InputStream iiBarStream = getClass().getResourceAsStream("/graphics/mainframe/bar.png");
            iiBar = new ImageIcon(ImageIO.read(iiBarStream));
            InputStream iiToClipboardStream = getClass().getResourceAsStream("/graphics/mainframe/to_clipboard.png");
            iiToClipboard = new ImageIcon(ImageIO.read(iiToClipboardStream));
            InputStream iiMoveUpStream = getClass().getResourceAsStream("/graphics/mainframe/move_up.png");
            iiMoveUp = new ImageIcon(ImageIO.read(iiMoveUpStream));
            InputStream iiClearStream = getClass().getResourceAsStream("/graphics/mainframe/clear.png");
            iiClear = new ImageIcon(ImageIO.read(iiClearStream));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Could not set graphics:");
            e.printStackTrace();
            System.err.println("Continuing without them...");
        }
        for (int i = 0; i < jlblChecks.length; i++) {
            jlblChecks[i] = new JLabel(iiCross);
        }
        jlblMarker = new JLabel(iiMarker);
        jbtnUpperToClipboard = new JButton(iiToClipboard);
        jbtnLowerToClipboard = new JButton(iiToClipboard);
        jbtnClear = new JButton(iiClear);
        jbtnMoveUp = new JButton(iiMoveUp);
        for (int i = 0; i < jlblChecks.length; i++) {
            jlblChecks[i].setBounds(310, 46 + (32 * i), 10, 10);
            cp.add(jlblChecks[i]);
        }
        JLabel jlblBar = new JLabel(iiBar);
        jlblBar.setBounds(50, 130, 200, 20);
        cp.add(jlblBar);
        jlblMarker.setBounds(45, 155, 10, 10);
        cp.add(jlblMarker);
        JLabel jlblImprovements = new JLabel(t.getMainImprovements());
        jlblImprovements.setBounds(330, 0, 250, 260);
        cp.add(jlblImprovements);
        jcbmiHidden = new JCheckBoxMenuItem(t.getMenuHidden());
        jcbmiHidden.addActionListener(this::jcbmiHiddenActionPerformed);
        JCheckBoxMenuItem jcbmiChecklist = new JCheckBoxMenuItem(t.getMenuChecklist());
        jcbmiChecklist.setState(true);
        jcbmiChecklist.addActionListener(this::jcbmiChecklistActionPerformed);
        JMenuItem jmiAbout = new JMenuItem(t.getHelpAboutTitle());
        JMenuItem jmiSecurityLevels = new JMenuItem(t.getHelpSecurityLevelsTitle());
        JMenu jmOptions = new JMenu(t.getMenuOptions());
        JMenu jmHelp = new JMenu(t.getHelpTitle());
        JMenuItem jmiLanguageConfig = new JMenuItem(t.getMenuLanguageConfig());
        jmiLanguageConfig.addActionListener(this::jmiLanguageConfigActionPerformed);
        jmOptions.add(jmiLanguageConfig);
        jmOptions.add(jcbmiHidden);
        jmOptions.add(jcbmiChecklist);
        jmHelp.add(jmiAbout);
        jmHelp.add(jmiSecurityLevels);
        JMenuBar jmbMainMenu = new JMenuBar();
        jmbMainMenu.add(jmOptions);
        jmbMainMenu.add(jmHelp);
        cp.add(jmbMainMenu);
        jmbMainMenu.setBounds(0, 0, 4096, 25); // 4096 just means "big". resize window -> menu bar stretches
        jmiAbout.addActionListener(this::jmiAboutActionPerformed);
        jmiSecurityLevels.addActionListener(this::jmiSafetyLevelsActionPerformed);
        KeyListener kl = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                boolean isEmpty = inputContainer.getText().equals("");
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    updatePasswordStrength();
                    jbtnLowerToClipboard.setEnabled(false);
                    jbtnMoveUp.setEnabled(false);
                }
                jbtnUpperToClipboard.setEnabled(!isEmpty);
                jbtnClear.setEnabled(!isEmpty);
            }
        };
        jtxtInputField = new JTextField();
        jtxtInputField.setBounds(10, 50, 220, 30);
        jtxtInputField.addKeyListener(kl);
        jtxtInputField.setHorizontalAlignment(JTextField.CENTER);
        cp.add(jtxtInputField);
        jbtnUpperToClipboard.setEnabled(false);
        jbtnUpperToClipboard.setBounds(235, 50, 30, 30);
        jbtnUpperToClipboard.addActionListener(this::jbtnUpperToClipboardActionPerformed);
        cp.add(jbtnUpperToClipboard);
        jbtnClear.setEnabled(false);
        jbtnClear.setBounds(270, 50, 30, 30);
        jbtnClear.addActionListener(this::jbtnClearActionPerformed);
        cp.add(jbtnClear);
        jpfInputField = new JPasswordField();
        jpfInputField.setBounds(10, 50, 220, 30);
        jpfInputField.setVisible(false);
        jpfInputField.addKeyListener(kl);
        jpfInputField.setHorizontalAlignment(JTextField.CENTER);
        cp.add(jpfInputField);
        inputContainer = jtxtInputField;
        jtxtOutputField = new JTextField();
        jtxtOutputField.setBounds(10, 90, 220, 30);
        jtxtOutputField.setEditable(false);
        jtxtOutputField.setHorizontalAlignment(JTextField.CENTER);
        cp.add(jtxtOutputField);
        jbtnLowerToClipboard.setBounds(235, 90, 30, 30);
        jbtnLowerToClipboard.addActionListener(this::jbtnLowerToClipboardActionPerformed);
        cp.add(jbtnLowerToClipboard);
        jbtnMoveUp.setBounds(270, 90, 30, 30);
        jbtnMoveUp.addActionListener(this::jbtnMoveUpActionPerformed);
        cp.add(jbtnMoveUp);
        JButton jbtnGenerate = new JButton();
        jbtnGenerate.setText(t.getMainGenerate());
        jbtnGenerate.setBounds(10, 180, 120, 40);
        jbtnGenerate.addActionListener(this::jbtnGenerateActionPerformed);
        cp.add(jbtnGenerate);
        JButton jbtnGenerateAndSave = new JButton();
        jbtnGenerateAndSave.setText(t.getMainGenerateAndSave());
        jbtnGenerateAndSave.setBounds(140, 180, 160, 40);
        jbtnGenerateAndSave.addActionListener(this::jbtnGenerateAndSaveActionPerformed);
        cp.add(jbtnGenerateAndSave);
        jbtnLowerToClipboard.setEnabled(false);
        jbtnUpperToClipboard.setEnabled(false);
        jbtnMoveUp.setEnabled(false);
        jbtnLowerToClipboard.setToolTipText(t.getMainClipboardText());
        jbtnUpperToClipboard.setToolTipText(t.getMainClipboardText());
        jbtnMoveUp.setToolTipText(t.getMainMoveUpText());
        jbtnClear.setToolTipText(t.getMainClearText());
        getRootPane().setDefaultButton(jbtnGenerate);
        ApplicationIcon ai = new ApplicationIcon();
        this.setIconImages(ai.getApplicationIcon());
        setResizable(false);
        setVisible(true);
    }

    private void jbtnClearActionPerformed(final ActionEvent actionEvent) {
        inputContainer.setText("");
        if (StringFunctions.startsWithAny(jtxtOutputField.getText(), t.getRatingTextBeginnings())) {
            jtxtOutputField.setText("");
        }
        jbtnUpperToClipboard.setEnabled(false);
        jbtnClear.setEnabled(false);
    }

    private void jbtnUpperToClipboardActionPerformed(final ActionEvent actionEvent) {
        if (!passwordHidden) {
            ClipboardManager.copyToClipboard(inputContainer.getText());
        } else {
            new HiddenPasswordDialog(this, inputContainer, t);
        }
    }

    private void jbtnLowerToClipboardActionPerformed(final ActionEvent actionEvent) {
        ClipboardManager.copyToClipboard(jtxtOutputField.getText());
    }

    private void jbtnMoveUpActionPerformed(final ActionEvent actionEvent) {
        jbtnMoveUp.setEnabled(false);
        inputContainer.setText(jtxtOutputField.getText());
        jbtnLowerToClipboard.setEnabled(false);
        jbtnUpperToClipboard.setEnabled(true);
        jbtnClear.setEnabled(true);
        updatePasswordStrength();
    }

    private void jmiLanguageConfigActionPerformed(final ActionEvent actionEvent) {
        new Preset(s, t);
        dispose();
    }

    private void jbtnGenerateActionPerformed(final ActionEvent actionEvent) {
        try {
            jtxtOutputField.setText(g.create());
        } catch (LogicErrorException | UnknownCharacterTypeException e) {
            e.printStackTrace();
        }
        jbtnMoveUp.setEnabled(true);
        jbtnLowerToClipboard.setEnabled(true);
    }

    private void jbtnGenerateAndSaveActionPerformed(final ActionEvent actionEvent) {
        try {
            jtxtOutputField.setText(g.create());
        } catch (LogicErrorException | UnknownCharacterTypeException e) {
            e.printStackTrace();
        }
        jbtnMoveUp.setEnabled(true);
        jbtnLowerToClipboard.setEnabled(true);
        new UnsafePasswordSaverDialog(this, jtxtOutputField.getText());
    }

    private void jcbmiHiddenActionPerformed(final ActionEvent actionEvent) {
        if (!jcbmiHidden.getState()) {
            inputContainer = jtxtInputField;
            String tempPass = new String(jpfInputField.getPassword());
            jtxtInputField.setText(tempPass);
            jpfInputField.setVisible(false);
            jtxtInputField.setVisible(true);
            passwordHidden = false;
        } else {
            inputContainer = jpfInputField;
            jpfInputField.setText(jtxtInputField.getText());
            jpfInputField.setVisible(true);
            jtxtInputField.setVisible(false);
            passwordHidden = true;
        }
    }

    private void jcbmiChecklistActionPerformed(final ActionEvent actionEvent) {
        changeSize();
    }

    private void jmiAboutActionPerformed(final ActionEvent actionEvent) {
        new About(this, t.getHelpAboutTitle(), true, t);
    }

    private void jmiSafetyLevelsActionPerformed(final ActionEvent actionEvent) {
        new Help(this, t.getHelpSecurityLevelsLongTitle(), true, t);
    }

    private void showRating() {
        String level;
        String password = inputContainer.getText();
        level = t.getLevelSecurityLevel();
        levelValue = r.getPasswordLevel(password);
        if (levelValue == 0) {
            level = t.getLevelBad();
        } else {
            level = level + levelValue + ".";
        }
        if (!r.dictionaryCheck(password)) {
            level = t.getLevelDictionary();
        }
        jtxtOutputField.setText(level);
    }

    private void changeSize() {
        if (currentWindowWidth == 310) {
            setSize(560, 280);
            currentWindowWidth = 560;
        } else if (currentWindowWidth == 560) {
            setSize(310, 280);
            currentWindowWidth = 310;
        }
    }

    private void updateIcons() {
        if (StringFunctions.containsLowerCaseCharacters(inputContainer.getText())) {
            jlblChecks[0].setIcon(iiCheck);
        } else {
            jlblChecks[0].setIcon(iiCross);
        }
        if (StringFunctions.containsUpperCaseCharacters(inputContainer.getText())) {
            jlblChecks[1].setIcon(iiCheck);
        } else {
            jlblChecks[1].setIcon(iiCross);
        }
        if (StringFunctions.containsSpecialCharacters(inputContainer.getText())) {
            jlblChecks[2].setIcon(iiCheck);
        } else {
            jlblChecks[2].setIcon(iiCross);
        }
        if (StringFunctions.containsDigits(inputContainer.getText())) {
            jlblChecks[3].setIcon(iiCheck);
        } else {
            jlblChecks[3].setIcon(iiCross);
        }
        if (inputContainer.getText().length() >= 14) {
            jlblChecks[4].setIcon(iiCheck);
        } else {
            jlblChecks[4].setIcon(iiCross);
        }
        try {
            if (Rating.has8changes(inputContainer.getText())) {
                jlblChecks[5].setIcon(iiCheck);
            } else {
                jlblChecks[5].setIcon(iiCross);
            }
        } catch (UnknownCharacterTypeException e) {
            e.printStackTrace();
        }
    }

    private void updatePasswordStrength() {
        showRating();
        updateIcons();
        jlblMarker.setBounds(45 + (levelValue * 40), 155, 10, 10);
    }
}
