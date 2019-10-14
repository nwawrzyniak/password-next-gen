package nwawsoft.pwng.ui;

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
    private JButton jbtnCreateSafePW;
    private JMenuBar jmbMainMenu;
    private JMenu jmOptions;
    private JMenu jmHelp;
    private JCheckBoxMenuItem jcbmiHidden;
    private JCheckBoxMenuItem jcbmiCriteria;
    private JMenuItem jmiSafetyLevels;
    private String tempPass = "";
    private JLabel jlblImprovements = new JLabel();
    private int currentWindowWidth;
    private ImageIcon iiCross = new ImageIcon("graphics/cross.png");
    private ImageIcon iiCheck = new ImageIcon("graphics/check.png");
    private ImageIcon iiBar = new ImageIcon("graphics/bar.png");
    private ImageIcon iiMarker = new ImageIcon("graphics/marker.png");
    private JLabel jlblCheck1 = new JLabel(iiCross);
    private JLabel jlblCheck2 = new JLabel(iiCross);
    private JLabel jlblCheck3 = new JLabel(iiCross);
    private JLabel jlblCheck4 = new JLabel(iiCross);
    private JLabel jlblCheck5 = new JLabel(iiCross);
    private JLabel jlblCheck6 = new JLabel(iiCross);
    private JLabel jlblBar = new JLabel(iiBar);
    private JLabel jlblMarker = new JLabel(iiMarker);
    private int levelValue;
    private Parser p = new Parser();
    private String language = "ENGLISH";

    public GUI(final String title) {
        super(title);
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
        jlblBar.setBounds(50, 130, 200, 20);
        cp.add(jlblBar);
        jlblMarker.setBounds(45, 155, 10, 10);
        cp.add(jlblMarker);
        jlblImprovements.setText("<HTML>Verwenden Sie Kleinbuchtaben<BR>" +
                "<BR>Verwenden Sie Gro" + ss + "buchtaben<BR>" +
                "<BR>Verwenden Sie Sonderzeichen<BR>" +
                "<BR>Verwenden Sie Ziffern<BR>" +
                "<BR>Verwenden Sie 14 Zeichen<BR>" +
                "<BR>Verwenden Sie 8 Zeichentypwechsel");
        jlblImprovements.setBounds(330, 0, 250, 260);
        cp.add(jlblImprovements);
        jcbmiHidden = new JCheckBoxMenuItem("Passwort verstecken");
        jcbmiHidden.addActionListener(this::jcbmiHiddenActionPerformed);
        jcbmiCriteria = new JCheckBoxMenuItem("Checkliste");
        jcbmiCriteria.setState(true);
        jcbmiCriteria.addActionListener(this::jcbmiBorderActionPerformed);
        jmiSafetyLevels = new JMenuItem("Sicherheitsstufen");
        jmOptions = new JMenu("Einstellungen");
        jmHelp = new JMenu("Hilfe");
        jmbMainMenu = new JMenuBar();
        jmOptions.add(jcbmiHidden);
        jmOptions.add(jcbmiCriteria);
        jmHelp.add(jmiSafetyLevels);
        jmbMainMenu.add(jmOptions);
        jmbMainMenu.add(jmHelp);
        cp.add(jmbMainMenu);
        jmbMainMenu.setBounds(0, 0, frameWidth, 25);
        jmiSafetyLevels.addActionListener(this::jmiAboutSafetyLevelsActionPerformed);
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
        jbtnCreateSafePW = new JButton();
        jbtnCreateSafePW.setText("Erzeugen");
        jbtnCreateSafePW.setBounds(90, 180, 120, 40);
        jbtnCreateSafePW.addActionListener(this::jbtnCreateSafePWActionPerformed);
        cp.add(jbtnCreateSafePW);
        getRootPane().setDefaultButton(jbtnCreateSafePW);
        setResizable(false);
        setVisible(true);
    }

    public void jbtnCreateSafePWActionPerformed(final ActionEvent evt) {
        jtxtOutputField.setText(p.createLevel5());
    }

    public void jcbmiHiddenActionPerformed(final ActionEvent evt) {
        if (!jcbmiHidden.getState()) {
            inputContainer = jtxtInputField;
            tempPass = new String(jpfInputField.getPassword());
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

    public void jcbmiBorderActionPerformed(final ActionEvent evt) {
        changeSize();
    }

    public void jmiAboutSafetyLevelsActionPerformed(final ActionEvent evt) {
        new Help(this, "Erkl" + ae + "rung der Sicherheitsstufen", true, language);
    }

    public void check() {
        String level;
        if (p.parse1(inputContainer.getText())) {
            level = "Passwortstufe betr" + ae + "gt 1";
            levelValue = 1;
            if (p.parse2(inputContainer.getText())) {
                level = "Passwortstufe betr" + ae + "gt 2";
                levelValue = 2;
                if (p.parse3(inputContainer.getText())) {
                    level = "Passwortstufe betr" + ae + "gt 3";
                    levelValue = 3;
                    if (p.parse4(inputContainer.getText())) {
                        level = "Passwortstufe betr" + ae + "gt 4";
                        levelValue = 4;
                        if (p.parse5(inputContainer.getText())) {
                            level = "Passwortstufe betr" + ae + "gt 5";
                            levelValue = 5;
                        }
                    }
                }
            }
        } else {
            level = "Ihr Passwort ist zu unsicher!";
            levelValue = 0;
        } if (!p.dictionaryCheck(inputContainer.getText())) {
            level = "Teile Ihres Passworts stehen im W" + oe + "rterbuch.";
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
