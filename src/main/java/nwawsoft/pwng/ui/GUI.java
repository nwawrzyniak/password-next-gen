package nwawsoft.pwng.ui;

import nwawsoft.pwng.model.CharacterSet;
import nwawsoft.pwng.model.Language;
import nwawsoft.pwng.model.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static nwawsoft.util.MutatedVowels.*;

public class GUI extends JFrame {
    private JTextField inputContainer;
    private JTextField jtxtInputField;
    private JPasswordField jpfInputField;
    private JTextField jtxtOutputField;
    private JCheckBoxMenuItem jcbmiHidden;
    private int currentWindowWidth;
    private ImageIcon iiCross = new ImageIcon("graphics/cross.png");
    private ImageIcon iiCheck = new ImageIcon("graphics/check.png");
    private ImageIcon iiMarker = new ImageIcon("graphics/marker.png");
    private JLabel jlblCheck1 = new JLabel(iiCross);
    private JLabel jlblCheck2 = new JLabel(iiCross);
    private JLabel jlblCheck3 = new JLabel(iiCross);
    private JLabel jlblCheck4 = new JLabel(iiCross);
    private JLabel jlblCheck5 = new JLabel(iiCross);
    private JLabel jlblCheck6 = new JLabel(iiCross);
    private JLabel jlblMarker = new JLabel(iiMarker);
    private int levelValue;
    private Parser p;
    private Language l;

    public GUI(final String title, final Language l, final CharacterSet cs) {
        super(title);
        this.l = l;
        this.p = new Parser(cs);
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
        ImageIcon iiBar = new ImageIcon("graphics/bar.png");
        JLabel jlblBar = new JLabel(iiBar);
        jlblBar.setBounds(50, 130, 200, 20);
        cp.add(jlblBar);
        jlblMarker.setBounds(45, 155, 10, 10);
        cp.add(jlblMarker);
        JLabel jlblImprovements = new JLabel();
        if (l.equals(Language.ENGLISH)) {
            jlblImprovements.setText("<HTML>Use lower case letter" +
                    "s<BR>" +
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
        JCheckBoxMenuItem jcbmiCriteria = null;
        switch (l) {
            case ENGLISH:
                jcbmiCriteria = new JCheckBoxMenuItem("Checklist");
                break;
            case GERMAN:
                jcbmiCriteria = new JCheckBoxMenuItem("Checkliste");
                break;
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
        JMenuBar jmbMainMenu = new JMenuBar();
        jmOptions.add(jcbmiHidden);
        jmOptions.add(jcbmiCriteria);
        jmHelp.add(jmiSecurityLevels);
        jmbMainMenu.add(jmOptions);
        jmbMainMenu.add(jmHelp);
        cp.add(jmbMainMenu);
        jmbMainMenu.setBounds(0, 0, frameWidth, 25);
        jmiSecurityLevels.addActionListener(this::jmiAboutSafetyLevelsActionPerformed);
        jtxtInputField = new JTextField();
        jtxtInputField.setBounds(20, 50, 260, 30);
        jtxtInputField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    updateSafetyCheckIcons();
                }
            }
        });
        cp.add(jtxtInputField);
        jpfInputField = new JPasswordField();
        jpfInputField.setBounds(20, 50, 260, 30);
        jpfInputField.setVisible(false);
        jpfInputField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    updateSafetyCheckIcons();
                }
            }
        });
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

    private void jbtnCreateSafePWActionPerformed(final ActionEvent evt) {
        jtxtOutputField.setText(p.createLevel5());
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

    private void check() {
        String level = null;
        if (p.parse1(inputContainer.getText())) {
            switch (l) {
                case ENGLISH:
                    level = "Security level is 1";
                    break;
                case GERMAN:
                    level = "Sicherheitsstufe betr" + ae + "gt 1";
                    break;
            }
            levelValue = 1;
            if (p.parse2(inputContainer.getText())) {
                switch (l) {
                    case ENGLISH:
                        level = "Security level is 2";
                        break;
                    case GERMAN:
                        level = "Sicherheitsstufe betr" + ae + "gt 2";
                        break;
                }
                levelValue = 2;
                if (p.parse3(inputContainer.getText())) {
                    switch (l) {
                        case ENGLISH:
                            level = "Security level is 3";
                            break;
                        case GERMAN:
                            level = "Sicherheitsstufe betr" + ae + "gt 3";
                            break;
                    }
                    levelValue = 3;
                    if (p.parse4(inputContainer.getText())) {
                        switch (l) {
                            case ENGLISH:
                                level = "Security level is 4";
                                break;
                            case GERMAN:
                                level = "Sicherheitsstufe betr" + ae + "gt 4";
                                break;
                        }
                        levelValue = 4;
                        if (p.parse5(inputContainer.getText())) {
                            switch (l) {
                                case ENGLISH:
                                    level = "Security level is 5";
                                    break;
                                case GERMAN:
                                    level = "Sicherheitsstufe betr" + ae + "gt 5";
                                    break;
                            }
                            levelValue = 5;
                        }
                    }
                }
            }
        } else {
            switch (l) {
                case ENGLISH:
                    level = "The password is horribly bad";
                    break;
                case GERMAN:
                    level = "Das Passwort ist furchtbar schlecht";
                    break;
            }
            levelValue = 0;
        } if (!p.dictionaryCheck(inputContainer.getText())) {
            switch (l) {
                case ENGLISH:
                    level = "Parts of your password are in the dictionary";
                    break;
                case GERMAN:
                    level = "Teile Ihres Passworts stehen im W" + oe + "rterbuch";
                    break;
            }
        }
        if (level != null) {
            jtxtOutputField.setText(level);
        }
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

    private void updateSafetyCheckIcons() {
        check();
        if (p.hasLower(inputContainer.getText())) {
            jlblCheck1.setIcon(iiCheck);
        } else {
            jlblCheck1.setIcon(iiCross);
        }
        if (p.hasUpper(inputContainer.getText())) {
            jlblCheck2.setIcon(iiCheck);
        } else {
            jlblCheck2.setIcon(iiCross);
        }
        if (p.hasSZ(inputContainer.getText())) {
            jlblCheck3.setIcon(iiCheck);
        } else {
            jlblCheck3.setIcon(iiCross);
        }
        if (p.hasDigit(inputContainer.getText())) {
            jlblCheck4.setIcon(iiCheck);
        } else {
            jlblCheck4.setIcon(iiCross);
        }
        if (inputContainer.getText().length() >= 14) {
            jlblCheck5.setIcon(iiCheck);
        } else {
            jlblCheck5.setIcon(iiCross);
        }
        if (p.has8changes(inputContainer.getText())) {
            jlblCheck6.setIcon(iiCheck);
        } else {
            jlblCheck6.setIcon(iiCross);
        }
        jlblMarker.setBounds(45 + (levelValue * 40), 155, 10, 10);
    }
}
