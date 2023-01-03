package dsw.raf.geruMap.gui.swing.commands.implementation;

import com.sun.tools.javac.Main;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.gui.swing.commands.AbstractCommand;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

public class AddCommand extends AbstractCommand {

    private MapTreeItem parent;
    private MapNode child;

    public AddCommand(MapTreeItem parent, MapNode child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        if(child == null ||  parent==null) return;
        MainFrame.getInstance().getMapTree().add_node(parent,child);


    }

    @Override
    public void undoCommand() {
        if(child == null ||  parent==null) return;
        MapTreeItem childitem = MainFrame.getInstance().getMapTree().findNode(child);
        childitem.delete();


    }
}
