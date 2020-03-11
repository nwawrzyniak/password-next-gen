package nwawsoft.pwng.ui;

import nwawsoft.pwng.exceptions.LogicErrorException;
import nwawsoft.pwng.exceptions.UnhandledCharacterSetException;
import nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import nwawsoft.pwng.exceptions.UnknownLanguageException;
import nwawsoft.pwng.model.characterset.CharacterSet;
import nwawsoft.pwng.model.Generator;
import nwawsoft.pwng.model.language.Language;
import nwawsoft.pwng.model.Rating;
import nwawsoft.pwng.model.language.Translation;
import nwawsoft.util.ClipboardManager;
import nwawsoft.util.ComponentFunctions;
import nwawsoft.util.StringFunctions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static nwawsoft.util.MutatedVowels.*;

public class MainFrame extends JFrame {
    private JTextField inputContainer;
    private JTextField jtxtInputField;
    private JPasswordField jpfInputField;
    private boolean passwordHidden = false;
    private JTextField jtxtOutputField;
    private JCheckBoxMenuItem jcbmiHidden;
    private int currentWindowWidth;
    private ImageIcon iiCross;
    private ImageIcon iiCheck;
    private ImageIcon iiMarker;
    private ImageIcon iiBar;
    private ImageIcon iiMoveUp;
    private ImageIcon iiClear;
    private ImageIcon iiToClipboard;
    private JLabel jlblCheck1;
    private JLabel jlblCheck2;
    private JLabel jlblCheck3;
    private JLabel jlblCheck4;
    private JLabel jlblCheck5;
    private JLabel jlblCheck6;
    private JLabel jlblMarker;
    private JButton jbtnUpperToClipboard;
    private JButton jbtnClear;
    private JButton jbtnLowerToClipboard;
    private JButton jbtnMoveUp;
    private int levelValue;
    private Rating r;
    private Language l;
    private Generator g;
    private Translation t;

