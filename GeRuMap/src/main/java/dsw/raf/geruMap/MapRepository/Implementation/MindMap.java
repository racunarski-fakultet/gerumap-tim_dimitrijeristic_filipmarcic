package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class MindMap extends MapNodeComposite implements Publisher
{
    private int counter =0;

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

    @Override
    public String toString() {
        return "MindMap{" + "counter=" + counter + '\'' + "name=" + this.getName() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MindMap mindMap = (MindMap) o;
        return counter == mindMap.counter && children==mindMap.children;
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter,children);
    }
}
