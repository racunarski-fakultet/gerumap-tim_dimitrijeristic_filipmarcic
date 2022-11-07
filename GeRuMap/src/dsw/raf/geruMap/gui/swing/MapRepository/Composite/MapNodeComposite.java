package dsw.raf.geruMap.gui.swing.MapRepository.Composite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode
{
    private boolean add_child(MapNode child)
    {
        return true;
    }
    private boolean delete_child(MapNode child)
    {
        return true;
    }
}
