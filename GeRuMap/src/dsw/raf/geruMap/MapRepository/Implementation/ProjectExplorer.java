package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;

import java.util.HashMap;

public class ProjectExplorer extends MapNodeComposite
{
    public ProjectExplorer()
    {
        setName("ProjectExplorer");
        children = new HashMap<>();
    }
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof Project;}

}
