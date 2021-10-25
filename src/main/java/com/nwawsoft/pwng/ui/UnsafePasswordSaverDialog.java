package com.nwawsoft.pwng.ui;

import com.nwawsoft.pwng.model.UnsafePasswordSaver;
import com.nwawsoft.pwng.model.language.Translation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Dialog that takes a file name input to save to.
 */
public class UnsafePasswordSaverDialog extends JDialog {
    private final JTextField fileName = new JTextField();
    private final String password;

    public UnsafePasswordSaverDialog(final JFrame mainFrame, final String password, final Translation t) {
        super(mainFrame, true);
        this.password = password;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 300;
        int frameHeight = 140;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle(t.getUnsafePasswordSaverDialogTitle());
        Container cp = getContentPane();
        cp.setLayout(null);
        fileName.setBounds(10, 10, 260, 30);
        fileName.setHorizontalAlignment(JLabel.CENTER);
        fileName.setText("");
        cp.add(fileName);
        JButton bYes = new JButton();
        bYes.setBounds(0, 0, 120, 33);
        bYes.setText(t.getUnsafePasswordSaverDialogSave());
        bYes.addActionListener(this::bYes_ActionPerformed);
        JButton bCancel = new JButton();
        bCancel.setBounds(130, 0, 120, 33);
        bCancel.setText(t.getUnsafePasswordSaverDialogCancel());
        bCancel.addActionListener(this::bCancel_ActionPerformed);
        JPanel buttonPanel = new JPanel(null, true);
        buttonPanel.add(bYes);
        buttonPanel.add(bCancel);
        buttonPanel.setBounds(10, 50, 300, 40);
        buttonPanel.setOpaque(false);
        cp.add(buttonPanel);
        ApplicationIcon ai = new ApplicationIcon();
        this.setIconImages(ai.getApplicationIcon());
        setResizable(false);
        setVisible(true);
    }

    public void bYes_ActionPerformed(final ActionEvent evt) {
        String saveFileName = fileName.getText();
        if (saveFileName.length() >= 1 && UnsafePasswordSaver.isFileName(saveFileName)) {
            UnsafePasswordSaver.save(saveFileName, password);
            dispose();
        }
    }

    public void bCancel_ActionPerformed(final ActionEvent evt) {
        dispose();
    }
}
