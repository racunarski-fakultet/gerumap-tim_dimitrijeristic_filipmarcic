package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;

import java.awt.*;

public class LinkPainter extends ElementPainter{
    public LinkPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {

    }

    @Override
    public boolean elementAt(Element element, Point pos) {
        return false;
    }
}
