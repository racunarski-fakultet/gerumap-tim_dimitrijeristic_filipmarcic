package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;
import java.awt.event.MouseWheelEvent;

public class NavigateState implements AbstractState{

    Point start;

    @Override
    public void mouseDrag(int x, int y) {
        Point end =  new Point(x,y);

        Point delta = new Point((int) (((double)(start.x - end.x))),
                (int) (((double)(start.y - end.y))));

//        ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getAffineTransform().

        for (ElementPainter i : ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if(i instanceof ThoughtPainter)
            {
                i.getElement().getPosition().x -= delta.x;
                i.getElement().getPosition().y -= delta.y;
            }
        }

        start = end;
       // MainFrame.getInstance().getDesktop().repaint();
    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node) {
        start = new Point(x,y);
    }

    @Override
    public void mouseRelease(int x, int y) {
    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {

    }
}
