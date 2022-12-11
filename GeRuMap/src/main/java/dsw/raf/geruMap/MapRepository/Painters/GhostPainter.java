package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.GhostLink;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;

public class GhostPainter extends ElementPainter{

    public GhostPainter(Element element) {
        super(element);
    }


    @Override
    public void paint(Graphics2D g) {
        GhostLink temp = (GhostLink)element;
        int xFrom = (int) temp.getFrom().getX();
        int yFrom = (int) temp.getFrom().getY();
        int xTo = (int) temp.getTo().getX();
        int yTo = (int) temp.getTo().getY();
        g.setPaint(temp.getPaint());
        g.setStroke(new BasicStroke(element.getThickness()));
        g.drawLine(xFrom,yFrom,xTo,yTo);
    }

    @Override
    public boolean elementAt(Element element, Point pos) {
        return false;
    }


    }

