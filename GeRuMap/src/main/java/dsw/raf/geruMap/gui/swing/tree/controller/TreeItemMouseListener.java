package dsw.raf.geruMap.gui.swing.tree.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TreeItemMouseListener implements MouseListener {
    private MapRepositoryImpl maprep;
    public TreeItemMouseListener() {
        maprep= (MapRepositoryImpl) AppCore.getInstance().getRep();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2)
        {

            MapTreeView treeView = ((MapTreeImplementation) MainFrame.getInstance().getMapTree()).getTreeView();
            treeView.expandPath(treeView.getSelectionPath());

            if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)
            {
                maprep.setSelectedProj((Project)MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode());
            }
            if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap)
            {
                System.out.println("MOUSELISTENER");
               // maprep.publish(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode());

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
