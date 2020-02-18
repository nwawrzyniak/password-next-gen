package nwawsoft.pwng.ui;

import nwawsoft.util.ClipboardManager;
import nwawsoft.util.DebugPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CopyHiddenPasswordToClipboardDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonClear;
    private JButton buttonCancel;

    private MainFrame mf;
    private String text;

    public CopyHiddenPasswordToClipboardDialog(final MainFrame mf, final String text) {
        setTitle("Password Security Notification");
        this.mf = mf;
        this.text = text;
        setContentPane(contentPane);
        /*Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);*/
        // width="615" height="297"
        setModal(true);
        //hiddenPasswordClipboardWarning.setBounds(0, 0, 200, 100);
        //contentPane.add(hiddenPasswordClipboardWarning);
        getRootPane().setDefaultButton(buttonCancel);
        buttonOK.addActionListener(e -> onOK());
        buttonClear.addActionListener(e -> onClear());
        buttonCancel.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setResizable(false);
    }

    private void onOK() {
        ClipboardManager.copyToClipboard(mf.getInputContainer().getText());
        dispose();
    }

    private void onClear() {
        mf.getInputContainer().setText("");
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
