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
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapView extends JPanel implements Subscriber
{
    private List<ElementPainter> elems;
    private GhostPainter ghost;
    private LassoPainter lasso;
    private MapNode myMap;
    private Scrollbar vScroll = new Scrollbar(Scrollbar.VERTICAL);
    private Scrollbar hScroll = new Scrollbar(Scrollbar.HORIZONTAL);
    private int lastvalueH =1;
    private double scale = 1;

    public AffineTransform affineTransform;
    private double scaling = 1.0;
    private double translateX = 0;
    private double translateY = 0;
    private final double translateFactor = 10;
    private final double scaleFactor = 1.2;

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
        if(affineTransform==null)
            affineTransform = g2.getTransform();

        AffineTransform temp_transform = g2.getTransform();
        //g2.setTransform(affineTransform);
        //g2.setFont(new Font(g2.getFont().getName(),g2.getFont().getStyle(), (int) (g2.getFont().getSize()*((MindMap)myMap).scaling)));

        for (ElementPainter painter : elems)
            if (painter instanceof LinkPainter)
                painter.paint(g2);

        for (ElementPainter painter : elems)
            if (painter instanceof ThoughtPainter)
                painter.paint(g2);

        g2.setTransform(temp_transform);

        if(ghost!=null)
            ghost.paint(g2);
        if(lasso!=null)
            lasso.paint(g2);

    }

    public MapView(MindMap map) {
      //  initializeGUI();

        this.addMouseListener(new StateMouseListener());
        this.addMouseMotionListener(new StateMouseListener());
        this.addMouseWheelListener(new StateMouseListener());
        this.myMap = map;
        map.subscribe(this);
        ((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().subscribe(this);
        update(map);
    }
    private void initializeGUI()
    {
        this.setLayout(new BorderLayout());
        this.add(vScroll,BorderLayout.EAST);
        this.add(hScroll,BorderLayout.SOUTH);
        hScroll.setBackground(Color.white);
        vScroll.setBackground(Color.white);
    }
}
