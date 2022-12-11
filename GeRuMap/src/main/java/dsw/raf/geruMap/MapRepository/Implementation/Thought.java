package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class Thought extends Element
{
    public Thought(String name, MapNodeComposite parent, Point position, Dimension size, Stroke stroke, Paint paint) {
        super(name, parent, position, size, stroke, paint);
    }
}
