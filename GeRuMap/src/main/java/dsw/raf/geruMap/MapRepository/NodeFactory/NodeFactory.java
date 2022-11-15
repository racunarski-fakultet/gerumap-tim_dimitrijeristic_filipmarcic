package dsw.raf.geruMap.MapRepository.NodeFactory;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;

public abstract class NodeFactory  {
    public abstract MapNode createNode(String name, MapNodeComposite parent);

}
