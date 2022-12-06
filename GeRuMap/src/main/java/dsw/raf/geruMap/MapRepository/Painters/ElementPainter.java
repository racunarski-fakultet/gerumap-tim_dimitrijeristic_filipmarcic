package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;

import java.awt.Graphics2D;
import java.awt.Point;

public abstract class ElementPainter {

    private Element element;

    public ElementPainter(Element element) {this.element = element;}

    public abstract void paint(Graphics2D g);

    public abstract boolean elementAt(Element element, Point pos);


}

