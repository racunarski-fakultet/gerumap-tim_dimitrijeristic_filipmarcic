package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.gui.swing.view.MainFrame;

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
        MainFrame.getInstance().getState_man().getCurrentState().mousePress(e.getX(),e.getY());
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
