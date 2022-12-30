package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class StateMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    final MapTreeView treeView = ((MapTreeImplementation) MainFrame.getInstance().getMapTree()).getTreeView();

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        AffineTransform at = getAt();
        Point2D pt = e.getPoint();
        try {
            at.inverseTransform(pt,pt);
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
        }

        MapTreeItem TreeItemMap = MainFrame.getInstance().getMapTree().findNode(((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap());
        MainFrame.getInstance().getState_man().getCurrentState().mousePress((int)pt.getX() ,(int)pt.getY(), TreeItemMap);
        MainFrame.getInstance().getDesktop().getSelectedComponent().repaint();
        render();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        AffineTransform at = getAt();
        Point2D pt = e.getPoint();
        try {
            at.inverseTransform(pt,pt);
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
        }

        MainFrame.getInstance().getState_man().getCurrentState().mouseRelease((int)pt.getX(),(int)pt.getY());
        MainFrame.getInstance().getDesktop().getSelectedComponent().repaint();

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        AffineTransform at = getAt();
        Point2D pt = e.getPoint();
        try {
            at.inverseTransform(pt,pt);
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
        }

        MainFrame.getInstance().getState_man().getCurrentState().mouseDrag((int)pt.getX(),(int)pt.getY());
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
        MainFrame.getInstance().getState_man().getCurrentState().mouseScrolled(e);
    }

    private AffineTransform getAt()
    {
        return ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getAffineTransform();
    }
}
