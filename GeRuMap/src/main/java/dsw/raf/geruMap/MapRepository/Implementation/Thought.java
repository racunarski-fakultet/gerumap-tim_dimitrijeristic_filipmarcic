package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Objects;

@Getter
@Setter
public class Thought extends Element
{
//    private Point position;
//    private Dimension size;
    boolean central=false;
    public Thought(String name, MapNodeComposite parent, Point position, Dimension size, int thickness, Color paint) {
        super(name, parent,position,size,thickness,paint);
        this.position=position;
        this.size=size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Thought thought = (Thought) o;
        return position.equals(thought.position) && size.equals(thought.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, size);
    }

    @Override
    public String toString() {
        return "Thought{" + "paint=" + paint + ", thickness=" + thickness + ", position=" + position + ", size=" + size + '}';
    }
}
