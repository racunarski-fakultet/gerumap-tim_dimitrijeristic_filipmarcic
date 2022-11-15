package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;


import java.util.ArrayList;
import java.util.List;


public class MindMap extends MapNodeComposite implements Publisher
{
    private int counter =0;
    private List<MapNode> elems = new ArrayList<MapNode>();

    public MindMap(String name,MapNodeComposite parent)
    {
        super(name,parent);
    }
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof Element;}
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
        subscribers.add(var1);
    }

    @Override
    public void publish(Object var1) {
        if(!subscribers.isEmpty())
            for(Subscriber sub:subscribers)
                    sub.update(var1);
    }
}
