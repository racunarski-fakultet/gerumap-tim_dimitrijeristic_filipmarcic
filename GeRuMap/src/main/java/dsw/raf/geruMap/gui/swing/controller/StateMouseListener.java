package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import dsw.raf.geruMap.gui.swing.view.ProjectView;
import dsw.raf.geruMap.gui.swing.view.TabbedPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class StateMouseListener implements MouseListener, MouseMotionListener {
    @Override
    public void mouseClicked(MouseEvent e) {
       // System.out.println("MOUSE CLICKED");
        MainFrame.getInstance().getState_man().getCurrentState().mouseClick(e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // System.out.println("MOUSE PRESSED");
//        int a = MainFrame.getInstance().getDesktop().getSelectedIndex();
//        MapTreeImplementation map = (MapTreeImplementation) MainFrame.getInstance().getMapTree();
//        MapRepositoryImpl rep = ((MapRepositoryImpl) AppCore.getInstance().getRep());
        MapTreeItem item = ((MapTreeImplementation) MainFrame.getInstance().getMapTree()).findNode(((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap());



        MainFrame.getInstance().getState_man().getCurrentState().mousePress(e.getX(),e.getY(),item);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getState_man().getCurrentState().mouseRelease(e.getX(),e.getY());
    }
    @Override
    public void mouseDragged(MouseEvent e) {
       // System.out.println("MOUSE DRAGGED");
        MainFrame.getInstance().getState_man().getCurrentState().mouseDrag(e.getX(),e.getY());
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
}
