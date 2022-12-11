package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class Thought extends Element
{
    private Point position;
    private Dimension size;
    public Thought(String name, MapNodeComposite parent, Point position, Dimension size, int thickness, Paint paint) {
        super(name, parent,position,size,thickness,paint);
        this.position=position;
        this.size=size;
    }
}
