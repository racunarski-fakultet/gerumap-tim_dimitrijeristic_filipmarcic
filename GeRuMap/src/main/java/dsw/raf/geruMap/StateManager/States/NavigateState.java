package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;

public class NavigateState implements AbstractState{

    Point start;

    @Override
    public void mouseDrag(int x, int y) {
        Point end = new Point(x,y);

        Point delta = new Point(start.x - end.x, start.y - end.y);

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
}
