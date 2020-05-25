package com.nwawsoft.pwng.ui;

import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;

/**
 * About window with information about version number and maintainer.
 */
public class About extends JDialog {
    public About(final JFrame owner, final String title, final boolean modal, final Translation t) {
        super(owner, title, modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 380;
        int frameHeight = 170;
        setSize(frameWidth, frameHeight);
        ComponentFunctions.center(this);
        Container cp = getContentPane();
        this.setLayout(new FlowLayout());
        setResizable(false);
        JLabel jlblAboutText = new JLabel(t.getAboutText(), SwingConstants.CENTER);
        cp.add(jlblAboutText);
        setVisible(true);
    }
}
