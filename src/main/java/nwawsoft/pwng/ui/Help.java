package nwawsoft.pwng.ui;

import nwawsoft.pwng.model.Language;

import javax.swing.*;
import java.awt.*;

import static nwawsoft.util.MutatedVowels.ss;

public class Help extends JDialog {
    private static final int LEVELS = 5;
    private JLabel[] jlblLevel = new JLabel[LEVELS];
    private JLabel jlblTypes = new JLabel();
    private Language l;

    public Help(final JFrame owner, final String title, final boolean modal, final Language l) {
        super(owner, title, modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 300;
        int frameHeight = 380;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        Container cp = getContentPane();
        cp.setLayout(null);
        setResizable(false);
        this.l = l;
        initHelpText();
        setHelpText();
        addHelpText(cp);
        setVisible(true);
    }

    private void initHelpText() {
        for (int i = 0; i < LEVELS; i++) {
            jlblLevel[i] = new JLabel();
        }
    }

    private void setHelpText() {
        switch (l) {
            case ENGLISH:
                jlblLevel[0].setText("<HTML><u>Password level 1:</u>" +
                        "<BR>  - At least 6 characters</HTML>");
                jlblLevel[1].setText("<HTML><u>Password level 2:</u>" +
                        "<BR>  - At least 8 characters" +
                        "<BR>  - At least 2 character types</HTML>");
                jlblLevel[2].setText("<HTML><u>Password level 3:</u>" +
                        "<BR>  - At least 10 characters" +
                        "<BR>  - At least 3 character types</HTML>");
                jlblLevel[3].setText("<HTML><u>Password level 4:</u>" +
                        "<BR>  - At least 12 characters" +
                        "<BR>  - All 4 character types</HTML>");
                jlblLevel[4].setText("<HTML><u>Password level 5:</u>" +
                        "<BR>  - At least 14 characters" +
                        "<BR>  - All 4 character types" +
                        "<BR>  - At least 8 changes of character types</HTML>");
                jlblTypes.setText("<HTML>A \"character type\" means one of these 4 categories: Upper case letters, " +
                        "lower case letters, digits, special characters.<HTML>");
                break;
            case GERMAN:
                jlblLevel[0].setText("<HTML><u>Passwortstufe 1:</u>" +
                        "<BR>  - Mindestens 6 Zeichen</HTML>");
                jlblLevel[1].setText("<HTML><u>Passwortstufe 2:</u>" +
                        "<BR>  - Mindestens 8 Zeichen" +
                        "<BR>  - Mindestens 2 Zeichentypen</HTML>");
                jlblLevel[2].setText("<HTML><u>Passwortstufe 3:</u>" +
                        "<BR>  - Mindestens 10 Zeichen" +
                        "<BR>  - Mindestens 3 Zeichentypen</HTML>");
                jlblLevel[3].setText("<HTML><u>Passwortstufe 4:</u>" +
                        "<BR>  - Mindestens 12 Zeichen" +
                        "<BR>  - Alle Zeichentypen</HTML>");
                jlblLevel[4].setText("<HTML><u>Passwortstufe 5:</u>" +
                        "<BR>  - Mindestens 14 Zeichen" +
                        "<BR>  - Alle 4 Zeichentypen" +
                        "<BR>  - Mindestens 8 Wechsel zwischen Zeichentypen</HTML>");
                jlblTypes.setText("<HTML>Es gibt folgende 4 Zeichentypen: Gro" + ss + "- und Kleinbuchstaben, Zahlen " +
                        "und Sonderzeichen.<HTML>");
                break;
        }
    }

    private void addHelpText(final Container cp) {
        jlblLevel[0].setBounds(10, 10, 280, 30);
        jlblLevel[1].setBounds(10, 50, 280, 45);
        jlblLevel[2].setBounds(10, 105, 280, 45);
        jlblLevel[3].setBounds(10, 160, 280, 45);
        jlblLevel[4].setBounds(10, 205, 280, 80);
        for (int i = 0; i < LEVELS; i++) {
            cp.add(jlblLevel[i]);
        }
        jlblTypes.setBounds(10, 280, 280, 80);
        cp.add(jlblTypes);
    }
}
