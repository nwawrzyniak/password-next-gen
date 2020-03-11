package nwawsoft.pwng.ui;

import nwawsoft.pwng.model.language.Translation;
import nwawsoft.util.ComponentFunctions;

import javax.swing.*;
import java.awt.*;

public class About extends JDialog {
    private JLabel jlblAboutText = new JLabel();
    private Translation t;

    public About(final JFrame owner, final String title, final boolean modal, final Translation t) {
        super(owner, title, modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 300;
        int frameHeight = 240;
        setSize(frameWidth, frameHeight);
        ComponentFunctions.center(this);
        Container cp = getContentPane();
        cp.setLayout(null);
        setResizable(false);
        this.t = t;

        setVisible(true);
    }
}
