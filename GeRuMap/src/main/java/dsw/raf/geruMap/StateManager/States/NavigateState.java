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

    Point startPoint;
    int counter;

    @Override
    public void mouseDrag(int x, int y) {

        for (ElementPainter i : ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if(i instanceof ThoughtPainter)
            {
                Point newPoint = i.getElement().getPosition();
                int dx = (x - startPoint.x)/20;
                int dy = (y - startPoint.y)/20;
                newPoint.x= newPoint.x+dx;
                newPoint.y= newPoint.y+dy;
                i.getElement().setPosition(newPoint);
            }
        }
        MainFrame.getInstance().getDesktop().repaint();
    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node) {
        startPoint = new Point(x,y);
        counter=0;
    }

    @Override
    public void mouseRelease(int x, int y) {
         counter = 0;
    }
}
