package dsw.raf.geruMap.gui.swing.tree;


import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;

import javax.swing.*;

public interface MapTree {

    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void add_node(MapTreeItem parent);
    void delete_node(MapTreeItem node);
    void rename_node(MapTreeItem node);
    MapTreeItem getSelectedNode();

}
