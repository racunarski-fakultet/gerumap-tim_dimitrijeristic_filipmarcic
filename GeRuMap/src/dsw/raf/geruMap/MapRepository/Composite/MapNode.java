package dsw.raf.geruMap.MapRepository.Composite;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MapNode
{
    private String name;
    private MapNodeComposite parent;

    public MapNode() {}

    public MapNode(String name, MapNodeComposite parent)
    {
        this.name = name;
        this.parent = parent;
    }
}
