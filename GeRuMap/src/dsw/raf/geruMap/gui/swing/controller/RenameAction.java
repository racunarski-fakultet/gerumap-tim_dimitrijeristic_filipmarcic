package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class RenameAction extends AbstractGeruMapAction {
    public RenameAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/plus.png"));
        putValue(NAME,"Rename");
        putValue(SHORT_DESCRIPTION,"Rename");
    }

    public void actionPerformed(ActionEvent e)
    {
        //JTextField edit = new JTextField(MainFrame.getInstance().getMapTree().getSelectedNode().toString());
        //MapTreeImplementation tree = (MapTreeImplementation) MainFrame.getInstance().getMapTree();
        //edit.addActionListener((ActionListener) tree.getTreeView().getCellEditor());
    }
}
