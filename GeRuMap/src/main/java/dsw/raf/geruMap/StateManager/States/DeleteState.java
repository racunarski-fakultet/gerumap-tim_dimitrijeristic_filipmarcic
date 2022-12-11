package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.LinkPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
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
        ElementPainter temp = null;

        for (ElementPainter i : ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if (i.elementAt(i.getElement(),new Point(x,y)))
            {
                System.out.println("deleted");
                temp = i;
            }
        }

        if (temp instanceof ThoughtPainter)
        {
            for (ElementPainter i : ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
            {
                if (i instanceof LinkPainter)
                {
                    if (((Link)i.getElement()).getParentThought().equals(temp.getElement()) || ((Link)i.getElement()).getChildThought().equals(temp.getElement()))
                    {
                        MainFrame.getInstance().getMapTree().findNode(i.getElement()).delete();
                    }
                }
            }
        }

        MainFrame.getInstance().getMapTree().findNode(temp.getElement()).delete();
    }

    @Override
    public void mouseRelease(int x, int y) {

    }
}
