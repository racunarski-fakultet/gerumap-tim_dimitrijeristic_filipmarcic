package dsw.raf.geruMap.StateManager.States;


import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.GhostLink;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.GhostPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.gui.swing.commands.implementation.LinkCommand;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import java.awt.*;
import java.awt.event.MouseWheelEvent;

public class LinkState implements AbstractState {
    GhostLink ghostLink;
    Thought node1;
    Thought node2;
    boolean create;

    @Override
    public void mouseDrag(int x, int y) {
        this.ghostLink.setTo(new Point(x,y));


        MainFrame.getInstance().getDesktop().getSelectedComponent().repaint();
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
                    create = true;
                    break;
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
      //  MainFrame.getInstance().getDesktop().repaint();

        for (ElementPainter i : ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getElems())
        {
            if (i instanceof ThoughtPainter)
            {
                if (i.elementAt(i.getElement(),location))
                {
                   // ghostLink.setFrom(i.getElement().getPosition());
                    node2 = (Thought) i.getElement();
                    if(node1==node2)
                        node2=null;
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

        if (node2 == null || node2.equals(node1))
            return;

        Link link = new Link(node1,node2, StylePicker.getInstance().getThickness(), StylePicker.getInstance().getColorChooserOut().getColor());
        MindMap map = (MindMap) ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap();

        map.add_edge(link);

        if (map.check_cycle())
            create = false;

        map.remove_edge(link);
        map.reset_g();

        if (create)
        {
            AppCore.getInstance().getGui().getCommandManager().addCommand(new LinkCommand(link, ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap()));
            AppCore.getInstance().getGui().getCommandManager().doCommand();
        }

        node1 = null;
        node2 = null;
        create = false;
        ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).repaint();
        MindMap myMap = (MindMap) ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap();
        myMap.reset_g();
    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {

    }
}
