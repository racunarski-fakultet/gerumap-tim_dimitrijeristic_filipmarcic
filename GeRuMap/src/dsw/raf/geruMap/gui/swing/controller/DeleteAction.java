package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
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
        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof ProjectExplorer)
        {
            AppCore.getInstance().getGenerator().generateMessage("ProjectExplorer ne moze biti izbrisan", MessageTypes.ERROR_MESSAGE);
            return;
        }

        MainFrame.getInstance().getMapTree().delete_node(MainFrame.getInstance().getMapTree().getSelectedNode());
    }
}
