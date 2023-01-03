package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.LinkPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.gui.swing.commands.implementation.DeleteCommand;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements AbstractState {


    @Override
    public void mouseDrag(int x, int y) {

    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node)
    {
        List<MapNode> tempList = new ArrayList<>();
        if(((MapRepositoryImpl) AppCore.getInstance().getRep()).getMapSelection().getSelection().isEmpty())
        {
            ElementPainter temp = null;

            for (ElementPainter i : ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
            {
                if (i.elementAt(i.getElement(),new Point(x,y)))
                {
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
                            tempList.add(i.getElement());
                        }
                    }
                }
            }
            if (temp != null)
            {
                tempList.add(temp.getElement());
            }

        }
        else
        {
            for(Element temp:((MapRepositoryImpl) AppCore.getInstance().getRep()).getMapSelection().getSelection())
            {
                if (temp instanceof Thought)
                {
                    for (ElementPainter i : ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
                    {
                        if (i instanceof LinkPainter)
                        {
                            if (((Link)i.getElement()).getParentThought().equals(temp) || ((Link)i.getElement()).getChildThought().equals(temp))
                            {
                                tempList.add(i.getElement());

                            }
                        }
                    }
                }
                if (temp != null)
                    tempList.add(temp);
            }
            ((MapRepositoryImpl) AppCore.getInstance().getRep()).getMapSelection().remove_all();
        }


        if (!tempList.isEmpty())
        {
            AppCore.getInstance().getGui().getCommandManager().addCommand(new DeleteCommand(node,tempList));
            AppCore.getInstance().getGui().getCommandManager().doCommand();
        }
    }

    @Override
    public void mouseRelease(int x, int y) {

    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {

    }
}
