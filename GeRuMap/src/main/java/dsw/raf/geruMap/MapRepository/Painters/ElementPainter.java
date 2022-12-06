package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;

import java.awt.Graphics2D;
import java.awt.Point;

public abstract class ElementPainter {

    public ElementPainter(Element element) {	}

    public abstract void paint(Graphics2D g, Element element);

    public abstract boolean elementAt(Element element, Point pos);


}

