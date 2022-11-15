package dsw.raf.geruMap.MapRepository;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.MapRepository.NodeFactory.ElementFactory;
import dsw.raf.geruMap.MapRepository.NodeFactory.MindMapFactory;
import dsw.raf.geruMap.MapRepository.NodeFactory.NodeFactory;
import dsw.raf.geruMap.MapRepository.NodeFactory.ProjectFactory;

public class FactoryUtils {

     NodeFactory projectFactory = new ProjectFactory();
     NodeFactory mindMapFactory = new MindMapFactory();
     NodeFactory elementFactory = new ElementFactory();
    public  MapNode createNode(MapNodeComposite parent)
    {
        if (parent instanceof ProjectExplorer)
           return projectFactory.createNode(Double.toString(Math.random()),parent);
        if (parent instanceof Project)
           return mindMapFactory.createNode(Double.toString(Math.random()),parent);
        if (parent instanceof MindMap)
           return elementFactory.createNode(Integer.toString(((MindMap)parent).getCounter()),parent);
        return null;
    }
}
