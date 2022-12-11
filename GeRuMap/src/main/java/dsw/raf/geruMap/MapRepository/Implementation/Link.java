package dsw.raf.geruMap.MapRepository.Implementation;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class Link extends Element
{
    private Thought parentThought;
    private Thought childThought;

    public Link(Thought parentThought, Thought childThought,int thickness, Paint paint)
    {
        super(null,null,null,null,thickness,paint);
        this.parentThought=parentThought;
        this.childThought=childThought;
    }

}
