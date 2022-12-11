package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;

public class DeleteState implements AbstractState {


    @Override
    public void mouseDrag(int x, int y) {

    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node)
    {
        for (ElementPainter i : ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if (i.elementAt(i.getElement(),new Point(x,y)))
            {
                System.out.println("deleted");
                MainFrame.getInstance().getMapTree().findNode(i.getElement()).delete();
            }

        }
    }

    @Override
    public void mouseRelease(int x, int y) {

    }
}
