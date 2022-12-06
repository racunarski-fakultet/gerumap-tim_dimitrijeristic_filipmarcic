package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.GeneralPath;

@Getter
@Setter
public abstract class ElementPainter {

    protected Element element;
    protected Shape shape;

    public ElementPainter(Element element)
    {
        this.element = element;

        shape=new GeneralPath();
        ((GeneralPath)shape).moveTo(element.getPosition().x,element.getPosition().y);

        ((GeneralPath)shape).lineTo(element.getPosition().x+element.getSize().width,element.getPosition().y);

        ((GeneralPath)shape).lineTo(element.getPosition().x+element.getSize().width,element.getPosition().y+element.getSize().height);

        ((GeneralPath)shape).lineTo(element.getPosition().x,element.getPosition().y+element.getSize().height);

        ((GeneralPath)shape).closePath();
    }

    public abstract void paint(Graphics2D g);

    public abstract boolean elementAt(Element element, Point pos);


}

