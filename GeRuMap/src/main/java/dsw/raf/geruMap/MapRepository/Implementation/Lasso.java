package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.LinkPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.view.MapView;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Line2D;

@Getter
@Setter
public class Lasso implements Publisher
{
    private Point start;
    private Point end;
    private MapView sub;

    public Lasso(Point start)
    {
        this.start = start;
        this.end = start;
    }

    @Override
    public void unsubscribe(Subscriber var1)
    {
        this.sub = null;
    }

    @Override
    public void subscribe(Subscriber var1)
    {
        this.sub = (MapView) var1;
    }

    @Override
    public void publish(Object var1)
    {
        sub.update(this);
    }

    public boolean contains(ElementPainter painter)
    {
        if (painter instanceof ThoughtPainter)
        {
            Thought element = (Thought) painter.getElement();
            Point epos = element.getPosition();
            Dimension esize = element.getSize();

            //granice
            int left_x = epos.x - esize.width/2;
            int right_x = epos.x + esize.width/2;
            int up_y = epos.y - esize.height/2;
            int down_y = epos.y + esize.height/2;

            if ((left_x > start.x && left_x < end.x || right_x > start.x && right_x < end.x) && (up_y > start.y && up_y < end.y || down_y > start.y && down_y < end.y))
                return true;
            else return painter.elementAt(element, start);
        }
        else if(painter instanceof LinkPainter)
        {
            Link element = (Link)painter.getElement();
            Line2D link = new Line2D.Double(element.getChildThought().getPosition().getX(),element.getChildThought().getPosition().getY(),element.getParentThought().getPosition().getX(),element.getParentThought().getPosition().getY());

            Line2D top=new Line2D.Double(start.x, start.y, end.x,start.y);
            Line2D bot=new Line2D.Double(start.x, end.y, end.x,end.y);
            Line2D left=new Line2D.Double(start.x, start.y, start.x,end.y);
            Line2D right=new Line2D.Double(end.x, start.y, start.x,end.y);

            if((painter.elementAt(element,start)))
                return true;
            else return link.intersectsLine(top) || link.intersectsLine(bot) || link.intersectsLine(right) || link.intersectsLine(left);
        }
        return false;
    }

}
