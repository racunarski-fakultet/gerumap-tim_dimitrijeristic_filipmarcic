package dsw.raf.geruMap.gui.swing.tree;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.gui.swing.tree.controller.TreeItemMouseListener;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.Iterator;

@Getter
@Setter
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
        treeView.addMouseListener(new TreeItemMouseListener());
        return treeView;
    }

    @Override
    public void add_node(MapTreeItem parent)
    {

        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;

        MapNode child = createChild((MapNodeComposite) parent.getMapNode());
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).add_child(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void delete_node(MapTreeItem node) {

        if (!(node.getMapNode() instanceof MapNodeComposite)) { removeSelf(node);}

        Iterator<TreeNode> iterator = node.children().asIterator();

        while(iterator.hasNext())
        {
            MapTreeItem item = (MapTreeItem)iterator.next();
            removeSelf(item);
        }
        node.removeFromParent();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void rename_node(MapTreeItem node) {


    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }
    private void removeSelf(MapTreeItem node)
    {
        MapNodeComposite parent = node.getMapNode().getParent();
        parent.delete_child(node.getMapNode());
        node.removeFromParent();
    }

    private MapNode createChild(MapNodeComposite parent)
    {
        if (parent instanceof ProjectExplorer)
            return new Project(Double.toString(Math.random()),parent);
        if (parent instanceof Project)
            return new MindMap(Double.toString(Math.random()),parent);
        if (parent instanceof MindMap)
            return new Element(Integer.toString(((MindMap)getSelectedNode().getMapNode()).getCounter()),parent);
        return null;
    }

}
