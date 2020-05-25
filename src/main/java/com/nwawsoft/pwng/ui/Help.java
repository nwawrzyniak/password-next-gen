package com.nwawsoft.pwng.ui;

import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;

public class Help extends JDialog {
    private static final int LEVELS = 5;
    private JLabel[] jlblLevel = new JLabel[LEVELS];
    private JLabel jlblTypes = new JLabel();
    private Translation t;

    public Help(final JFrame owner, final String title, final boolean modal, final Translation t) {
        super(owner, title, modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 300;
        int frameHeight = 380;
        setSize(frameWidth, frameHeight);
        ComponentFunctions.center(this);
        Container cp = getContentPane();
        cp.setLayout(null);
        setResizable(false);
        this.t = t;
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
        jlblLevel[0].setText(t.getHelpPasswordLevel1());
        jlblLevel[1].setText(t.getHelpPasswordLevel2());
        jlblLevel[2].setText(t.getHelpPasswordLevel3());
        jlblLevel[3].setText(t.getHelpPasswordLevel4());
        jlblLevel[4].setText(t.getHelpPasswordLevel5());
        jlblTypes.setText(t.getHelpCharacterTypes());
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
