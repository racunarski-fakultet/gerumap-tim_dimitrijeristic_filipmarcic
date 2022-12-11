package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.GhostLink;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;

public class GhostPainter {
    GhostLink element;

    public GhostPainter(GhostLink element) {
        this.element = element;
    }


    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawLine(element.getFrom().x, element.getFrom().y, element.getTo().x, element.getTo().y);
    }
}



