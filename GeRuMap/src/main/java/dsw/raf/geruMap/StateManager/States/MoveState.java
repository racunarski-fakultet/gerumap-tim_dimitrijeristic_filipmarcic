package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;

public class MoveState implements AbstractState{
    Point start;
    boolean single;

    @Override
    public void mouseDrag(int x, int y)
    {
        Point end = new Point(x,y);

        Point delta = new Point(start.x - end.x, start.y - end.y);

        for (Element i : ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().getSelection())
        {
            i.getPosition().x -= delta.x;
            i.getPosition().y -= delta.y;
        }

        start = end;
       // MainFrame.getInstance().getDesktop().getSelectedComponent().repaint();
    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node)
    {
        if (((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().getSelection().isEmpty())
        {
            single = true;

            for (ElementPainter i : ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
            {
                if (i.elementAt(i.getElement(),new Point(x,y)))
                    ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().add_selected(i.getElement());

            }
        }

        start = new Point(x,y);
    }

    @Override
    public void mouseRelease(int x, int y)
    {
        if (single)
            ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().remove_all();
        single = false;
    }
}
