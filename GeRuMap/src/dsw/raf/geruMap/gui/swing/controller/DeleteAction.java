package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractGeruMapAction{

    public DeleteAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke((char) KeyEvent.VK_DELETE));
        putValue(SMALL_ICON, loadIcon("images/log-out.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getMapTree().delete_node(MainFrame.getInstance().getMapTree().getSelectedNode());
    }
}
