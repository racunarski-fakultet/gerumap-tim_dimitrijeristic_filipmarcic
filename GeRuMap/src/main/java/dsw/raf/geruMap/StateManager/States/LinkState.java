package dsw.raf.geruMap.StateManager.States;

import com.sun.tools.javac.Main;
import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.GhostLink;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.GhostPainter;
import dsw.raf.geruMap.MapRepository.Painters.LinkPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import javax.swing.text.Position;
import java.awt.*;

public class LinkState implements AbstractState {
    GhostLink ghostLink;
    Thought node1;
    Thought node2;

    @Override
    public void mouseDrag(int x, int y) {
        this.ghostLink.setTo(new Point(x,y));


        MainFrame.getInstance().getDesktop().repaint();
    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node) {
        this.ghostLink = new GhostLink(new Point(x,y));
        ghostLink.subscribe((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent());
        ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).setGhost(new GhostPainter(ghostLink));

        for (ElementPainter i : ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if (i instanceof ThoughtPainter)
            {
                if (i.elementAt(i.getElement(),ghostLink.getFrom()))
                {
                    ghostLink.setFrom(i.getElement().getPosition());
                    node1 = (Thought) i.getElement();
                }

            }
        }
       // MainFrame.getInstance().getDesktop().repaint();

    }

    @Override
    public void mouseRelease(int x, int y) {
        node2 = null;
        Point location = new Point(x,y);
        ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).setGhost(null);
        MainFrame.getInstance().getDesktop().repaint();

        for (ElementPainter i : ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if (i instanceof ThoughtPainter)
            {
                if (i.elementAt(i.getElement(),location))
                {
                    ghostLink.setFrom(i.getElement().getPosition());
                    node2 = (Thought) i.getElement();
                }

            }
        }

        for (ElementPainter i : ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if (i instanceof ThoughtPainter)
                continue;

            if (((Link)i.getElement()).getChildThought().equals(node1) && ((Link)i.getElement()).getParentThought().equals(node2)
                || ((Link)i.getElement()).getParentThought().equals(node1) && ((Link)i.getElement()).getChildThought().equals(node2))
                return;

        }

        if (node2 != null)
            MainFrame.getInstance().getMapTree().add_node(MainFrame.getInstance().getMapTree().findNode(((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap()),new Link(node1,node2,2,new Color(255,255,255)));

    }
}
