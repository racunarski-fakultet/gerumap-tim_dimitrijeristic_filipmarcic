package dsw.raf.geruMap.gui.swing.tree;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
import dsw.raf.geruMap.gui.swing.tree.controller.TreeItemMouseListener;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.*;

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
    public void delete_node(MapTreeItem node)
    {
        Queue<MapTreeItem> que = new LinkedList<MapTreeItem>();
        que.add(node);

        while(!que.isEmpty())
        {
            MapTreeItem temp = que.remove();
            Iterator<TreeNode> iter = temp.children().asIterator();

            while(iter.hasNext())
                que.add((MapTreeItem) iter.next());
            temp.delete();
        }
        node.delete();
        //???
        treeView.getSelectionModel().removeSelectionPath(treeView.getSelectionPath());
        render();
    }

    @Override
    public void rename_node(MapTreeItem node) {
        MapNode node1 = node.getMapNode();
        String name="";
        while(name.isEmpty())
        {
            name = JOptionPane.showInputDialog("Podesi ime");
            if(name==null)
                return;
            if(name.isEmpty())
                AppCore.getInstance().getGenerator().generateMessage("Ime ne moze biti prazno", MessageTypes.ERROR_MESSAGE);
        }
        node.setName(name);
        if(node1 instanceof Project)
            ((MapRepositoryImpl)AppCore.getInstance().getRep()).publish(node);
        else
            ((MapRepositoryImpl)AppCore.getInstance().getRep()).publish(node.getParent());

    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }
    private void render()
    {
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
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
