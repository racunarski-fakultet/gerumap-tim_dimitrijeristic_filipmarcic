package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;

import java.awt.*;

public class ThoughtPainter extends ElementPainter{
    public ThoughtPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {

        g.setPaint(Color.RED);

        g.setStroke(element.getStroke());
        g.draw(getShape());
        g.setPaint(element.getPaint());

        g.fill(getShape());

        if (element instanceof Thought){
            g.setPaint(Color.BLACK);
            Thought device=(Thought)element;
            g.drawString(device.getName(), (int)device.getPosition().getX()+10,
                    (int)device.getPosition().getY()+10);
        }
    }

    @Override
    public boolean elementAt(Element element, Point pos) {
        return false;
    }
}
