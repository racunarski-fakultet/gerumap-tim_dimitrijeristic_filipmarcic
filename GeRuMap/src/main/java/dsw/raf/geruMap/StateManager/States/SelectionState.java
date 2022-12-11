package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;

public class SelectionState implements AbstractState{
    @Override
    public void mouseClick(int x, int y) {
        System.out.println("SELECTION STATE CLICK");
    }

    @Override
    public void mouseDrag(int x, int y) {

    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node)
    {
        ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().remove_all();

        for (ElementPainter i : ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if (i.elementAt(i.getElement(),new Point(x,y)))
                ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().add_selected(i.getElement());

        }
        MainFrame.getInstance().getDesktop().repaint();
    }

    @Override
    public void mouseRelease(int x, int y) {

    }
}
