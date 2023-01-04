package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
import dsw.raf.geruMap.gui.swing.commands.implementation.DeleteCommand;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteAction extends AbstractGeruMapAction{

    public DeleteAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke((char) KeyEvent.VK_DELETE));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode()==null)
            return;
        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof ProjectExplorer)
        {
            AppCore.getInstance().getGenerator().generateMessage("ProjectExplorer ne moze biti izbrisan", MessageTypes.ERROR_MESSAGE);
            return;
        }
        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Thought)
        {
            List<MapNode> list = new ArrayList<>();
            Thought node = (Thought) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
            list.add(node);
            MindMap map = (MindMap) node.getParent();

            for (int j = map.getChildren().size() - 1; j >= 0; j--)
            {
                MapNode i = map.getChildren().get(j);

                if (i instanceof Link)
                {
                    if (((Link) i).getParentThought().equals(node) || ((Link) i).getChildThought().equals(node))
                       list.add(i);
                }
            }

            if (((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap().equals(map))
                AppCore.getInstance().getGui().getCommandManager().addCommand(new DeleteCommand(MainFrame.getInstance().getMapTree().findNode(map), list));
            else
                for(MapNode i : list)
                    MainFrame.getInstance().getMapTree().delete_node(MainFrame.getInstance().getMapTree().findNode(i));

            return;
        }


//        if (MainFrame.getInstance().getMapTree().getSelectedNode().equals(((MapRepositoryImpl) AppCore.getInstance().getRep()).getSelectedProj()))
//            ((MapRepositoryImpl) AppCore.getInstance().getRep()).publish(((MapRepositoryImpl) AppCore.getInstance().getRep()).getSelectedProj().getParent());
        MainFrame.getInstance().getMapTree().delete_node(MainFrame.getInstance().getMapTree().getSelectedNode());
//        ((MapRepositoryImpl) AppCore.getInstance().getRep()).publish(((MapRepositoryImpl) AppCore.getInstance().getRep()).getSelectedProj());
    }
}
