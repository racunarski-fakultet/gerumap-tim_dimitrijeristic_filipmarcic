package dsw.raf.geruMap.gui.swing.MapRepository.Composite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode
{
    protected abstract boolean add_child(MapNode child);
    protected abstract boolean delete_child(MapNode child);

}
