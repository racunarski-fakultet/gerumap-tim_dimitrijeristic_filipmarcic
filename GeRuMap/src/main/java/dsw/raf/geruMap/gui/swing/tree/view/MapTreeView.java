package dsw.raf.geruMap.gui.swing.tree.view;

import dsw.raf.geruMap.gui.swing.tree.controller.MapTreeCellEditor;
import dsw.raf.geruMap.gui.swing.tree.controller.MapTreeSelectionListener;


import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeView extends JTree {


    public MapTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        MapTreeCellRenderer ruTreeCellRenderer = new MapTreeCellRenderer();
        addTreeSelectionListener(new MapTreeSelectionListener());
        setCellEditor(new MapTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
    }
}
