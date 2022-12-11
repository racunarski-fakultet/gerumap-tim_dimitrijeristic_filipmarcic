package dsw.raf.geruMap.gui.swing.tree.model;


import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

@Getter
@Setter
public class MapTreeItem extends DefaultMutableTreeNode {

    private MapNode mapNode;
    private int id;

    public MapTreeItem(MapNode nodeModel) {
        this.mapNode = nodeModel;
        this.id = this.hashCode();
    }

    @Override
    public String toString() {
        if(mapNode != null)
            return mapNode.getName();
        return null;
    }

    public void setName(String name) {
        this.mapNode.setName(name);
    }

    public void delete()
    {
        this.mapNode.delete();
        this.removeFromParent();
    }
}
