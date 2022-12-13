package dsw.raf.geruMap.MapRepository;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.MapRepository.NodeFactory.ThoughtFactory;
import dsw.raf.geruMap.MapRepository.NodeFactory.MindMapFactory;
import dsw.raf.geruMap.MapRepository.NodeFactory.NodeFactory;
import dsw.raf.geruMap.MapRepository.NodeFactory.ProjectFactory;

public class FactoryUtils {

     static NodeFactory projectFactory = new ProjectFactory();
     static NodeFactory mindMapFactory = new MindMapFactory();
     static NodeFactory thoughtFactory = new ThoughtFactory();
    public static MapNode createNode(MapNodeComposite parent)
    {
        if (parent instanceof ProjectExplorer)
           return projectFactory.createNode("Project "+ ((ProjectExplorer) parent).getCounter(),parent);
        if (parent instanceof Project)
           return mindMapFactory.createNode("Mind map "+ ((Project) parent).getCounter(),parent);
        if (parent instanceof MindMap)
           return thoughtFactory.createNode(Integer.toString(((MindMap)parent).getCounter()),parent);
        return null;
    }
}
