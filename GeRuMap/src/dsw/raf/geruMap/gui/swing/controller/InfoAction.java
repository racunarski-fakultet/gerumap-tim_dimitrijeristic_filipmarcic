package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.gui.swing.view.InfoFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractGeruMapAction {
    public InfoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F12, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/info.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Informacije o autoru");
    }
    public void actionPerformed(ActionEvent arg0)
    {
        InfoFrame info_frame = InfoFrame.getInstance();
        info_frame.setVisible(true);
    }
}
