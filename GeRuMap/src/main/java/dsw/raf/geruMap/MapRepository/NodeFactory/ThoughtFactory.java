package dsw.raf.geruMap.MapRepository.NodeFactory;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;

public class ThoughtFactory extends NodeFactory{
    public MapNode createNode(String name, MapNodeComposite parent) {
        return new Thought(name,parent);
    }
}
