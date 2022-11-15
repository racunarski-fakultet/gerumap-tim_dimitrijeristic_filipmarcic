package dsw.raf.geruMap.MapRepository.NodeFactory;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Element;

public class ElementFactory extends NodeFactory{
    public MapNode createNode(String name, MapNodeComposite parent) {
        return new Element(name,parent);
    }
}
