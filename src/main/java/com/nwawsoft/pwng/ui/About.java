package com.nwawsoft.pwng.ui;

import com.nwawsoft.pwng.model.BuildData;
import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * About window with information about version number and maintainer.
 */
public class About extends JDialog {
    private static final String GITHUB = "GitHub";
    private static final String WEBSITE = "pwng.nwawsoft.com";
    private static final String LINK_PRE = "<html><a href=''>";
    private static final String LINK_POST = "</a></html>";
    private static final String HTML_PRE = "<html>";
    private static final String HTML_POST = "</html>";

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
        JLabel jlblAboutText1 = new JLabel(HTML_PRE + t.getAboutText1() + HTML_POST, SwingConstants.CENTER);
        JLabel jlblLinkGitHub = new JLabel(LINK_PRE + GITHUB + LINK_POST, SwingConstants.CENTER);
        jlblLinkGitHub.setForeground(Color.BLUE.darker());
        jlblLinkGitHub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlblLinkGitHub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(BuildData.GITHUB_LINK));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        JLabel jlblAboutText2 = new JLabel(HTML_PRE + t.getAboutText2() + HTML_POST, SwingConstants.CENTER);
        JLabel jlblLinkWebsite = new JLabel(LINK_PRE + WEBSITE + LINK_POST, SwingConstants.CENTER);
        jlblLinkWebsite.setForeground(Color.BLUE.darker());
        jlblLinkWebsite.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlblLinkWebsite.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(BuildData.WEBSITE_LINK));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        Box vBox = Box.createVerticalBox();
        vBox.add(jlblAboutText1);
        vBox.add(jlblLinkGitHub);
        vBox.add(jlblAboutText2);
        vBox.add(jlblLinkWebsite);
        cp.add(vBox);
        ApplicationIcon ai = new ApplicationIcon();
        this.setIconImages(ai.getApplicationIcon());
        setVisible(true);
    }
}
