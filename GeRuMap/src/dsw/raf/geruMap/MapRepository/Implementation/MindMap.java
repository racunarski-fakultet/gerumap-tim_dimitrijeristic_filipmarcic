package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor

public class MindMap extends MapNodeComposite
{
    private int counter =0;
    public MindMap(String name)
    {
        children = new HashMap<>();
        setName(name);
    }
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof Element;}

    public int getCounter()
    {
        counter++;
        return counter;
    }
}
