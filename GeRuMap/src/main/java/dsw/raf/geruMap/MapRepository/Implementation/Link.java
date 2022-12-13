package dsw.raf.geruMap.MapRepository.Implementation;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Random;

@Getter
@Setter
public class Link extends Element
{
    private Thought parentThought;
    private Thought childThought;

    public Link(Thought parentThought, Thought childThought,int thickness, Paint paint)
    {
        super(parentThought.getName()+"---"+childThought.getName(), (MapNodeComposite) ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap(),null,null,thickness,paint);
        this.parentThought=parentThought;
        this.childThought=childThought;
    }

}
