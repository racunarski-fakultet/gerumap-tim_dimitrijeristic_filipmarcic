package dsw.raf.geruMap.MapRepository.Composite;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode
{
    protected Map<String,MapNode> children;
    public MapNodeComposite(String name,MapNodeComposite parent)
    {
        super(name,parent);
        children = new HashMap<>();
    }
    protected abstract boolean allowsChild(MapNode child);

    public boolean add_child(MapNode child)
    {
        if(!this.allowsChild(child))
        {
            return false;
        }
        else
        {
            this.children.put(child.getName(),child);
            return true;
        }

    }
    public boolean delete_child(MapNode child)
    {
        if(!this.allowsChild(child))
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
