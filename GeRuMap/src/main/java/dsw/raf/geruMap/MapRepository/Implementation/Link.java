package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;

import java.awt.*;

public class Link extends Element
{

    public Link(String name, MapNodeComposite parent, Point position, Dimension size, Stroke stroke, Paint paint)
    {
        super(name, parent, position, size, stroke, paint);
    }
}
