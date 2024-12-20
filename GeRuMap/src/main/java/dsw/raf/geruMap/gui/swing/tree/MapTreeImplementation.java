package dsw.raf.geruMap.gui.swing.tree;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.FactoryUtils;
import dsw.raf.geruMap.MapRepository.Implementation.*;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
import dsw.raf.geruMap.gui.swing.tree.controller.TreeItemMouseListener;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.*;

@Getter
@Setter
public class MapTreeImplementation implements MapTree
{

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    public MapTreeItem findNode(MapNode node)
    {
        MapTreeItem temp = (MapTreeItem) ((MapTreeImplementation)MainFrame.getInstance().getMapTree()).getTreeModel().getRoot();
        Queue<MapTreeItem> q = new ArrayDeque<>();
        q.add(temp);

        while (!q.isEmpty())
        {
            MapTreeItem cur = q.poll();

            if (cur.getMapNode().equals(node))
                return cur;

            if (cur != null)
            {
                for (int i = 0; i < cur.getChildCount(); i++)
                {
                    q.add((MapTreeItem) cur.getChildAt(i));
                }
            }
        }

        return null;
    }

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
        //MapNode child =((MapRepositoryImpl)AppCore.getInstance().getRep()).getFactoryUtils().createNode((MapNodeComposite) parent.getMapNode());
        MapNode child = FactoryUtils.createNode((MapNodeComposite) parent.getMapNode());
        MapTreeItem item = new MapTreeItem(child);
        parent.add(item);

        ((MapNodeComposite) parent.getMapNode()).add_child(child);
        treeView.expandPath(treeView.getSelectionPath());
        render();
    }

    public void add_node(MapTreeItem parent,MapNode child)
    {

        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;
        //MapNode child =((MapRepositoryImpl)AppCore.getInstance().getRep()).getFactoryUtils().createNode((MapNodeComposite) parent.getMapNode());
       // MapNode child = FactoryUtils.createNode((MapNodeComposite) parent.getMapNode());
        MapTreeItem item = new MapTreeItem(child);

        if(((MapNodeComposite) parent.getMapNode()).add_child(child))
            parent.add(item);


        treeView.expandPath(treeView.getSelectionPath());
        render();
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
        if(node.getMapNode().equals(((MapRepositoryImpl)AppCore.getInstance().getRep()).getSelectedProj()))
        {
           // ((MapRepositoryImpl) AppCore.getInstance().getRep()).publish(node.getMapNode().getParent());
            ((MapRepositoryImpl) AppCore.getInstance().getRep()).setSelectedProj(null);
        }
        //???
        //treeView.getSelectionModel().removeSelectionPath(treeView.getSelectionPath());
        render();
    }

    @Override
    public void rename_node(MapTreeItem node) {
       // MapNode node1 = node.getMapNode();
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

        //((MapRepositoryImpl)AppCore.getInstance().getRep()).publish(((MapRepositoryImpl)AppCore.getInstance().getRep()).getSelectedProj());

    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }
    private void render()
    {
       // treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

//    private MapNode createChild(MapNodeComposite parent)
//    {
//
//        if (parent instanceof ProjectExplorer)
//            return new Project(Double.toString(Math.random()),parent);
//        if (parent instanceof Project)
//            return new MindMap(Double.toString(Math.random()),parent);
//        if (parent instanceof MindMap)
//            return new Element(Integer.toString(((MindMap)getSelectedNode().getMapNode()).getCounter()),parent);
//        return null;
//    }

}
