package dsw.raf.geruMap.gui.swing.tree.controller;



import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {


    private Object clickedOn =null;
    private JTextField edit=null;

    public MapTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }



    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }



    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof MapTreeItem))
            return;

        if((((MapTreeItem) clickedOn).getMapNode() instanceof ProjectExplorer))
        {
            AppCore.getInstance().getGenerator().generateMessage("ProjectExplorer ne moze biti preimenovan", MessageTypes.ERROR_MESSAGE);
            SwingUtilities.updateComponentTreeUI(((MapTreeImplementation)MainFrame.getInstance().getMapTree()).getTreeView());
            return;
        }

        MapTreeItem clicked = (MapTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());

        //sluzi za menanje imena u tree
        //MapNode node = clicked.getMapNode();

//        if(node instanceof Project)
//            ((MapRepositoryImpl) AppCore.getInstance().getRep()).publish(node);
//        else
//            ((MapRepositoryImpl)AppCore.getInstance().getRep()).publish(node.getParent());

    }



}
