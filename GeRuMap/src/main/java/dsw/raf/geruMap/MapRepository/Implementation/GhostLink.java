package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.view.MapView;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class GhostLink extends Element implements Publisher {
    private MapView sub;
    Point from;
    Point to;
    public GhostLink(Point from, Point to,int thickness, Paint paint) {
        super("ghost", null, from, new Dimension(0,0), thickness, paint);
        this.from = from;
        this.paint = paint;
    }
    public GhostLink() {
        super(null, null, null, null, 0, null);
    }

    @Override
    public void unsubscribe(Subscriber var1) {
        this.sub.remove((MapView)var1);
    }

    @Override
    public void subscribe(Subscriber var1) {
        this.sub = (MapView) var1;
    }

    @Override
    public void publish(Object var1) {
        sub.update(var1);
    }
    public void setTo(Point point)
    {
        to = point;
        publish(this);
    }
}
