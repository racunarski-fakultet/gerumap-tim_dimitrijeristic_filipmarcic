package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;

public class ProjectExplorer extends MapNodeComposite
{
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof Project;}

}
