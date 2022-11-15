package dsw.raf.geruMap.MapRepository.NodeFactory;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;

public class MindMapFactory extends NodeFactory{
    @Override
    public MapNode createNode(String name, MapNodeComposite parent) {
        return new MindMap(name,parent);
    }
}
