package dsw.raf.geruMap.gui.swing.commands.implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.gui.swing.commands.AbstractCommand;
import dsw.raf.geruMap.gui.swing.tree.MapTree;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import java.util.List;

public class DeleteCommand extends AbstractCommand {
    private MapTreeItem parent;
    private List<MapNode> children;

    public DeleteCommand(MapTreeItem parent,  List<MapNode> children) {
        this.parent = parent;
        this.children = children;
    }
    @Override
    public void doCommand() {
        if(children == null ||  parent==null) return;


        for(MapNode child:children)
        {
            if (child instanceof Link)
            {
                MapTreeItem temp = MainFrame.getInstance().getMapTree().findNode(child);

                if (temp != null)
                    temp.delete();
            }
        }

        for(MapNode child:children)
        {
            if (child instanceof Thought)
            {
                MapTreeItem temp = MainFrame.getInstance().getMapTree().findNode(child);

                if (temp != null)
                    temp.delete();
            }
        }
        ((MindMap)parent.getMapNode()).reset_g();
    }

    @Override
    public void undoCommand() {
        if(children == null ||  parent==null) return;


        for(MapNode child: children)
        {
            if (MainFrame.getInstance().getMapTree().findNode(child) != null)
                continue;

            if (child instanceof Thought)
                MainFrame.getInstance().getMapTree().add_node(parent, child);
        }

        for(MapNode child: children)
        {
            if (MainFrame.getInstance().getMapTree().findNode(child) != null)
                continue;

            if (child instanceof Link)
                MainFrame.getInstance().getMapTree().add_node(parent, child);
        }
        ((MindMap)parent.getMapNode()).reset_g();

    }
}
