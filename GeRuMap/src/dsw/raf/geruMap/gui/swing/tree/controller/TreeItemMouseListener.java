package dsw.raf.geruMap.gui.swing.tree.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TreeItemMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2)
        {
            if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)
            {
                ((MapRepositoryImpl)AppCore.getInstance().getRep()).publish(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode());
            }
            if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap)
            {

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
