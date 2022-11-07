package dsw.raf.geruMap.gui.swing.MapRepository.Implementation;

import dsw.raf.geruMap.gui.swing.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.gui.swing.MapRepository.Composite.MapNodeComposite;

public class Project extends MapNodeComposite
{
    @Override
    protected boolean add_child(MapNode child)
    {
        if(!(child instanceof MindMap))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    protected boolean delete_child(MapNode child)
    {
        return false;
    }
}
