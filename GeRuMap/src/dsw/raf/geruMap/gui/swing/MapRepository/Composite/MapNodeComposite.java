package dsw.raf.geruMap.gui.swing.MapRepository.Composite;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode
{
    protected Map<String,MapNode> children;
    protected abstract boolean add_child(MapNode child);
    protected abstract boolean delete_child(MapNode child);
}
