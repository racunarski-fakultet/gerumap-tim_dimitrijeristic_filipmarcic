package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AuthorAction extends AbstractGeruMapAction{
    public AuthorAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/plus.png"));
        putValue(NAME,"Set author");
        putValue(SHORT_DESCRIPTION,"Set author");
    }

    public void actionPerformed(ActionEvent e)
    {
        MapNode node = MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        if(node instanceof Project)
        {
            ((Project) node).setAutor(JOptionPane.showInputDialog("Unesi autora"));
            ((MapRepositoryImpl) AppCore.getInstance().getRep()).publish(node);
        }


    }
}
