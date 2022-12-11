package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;

import java.awt.*;

public class LinkPainter extends ElementPainter{
    public LinkPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {
        Link temp=(Link)element;
        Thought from = temp.getParentThought();
        Thought to = temp.getChildThought();
        int xFrom = (int) from.getPosition().getX();
        int yFrom = (int) from.getPosition().getY();
        int xTo = (int) to.getPosition().getX();
        int yTo = (int) to.getPosition().getY();
        g.setPaint(temp.getPaint());
        g.setStroke(new BasicStroke(element.getThickness()));
        g.drawLine(xFrom,yFrom,xTo,yTo);



    }

    @Override
    public boolean elementAt(Element element, Point pos) {
        int x = pos.x;
        int y = pos.y;
        Point hit = element.getPosition();
        Dimension size = element.getSize();

        if ((x > hit.getX() - size.width/2 && x < hit.getX() + size.width/2) && (y > hit.getY() - size.height/2 && y < hit.getY() + size.height/2))
        {
            return true;
        }

        return false;
    }
}
