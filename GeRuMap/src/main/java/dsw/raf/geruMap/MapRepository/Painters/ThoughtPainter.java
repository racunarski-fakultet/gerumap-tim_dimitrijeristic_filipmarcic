package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;

import java.awt.*;

public class ThoughtPainter extends ElementPainter{
    public ThoughtPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, Element element) {

    }

    @Override
    public boolean elementAt(Element element, Point pos) {
        return false;
    }
}
