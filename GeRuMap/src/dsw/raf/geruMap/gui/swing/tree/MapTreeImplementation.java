package dsw.raf.geruMap.gui.swing.tree;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeImplementation implements MapTree
{

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer)
    {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    @Override
    public void add_node(MapTreeItem parent)
    {

        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;

        MapNode child = createChild(parent.getMapNode());
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).add_child(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void delete_node(MapTreeItem node) {

    }

    @Override
    public void rename_node(MapTreeItem node) {

    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    private MapNode createChild(MapNode parent)
    {
        if (parent instanceof ProjectExplorer)
            return new Project(Double.toString(Math.random()));
        if (parent instanceof Project)
            return new MindMap(Double.toString(Math.random()));
        if (parent instanceof MindMap)
            return new Element(Integer.toString(((MindMap)getSelectedNode().getMapNode()).getCounter()));
        return null;
    }

}
