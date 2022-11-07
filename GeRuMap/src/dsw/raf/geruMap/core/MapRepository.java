package dsw.raf.geruMap.core;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;

public interface MapRepository
{
    ProjectExplorer getProjectExplorer();

}
