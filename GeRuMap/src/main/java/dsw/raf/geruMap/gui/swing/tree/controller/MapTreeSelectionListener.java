package dsw.raf.geruMap.gui.swing.tree.controller;



import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MapTreeSelectionListener implements TreeSelectionListener{

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem)path.getLastPathComponent();
        System.out.println("Selektovan cvor:"+ treeItemSelected.getMapNode().getName());
//        if(treeItemSelected.getMapNode() instanceof Project)
//            System.out.println("changed= "+((Project) treeItemSelected.getMapNode()).isChanged());
        System.out.println("getPath: "+e.getPath());
    }
}


