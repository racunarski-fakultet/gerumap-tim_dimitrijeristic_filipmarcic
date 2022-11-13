package dsw.raf.geruMap.MapRepository.Composite;

import dsw.raf.geruMap.gui.swing.tree.MapTree;
import dsw.raf.geruMap.gui.swing.tree.controller.MapTreeSelectionListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapNode mapNode = (MapNode) o;
        return name.equals(mapNode.name) && parent.equals(mapNode.parent);
    }

    public void delete()
    {
        MapNodeComposite parent = this.getParent();
        parent.delete_child(this);
    }
}
