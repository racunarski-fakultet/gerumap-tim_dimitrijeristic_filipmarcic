package dsw.raf.geruMap.gui.swing.commands.implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.gui.swing.commands.AbstractCommand;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

public class LinkCommand extends AbstractCommand {
    MapTreeItem map;
    MapNode link;

    public LinkCommand(Link link, MapNode myMap) {
        this.link = link;
        this.map = MainFrame.getInstance().getMapTree().findNode(myMap);
    }

    @Override
    public void doCommand() {
        MainFrame.getInstance().getMapTree().add_node(map, link);
        ((MindMap)map.getMapNode()).reset_g();

    }

    @Override
    public void undoCommand() {
        MainFrame.getInstance().getMapTree().delete_node(MainFrame.getInstance().getMapTree().findNode(link));
        ((MindMap)map.getMapNode()).reset_g();
    }
}
