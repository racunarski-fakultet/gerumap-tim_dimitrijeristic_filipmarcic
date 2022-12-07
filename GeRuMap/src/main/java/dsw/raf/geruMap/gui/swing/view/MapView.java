package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.LinkPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.controller.StateMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapView extends JPanel implements Subscriber
{
    List<ElementPainter> elems;
    MapNode myMap;
    @Override
    public void update(Object var1)
    {
        if (var1 instanceof MindMap)
        {
            elems = new ArrayList<>();

            if (((MindMap)var1).getChildren() != null) {
                for (MapNode child : ((MindMap) var1).getChildren()) {
                    if (child instanceof Link)
                        elems.add(new LinkPainter((Link) child));
                    else
                        elems.add(new ThoughtPainter((Thought) child));
                }
            }

            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

        for (ElementPainter painter : elems)
            painter.paint(g2);
    }

    public MapView(MindMap map) {
        this.addMouseListener(new StateMouseListener());
        this.addMouseMotionListener(new StateMouseListener());
        this.myMap = map;
        map.subscribe(this);
        update(map);
    }
}
