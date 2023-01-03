package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.LinkPainter;
import dsw.raf.geruMap.gui.swing.commands.implementation.MoveCommand;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;
import java.awt.event.MouseWheelEvent;

public class MoveState implements AbstractState{
    Point start;
    Point origin;
    boolean single;

    @Override
    public void mouseDrag(int x, int y)
    {
        Point end = new Point(x,y);

        Point delta = new Point(start.x - end.x, start.y - end.y);
        if(single&&!(((MapRepositoryImpl) AppCore.getInstance().getRep()).getMapSelection().getSelection().isEmpty())
                && ((MapRepositoryImpl) AppCore.getInstance().getRep()).getMapSelection().getSelection().get(0) instanceof Link temp)
        {
            Thought child = temp.getChildThought();
            Thought parent = temp.getParentThought();

            child.getPosition().x -= delta.x;
            child.getPosition().y -= delta.y;
            parent.getPosition().x -= delta.x;
            parent.getPosition().y -= delta.y;
            start = end;
            return;
        }

        for (Element i : ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().getSelection())
        {
            if(i instanceof Thought)
            {
                i.getPosition().x -= delta.x;
                i.getPosition().y -= delta.y;
            }

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
                {
                    ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().add_selected(i.getElement());

                    if (i instanceof LinkPainter)
                    {
                        Link temp = (Link) i.getElement();

                        ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().add_selected(temp.getParentThought());
                        ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().add_selected(temp.getChildThought());
                    }
                }
            }
        }

        start = new Point(x,y);
        origin = start;
    }

    @Override
    public void mouseRelease(int x, int y)
    {
        if (((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().getSelection().isEmpty())
            return;

        Point delta = new Point(start.x - origin.x, start.y - origin.y);

        AppCore.getInstance().getGui().getCommandManager().addCommand(new MoveCommand(((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().getSelection(), delta));
        AppCore.getInstance().getGui().getCommandManager().doCommand();

        if (single)
            ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().remove_all();
        single = false;
    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {

    }
}
