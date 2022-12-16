package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Lasso;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.LassoPainter;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;
import java.awt.event.MouseWheelEvent;

public class SelectionState implements AbstractState{
    Lasso lasso;

    @Override
    public void mouseDrag(int x, int y)
    {
        lasso.setEnd(new Point(x,y));

       // MainFrame.getInstance().getDesktop().repaint();
    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node)
    {
        this.lasso = new Lasso(new Point(x,y));
        lasso.subscribe((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent());
        ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).setLasso(new LassoPainter(lasso));
    }

    @Override
    public void mouseRelease(int x, int y)
    {
        ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().remove_all();
        lasso.setEnd(new Point(x,y));

        if (lasso.getStart().x > lasso.getEnd().x)
        {
            int temp = lasso.getStart().x;
            lasso.getStart().x = lasso.getEnd().x;
            lasso.getEnd().x = temp;
        }
        if (lasso.getStart().y > lasso.getEnd().y)
        {
            int temp = lasso.getStart().y;
            lasso.getStart().y = lasso.getEnd().y;
            lasso.getEnd().y = temp;
        }

        for (ElementPainter i : ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if (lasso.contains(i))
                ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().add_selected(i.getElement());

        }

        ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).setLasso(null);
       // MainFrame.getInstance().getDesktop().repaint();
    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {

    }
}
