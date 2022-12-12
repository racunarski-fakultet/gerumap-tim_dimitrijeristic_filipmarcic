package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.*;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.*;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.controller.StateMouseListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapView extends JPanel implements Subscriber
{
    List<ElementPainter> elems;
    GhostPainter ghost;
    LassoPainter lasso;
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
                    else if(child instanceof Thought)
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
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        for (ElementPainter painter : elems)
            if (painter instanceof LinkPainter)
                painter.paint(g2);

        for (ElementPainter painter : elems)
            if (painter instanceof ThoughtPainter)
                painter.paint(g2);

        if(ghost!=null)
            ghost.paint(g2);
        if(lasso!=null)
            lasso.paint(g2);
    }

    public MapView(MindMap map) {
        this.addMouseListener(new StateMouseListener());
        this.addMouseMotionListener(new StateMouseListener());
        this.myMap = map;
        map.subscribe(this);
        ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().subscribe(this);
        update(map);
    }
}
