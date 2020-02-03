package nwawsoft.pwng.ui;

import nwawsoft.pwng.exceptions.LogicErrorException;
import nwawsoft.pwng.exceptions.UnhandledCharacterSetException;
import nwawsoft.pwng.exceptions.UnknownCharacterTypeException;
import nwawsoft.pwng.exceptions.UnknownLanguageException;
import nwawsoft.pwng.model.characterset.CharacterSet;
import nwawsoft.pwng.model.Generator;
import nwawsoft.pwng.model.language.Language;
import nwawsoft.pwng.model.Rating;
import nwawsoft.util.StringFunctions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;

import static nwawsoft.util.MutatedVowels.*;

public class MainFrame extends JFrame {
    private JTextField inputContainer;
    private JTextField jtxtInputField;
    private JPasswordField jpfInputField;
    private JTextField jtxtOutputField;
    private JCheckBoxMenuItem jcbmiHidden;
    private int currentWindowWidth;
    private ImageIcon iiCross;
    private ImageIcon iiCheck;
    private ImageIcon iiMarker;
    private ImageIcon iiBar;
    private JLabel jlblCheck1;
    private JLabel jlblCheck2;
    private JLabel jlblCheck3;
    private JLabel jlblCheck4;
    private JLabel jlblCheck5;
    private JLabel jlblCheck6;
    private JLabel jlblMarker;
    private int levelValue;
    private Rating r;
    private Language l;
    private Generator g;

    public MainFrame(final String title, final Language l, final CharacterSet cs) throws UnknownLanguageException {
        super(title);
        this.l = l;
        this.r = new Rating();
        this.g = new Generator(cs);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 560;
        int frameHeight = 280;
        setSize(frameWidth, frameHeight);
        currentWindowWidth = frameWidth;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
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
        JMenuItem jmiSecurityLevels = null;
        switch (l) {
            case ENGLISH:
                jmiSecurityLevels = new JMenuItem("Security levels");
                break;
            case GERMAN:
                jmiSecurityLevels = new JMenuItem("Sicherheitsstufen");
                break;
        }
        JMenu jmOptions = null;
        switch (l) {
            case ENGLISH:
                jmOptions = new JMenu("Settings");
                break;
            case GERMAN:
                jmOptions = new JMenu("Einstellungen");
                break;
        }
        JMenu jmHelp = null;
        switch (l) {
            case ENGLISH:
                jmHelp = new JMenu("Help");
                break;
            case GERMAN:
                jmHelp = new JMenu("Hilfe");
                break;
        }
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
        jmbMainMenu.setBounds(0, 0, frameWidth, 25);
        jmiSecurityLevels.addActionListener(this::jmiAboutSafetyLevelsActionPerformed);
        jtxtInputField = new JTextField();
        jtxtInputField.setBounds(20, 50, 260, 30);
        jtxtInputField.addKeyListener(kl);
        cp.add(jtxtInputField);
        jpfInputField = new JPasswordField();
        jpfInputField.setBounds(20, 50, 260, 30);
        jpfInputField.setVisible(false);
        jpfInputField.addKeyListener(kl);
        cp.add(jpfInputField);
        inputContainer = jtxtInputField;
        jtxtOutputField = new JTextField();
        jtxtOutputField.setBounds(20, 90, 260, 30);
        jtxtOutputField.setEditable(false);
        jtxtOutputField.setHorizontalAlignment(JTextField.CENTER);
        cp.add(jtxtOutputField);
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

    private void jmiLanguageConfigActionPerformed(final ActionEvent evt) {
        new Preset();
        dispose();
    }

    private void jbtnCreateSafePWActionPerformed(final ActionEvent evt) {
        try {
            jtxtOutputField.setText(g.create());
        } catch (UnhandledCharacterSetException | LogicErrorException | UnknownCharacterTypeException e) {
            e.printStackTrace();
        }
    }

    private void jcbmiHiddenActionPerformed(final ActionEvent evt) {
        if (!jcbmiHidden.getState()) {
            inputContainer = jtxtInputField;
            String tempPass = new String(jpfInputField.getPassword());
            jtxtInputField.setText(tempPass);
            jpfInputField.setVisible(false);
            jtxtInputField.setVisible(true);
        } else {
            inputContainer = jpfInputField;
            jpfInputField.setText(jtxtInputField.getText());
            jpfInputField.setVisible(true);
            jtxtInputField.setVisible(false);
        }
    }

    private void jcbmiBorderActionPerformed(final ActionEvent evt) {
        changeSize();
    }

    private void jmiAboutSafetyLevelsActionPerformed(final ActionEvent evt) {
        switch (l) {
            case ENGLISH:
                new Help(this, "Explanation of security levels", true, l);
                break;
            case GERMAN:
                new Help(this, "Erkl" + ae + "rung der Sicherheitsstufen", true, l);
                break;
        }
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
        if (currentWindowWidth == 300) {
            setSize(560, 280);
            currentWindowWidth = 560;
        } else if (currentWindowWidth == 560) {
            setSize(300, 280);
            currentWindowWidth = 300;
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
}
