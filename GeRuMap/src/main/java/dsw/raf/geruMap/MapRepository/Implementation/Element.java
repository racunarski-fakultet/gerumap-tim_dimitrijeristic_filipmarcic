package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public abstract class Element extends MapNode
{
    private int boja;
    private int debljina;

    protected Paint paint;
    protected Stroke stroke;
    protected Dimension size;
    protected Point position;

    public Element(String name, MapNodeComposite parent, Point position, Dimension size, Stroke stroke, Paint paint)
    {
        super(name,parent);

        this.position = position;
        this.size = size;
        this.stroke = stroke;
        this.paint = paint;
    }
}
