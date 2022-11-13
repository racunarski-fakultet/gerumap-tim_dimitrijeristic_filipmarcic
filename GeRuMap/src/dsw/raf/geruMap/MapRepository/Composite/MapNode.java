package dsw.raf.geruMap.MapRepository.Composite;

import dsw.raf.geruMap.gui.swing.tree.MapTree;
import dsw.raf.geruMap.gui.swing.tree.controller.MapTreeSelectionListener;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
