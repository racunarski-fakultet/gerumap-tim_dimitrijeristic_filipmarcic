package dsw.raf.geruMap.MapRepository.NodeFactory;


import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Project;

public class ProjectFactory extends NodeFactory {
    public MapNode createNode(String name, MapNodeComposite parent) {
        return new Project(name,parent);
    }
}
