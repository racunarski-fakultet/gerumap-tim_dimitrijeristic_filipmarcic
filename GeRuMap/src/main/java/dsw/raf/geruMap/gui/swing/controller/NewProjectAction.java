package dsw.raf.geruMap.gui.swing.controller;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class NewProjectAction extends AbstractGeruMapAction
{
    public NewProjectAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/new.png"));
        putValue(NAME,"New Project");
        putValue(SHORT_DESCRIPTION,"New Project");
    }

    public void actionPerformed(ActionEvent e)
    {
        if(MainFrame.getInstance().getMapTree().getSelectedNode()!=null)
            MainFrame.getInstance().getMapTree().add_node(MainFrame.getInstance().getMapTree().getSelectedNode());
    }
}
