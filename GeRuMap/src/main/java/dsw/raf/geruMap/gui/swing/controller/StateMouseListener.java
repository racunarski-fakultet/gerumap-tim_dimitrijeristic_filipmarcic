package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.StateManager.States.ZoomState;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import javax.swing.*;
import javax.swing.text.Position;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class StateMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    MapTreeView treeView = ((MapTreeImplementation) MainFrame.getInstance().getMapTree()).getTreeView();

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        AffineTransform at = getAt();

        System.out.println("X:"+e.getX()+" Y:"+e.getY());
        MapTreeItem TreeItemMap = MainFrame.getInstance().getMapTree().findNode(((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap());
        MainFrame.getInstance().getState_man().getCurrentState().mousePress(e.getX() ,e.getY(), TreeItemMap);
        MainFrame.getInstance().getDesktop().getSelectedComponent().repaint();
        render();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        AffineTransform at = getAt();

        MainFrame.getInstance().getState_man().getCurrentState().mouseRelease(e.getX(),e.getY());
        MainFrame.getInstance().getDesktop().getSelectedComponent().repaint();

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        AffineTransform at = getAt();

        MainFrame.getInstance().getState_man().getCurrentState().mouseDrag(e.getX(),e.getY());
        MainFrame.getInstance().getDesktop().getSelectedComponent().repaint();
    }
    private void render() {
        MapTreeItem TreeItemMap = (MainFrame.getInstance().getMapTree()).findNode(((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap());
        treeView.setSelectionPath(new TreePath(TreeItemMap.getPath()));
        SwingUtilities.updateComponentTreeUI(treeView);
        treeView.expandPath(treeView.getSelectionPath());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        AffineTransform at = getAt();

        MainFrame.getInstance().getState_man().getCurrentState().mouseScrolled(e);
    }

    private AffineTransform getAt()
    {
        return ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getAffineTransform();
    }
}
