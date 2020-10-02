package com.nwawsoft.pwng.ui;

import com.nwawsoft.pwng.model.BuildData;
import com.nwawsoft.pwng.model.language.Translation;
import com.nwawsoft.util.html.HTMLTagger;
import com.nwawsoft.util.ui.ComponentFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * About window with information about version number, maintainer and online appearance.
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
        JLabel jlblAboutText1 = new JLabel(HTMLTagger.toHTML(t.getAboutText1()), SwingConstants.CENTER);
        JLabel jlblLinkGitHub = new JLabel(HTMLTagger.toLink(BuildData.GITHUB), SwingConstants.CENTER);
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
        JLabel jlblAboutText2 = new JLabel(HTMLTagger.toHTML(t.getAboutText2()), SwingConstants.CENTER);
        JLabel jlblLinkWebsite = new JLabel(HTMLTagger.toLink(BuildData.WEBSITE), SwingConstants.CENTER);
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
