package com.nwawsoft.pwng.ui;

import com.nwawsoft.util.tools.ClipboardManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Dialog that warns the user if he tries to copy a hidden password.
 */
public class HiddenPasswordDialog extends JDialog {
    private final JTextField inputContainer;

    public HiddenPasswordDialog(final JFrame mainFrame, final JTextField inputContainer) {
        super(mainFrame, true);
        this.inputContainer = inputContainer;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 600;
        int frameHeight = 144;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Hidden Password Copy Warning");
        Container cp = getContentPane();
        cp.setLayout(null);
        JLabel labelWarning = new JLabel();
        labelWarning.setBounds(0, 0, 588, 20);
        labelWarning.setHorizontalAlignment(JLabel.CENTER);
        labelWarning.setText("WARNING!");
        JPanel textPanel = new JPanel(null, true);
        textPanel.add(labelWarning);
        JLabel labelCopyHiddenPassword = new JLabel();
        labelCopyHiddenPassword.setBounds(0, 20, 588, 20);
        labelCopyHiddenPassword.setHorizontalAlignment(JLabel.CENTER);
        labelCopyHiddenPassword.setText("You are trying to copy a hidden password to your clipboard.");
        textPanel.add(labelCopyHiddenPassword);
        JLabel labelContinue = new JLabel();
        labelContinue.setBounds(0, 40, 588, 20);
        labelContinue.setHorizontalAlignment(JLabel.CENTER);
        labelContinue.setText("Are you sure you want to continue?");
        textPanel.add(labelContinue);
        textPanel.setBounds(8, 8, 588, 60);
        cp.add(textPanel);
        JButton bYes = new JButton();
        bYes.setBounds(0, 0, 260, 33);
        bYes.setText("Yes, save my password to the clipboard");
        bYes.addActionListener(this::bYes_ActionPerformed);
        JPanel buttonPanel = new JPanel(null, true);
        buttonPanel.add(bYes);
        JButton bClear = new JButton();
        bClear.setBounds(266, 0, 198, 33);
        bClear.setText("No, clear the password field");
        bClear.addActionListener(this::bClear_ActionPerformed);
        buttonPanel.add(bClear);
        JButton bCancel = new JButton();
        bCancel.setBounds(468, 0, 104, 33);
        bCancel.setText("Just cancel");
        getRootPane().setDefaultButton(bCancel);
        bCancel.addAncestorListener(new RequestFocusListener());
        bCancel.addActionListener(this::bCancel_ActionPerformed);
        buttonPanel.add(bCancel);
        buttonPanel.setBounds(8, 76, 580, 92);
        buttonPanel.setOpaque(false);
        cp.add(buttonPanel);
        ApplicationIcon ai = new ApplicationIcon();
        this.setIconImages(ai.getApplicationIcon());
        setResizable(false);
        setVisible(true);
    }

    public void bYes_ActionPerformed(final ActionEvent evt) {
        ClipboardManager.copyToClipboard(inputContainer.getText());
        dispose();
    }

    public void bClear_ActionPerformed(final ActionEvent evt) {
        inputContainer.setText("");
        dispose();
    }

    public void bCancel_ActionPerformed(final ActionEvent evt) {
        dispose();
    }
}
