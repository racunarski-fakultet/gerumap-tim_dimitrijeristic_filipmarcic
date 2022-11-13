package dsw.raf.geruMap.gui.swing.tree.model;


import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

@Getter
@Setter
public class MapTreeItem extends DefaultMutableTreeNode {

    private MapNode mapNode;

    public MapTreeItem(MapNode nodeModel) {
        this.mapNode = nodeModel;
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