    public MainFrame(final String title, final Language l, final CharacterSet cs) throws UnknownLanguageException {
        super(title);
        this.l = l;
        this.r = new Rating();
        this.g = new Generator(cs);
        t = new Translation(l);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 560;
        int frameHeight = 280;
        setSize(frameWidth, frameHeight);
        currentWindowWidth = frameWidth;
        ComponentFunctions.center(this);
        Container cp = getContentPane();
        cp.setLayout(null);
        KeyListener kl = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    updatePasswordStrength();
                }
            }
        };
        try {
            InputStream iiCrossStream = getClass().getResourceAsStream("/graphics/cross.png");
            iiCross = new ImageIcon(ImageIO.read(iiCrossStream));
            InputStream iiCheckStream = getClass().getResourceAsStream("/graphics/check.png");
            iiCheck = new ImageIcon(ImageIO.read(iiCheckStream));
            InputStream iiMarkerStream = getClass().getResourceAsStream("/graphics/marker.png");
            iiMarker = new ImageIcon(ImageIO.read(iiMarkerStream));
            InputStream iiBarStream = getClass().getResourceAsStream("/graphics/bar.png");
            iiBar = new ImageIcon(ImageIO.read(iiBarStream));
            InputStream iiToClipboardStream = getClass().getResourceAsStream("/graphics/to_clipboard.png");
            iiToClipboard = new ImageIcon(ImageIO.read(iiToClipboardStream));
            InputStream iiMoveUpStream = getClass().getResourceAsStream("/graphics/move_up.png");
            iiMoveUp = new ImageIcon(ImageIO.read(iiMoveUpStream));
            InputStream iiClearStream = getClass().getResourceAsStream("/graphics/clear.png");
            iiClear = new ImageIcon(ImageIO.read(iiClearStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jlblCheck1 = new JLabel(iiCross);
        jlblCheck2 = new JLabel(iiCross);
        jlblCheck3 = new JLabel(iiCross);
        jlblCheck4 = new JLabel(iiCross);
        jlblCheck5 = new JLabel(iiCross);
        jlblCheck6 = new JLabel(iiCross);
        jlblMarker = new JLabel(iiMarker);
        jbtnUpperToClipboard = new JButton(iiToClipboard);
        jbtnClear = new JButton(iiClear);
        jbtnLowerToClipboard = new JButton(iiToClipboard);
        jbtnMoveUp = new JButton(iiMoveUp);
        jlblCheck1.setBounds(310, 46, 10, 10);
        cp.add(jlblCheck1);
        jlblCheck2.setBounds(310, 78, 10, 10);
        cp.add(jlblCheck2);
        jlblCheck3.setBounds(310, 110, 10, 10);
        cp.add(jlblCheck3);
        jlblCheck4.setBounds(310, 142, 10, 10);
        cp.add(jlblCheck4);
        jlblCheck5.setBounds(310, 174, 10, 10);
        cp.add(jlblCheck5);
        jlblCheck6.setBounds(310, 206, 10, 10);
        cp.add(jlblCheck6);
        JLabel jlblBar = new JLabel(iiBar);
        jlblBar.setBounds(50, 130, 200, 20);
        cp.add(jlblBar);
        jlblMarker.setBounds(45, 155, 10, 10);
        cp.add(jlblMarker);
        JLabel jlblImprovements = new JLabel();
        if (l.equals(Language.ENGLISH)) {
            jlblImprovements.setText("<HTML>Use lower case letters" +
                    "<BR>" +
                    "<BR>Use upper case letters" +
                    "<BR>" +
                    "<BR>Use special characters" +
                    "<BR>" +
                    "<BR>Use digits" +
                    "<BR>" +
                    "<BR>Use 14 characters" +
                    "<BR>" +
                    "<BR>Have 8 character type changes");
        } else if (l.equals(Language.GERMAN)) {
            jlblImprovements.setText("<HTML>Verwenden Sie Kleinbuchstaben" +
                    "<BR>" +
                    "<BR>Verwenden Sie Gro" + ss + "buchstaben" +
                    "<BR>" +
                    "<BR>Verwenden Sie Sonderzeichen" +
                    "<BR>" +
                    "<BR>Verwenden Sie Ziffern" +
                    "<BR>" +
                    "<BR>Verwenden Sie 14 Zeichen" +
                    "<BR>" +
                    "<BR>Verwenden Sie 8 Zeichentypwechsel");
        }
        jlblImprovements.setBounds(330, 0, 250, 260);
        cp.add(jlblImprovements);
        if (l.equals(Language.ENGLISH)) {
            jcbmiHidden = new JCheckBoxMenuItem("Hide password");
        } else if (l.equals(Language.GERMAN)) {
            jcbmiHidden = new JCheckBoxMenuItem("Passwort verstecken");
        }
        jcbmiHidden.addActionListener(this::jcbmiHiddenActionPerformed);
        JCheckBoxMenuItem jcbmiCriteria;
        switch (l) {
            case ENGLISH:
                jcbmiCriteria = new JCheckBoxMenuItem("Checklist");
                break;
            case GERMAN:
                jcbmiCriteria = new JCheckBoxMenuItem("Checkliste");
                break;
            default:
                throw new UnknownLanguageException();
        }
        jcbmiCriteria.setState(true);
        jcbmiCriteria.addActionListener(this::jcbmiBorderActionPerformed);
        JMenuItem jmiSecurityLevels = new JMenuItem(t.getHelpSecurityLevelsTitle());
        JMenu jmOptions = null;
        switch (l) {
            case ENGLISH:
                jmOptions = new JMenu("Settings");
                break;
            case GERMAN:
                jmOptions = new JMenu("Einstellungen");
                break;
        }
        JMenu jmHelp = new JMenu(t.getHelpTitle());
        JMenuItem jmiLanguageConfig;
        switch (l) {
            case ENGLISH:
                jmiLanguageConfig = new JMenuItem("General Settings");
                break;
            case GERMAN:
                jmiLanguageConfig = new JMenuItem("Allgemeine Einstellungen");
                break;
            default:
                throw new UnknownLanguageException();
        }
        jmiLanguageConfig.addActionListener(this::jmiLanguageConfigActionPerformed);
        jmOptions.add(jmiLanguageConfig);
        jmOptions.add(jcbmiHidden);
        jmOptions.add(jcbmiCriteria);
        jmHelp.add(jmiSecurityLevels);
        JMenuBar jmbMainMenu = new JMenuBar();
        jmbMainMenu.add(jmOptions);
        jmbMainMenu.add(jmHelp);
        cp.add(jmbMainMenu);
        jmbMainMenu.setBounds(0, 0, 4096, 25); // 4096 just means "big". resize window -> menu bar stretches
        jmiSecurityLevels.addActionListener(this::jmiAboutSafetyLevelsActionPerformed);
        jtxtInputField = new JTextField();
        jtxtInputField.setBounds(10, 50, 220, 30);
        jtxtInputField.addKeyListener(kl);
        cp.add(jtxtInputField);
        jbtnUpperToClipboard.setBounds(235, 50, 30, 30);
        jbtnUpperToClipboard.addActionListener(this::jbtnUpperToClipboardActionPerformed);
        cp.add(jbtnUpperToClipboard);
        jbtnClear.setBounds(270, 50, 30, 30);
        jbtnClear.addActionListener(this::jbtnClearActionPerformed);
        cp.add(jbtnClear);
        jpfInputField = new JPasswordField();
        jpfInputField.setBounds(10, 50, 220, 30);
        jpfInputField.setVisible(false);
        jpfInputField.addKeyListener(kl);
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
        JButton jbtnCreateSafePW = new JButton();
        switch (l) {
            case ENGLISH:
                jbtnCreateSafePW.setText("Generate");
                break;
            case GERMAN:
                jbtnCreateSafePW.setText("Generieren");
                break;
        }
        jbtnCreateSafePW.setBounds(90, 180, 120, 40);
        jbtnCreateSafePW.addActionListener(this::jbtnCreateSafePWActionPerformed);
        cp.add(jbtnCreateSafePW);
        getRootPane().setDefaultButton(jbtnCreateSafePW);
        setResizable(false);
        setVisible(true);
    }

    private void jbtnClearActionPerformed(final ActionEvent actionEvent) {
        inputContainer.setText("");
    }

    private void jbtnUpperToClipboardActionPerformed(final ActionEvent actionEvent) {
        if (passwordHidden) {
            CopyHiddenPasswordToClipboardDialog chptcd = new CopyHiddenPasswordToClipboardDialog(this,
                    inputContainer.getText());
            chptcd.pack();
            ComponentFunctions.center(chptcd);
            chptcd.setVisible(true);
        } else {
            ClipboardManager.copyToClipboard(inputContainer.getText());
        }
    }

    private void jbtnLowerToClipboardActionPerformed(final ActionEvent actionEvent) {
        ClipboardManager.copyToClipboard(jtxtOutputField.getText());
    }

    private void jbtnMoveUpActionPerformed(final ActionEvent actionEvent) {
        inputContainer.setText(jtxtOutputField.getText());
        updatePasswordStrength();
    }

    private void jmiLanguageConfigActionPerformed(final ActionEvent actionEvent) {
        new Preset();
        dispose();
    }

    private void jbtnCreateSafePWActionPerformed(final ActionEvent actionEvent) {
        try {
            jtxtOutputField.setText(g.create());
        } catch (UnhandledCharacterSetException | LogicErrorException | UnknownCharacterTypeException e) {
            e.printStackTrace();
        }
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

    private void jcbmiBorderActionPerformed(final ActionEvent actionEvent) {
        changeSize();
    }

    private void jmiAboutSafetyLevelsActionPerformed(final ActionEvent actionEvent) {
        new Help(this, t.getHelpSecurityLevelsLongTitle(), true, t);
    }

    private void printRating() throws UnknownLanguageException {
        String level;
        String password = inputContainer.getText();
        switch (l) {
            case ENGLISH:
                level = "Security level is ";
                break;
            case GERMAN:
                level = "Sicherheitsstufe betr" + ae + "gt ";
                break;
            default:
                throw new UnknownLanguageException(l);
        }
        levelValue = r.getPasswordLevel(password);
        if (levelValue == 0) {
            switch (l) {
                case ENGLISH:
                    level = "The password is horribly bad.";
                    break;
                case GERMAN:
                    level = "Das Passwort ist furchtbar schlecht.";
                    break;
                default:
                    throw new UnknownLanguageException(l);
            }
        } else {
            level = level + levelValue + ".";
        }
        if (!r.dictionaryCheck(password)) {
            switch (l) {
                case ENGLISH:
                    level = "Parts of your password are in the dictionary.";
                    break;
                case GERMAN:
                    level = "Teile Ihres Passworts stehen im W" + oe + "rterbuch.";
                    break;
                default:
                    throw new UnknownLanguageException(l);
            }
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
            jlblCheck1.setIcon(iiCheck);
        } else {
            jlblCheck1.setIcon(iiCross);
        }
        if (StringFunctions.containsUpperCaseCharacters(inputContainer.getText())) {
            jlblCheck2.setIcon(iiCheck);
        } else {
            jlblCheck2.setIcon(iiCross);
        }
        if (StringFunctions.containsSpecialCharacters(inputContainer.getText())) {
            jlblCheck3.setIcon(iiCheck);
        } else {
            jlblCheck3.setIcon(iiCross);
        }
        if (StringFunctions.containsDigits(inputContainer.getText())) {
            jlblCheck4.setIcon(iiCheck);
        } else {
            jlblCheck4.setIcon(iiCross);
        }
        if (inputContainer.getText().length() >= 14) {
            jlblCheck5.setIcon(iiCheck);
        } else {
            jlblCheck5.setIcon(iiCross);
        }
        try {
            if (Rating.has8changes(inputContainer.getText())) {
                jlblCheck6.setIcon(iiCheck);
            } else {
                jlblCheck6.setIcon(iiCross);
            }
        } catch (UnknownCharacterTypeException e) {
            e.printStackTrace();
        }
    }

    private void updatePasswordStrength() {
        try {
            printRating();
        } catch (UnknownLanguageException e) {
            e.printStackTrace();
        }
        updateIcons();
        jlblMarker.setBounds(45 + (levelValue * 40), 155, 10, 10);
    }

    public JTextField getInputContainer() {
        return inputContainer;
    }
}
