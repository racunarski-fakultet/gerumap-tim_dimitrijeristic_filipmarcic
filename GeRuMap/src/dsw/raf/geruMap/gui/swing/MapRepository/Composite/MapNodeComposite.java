package dsw.raf.geruMap.gui.swing.MapRepository.Composite;

import dsw.raf.geruMap.gui.swing.MapRepository.Implementation.Element;
import dsw.raf.geruMap.gui.swing.MapRepository.Implementation.MindMap;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode
{
    protected Map<String,MapNode> children;
    protected MapNode child_type;
    protected boolean add_child(MapNode child)
    {
        if(!(child instanceof )
        {
            return false;
        }
        else
        {
            this.children.put(child.getName(),child);
            return true;
        }
    }
    protected boolean delete_child(MapNode child)
    {
        if(!(child instanceof Element))
        {
            return false;
        }
        else
        {
            this.getChildren().remove(child.getName(),child);
            return true;
        }
    }
}
