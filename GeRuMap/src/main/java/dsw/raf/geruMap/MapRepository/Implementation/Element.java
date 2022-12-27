package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;

@Getter
@Setter
public abstract class Element extends MapNode
{
    protected Color paint;
    protected int thickness;
    protected Dimension size;
    protected Point position;

    public Element(String name, MapNodeComposite parent, Point position, Dimension size, int thickness, Color paint)
    {
        super(name,parent);

        this.position = position;
        this.size = size;
        this.thickness = thickness;
        this.paint = paint;
    }
}
