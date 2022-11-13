package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MindMap extends MapNodeComposite implements Publisher
{
    private int counter =0;
    private List<MapNode> elems = new ArrayList<MapNode>();
    private Subscriber sub;
    public MindMap(String name,MapNodeComposite parent)
    {
        super(name,parent);
    }
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof Element;}

    @Override
    public boolean add_child(MapNode child) {
        super.add_child(child);
        this.publish(this);
        return true;
    }

    @Override
    public boolean delete_child(MapNode child) {
        super.delete_child(child);
        this.publish(this);
        return true;
    }

    public int getCounter()
    {
        counter++;
        return counter;
    }

    @Override
    public void unsubscribe(Subscriber var1) {
    }

    @Override
    public void subscribe(Subscriber var1) {
        this.sub = var1;
    }

    @Override
    public void publish(Object var1) {
        if (sub != null)
            sub.update(this);
    }
}
