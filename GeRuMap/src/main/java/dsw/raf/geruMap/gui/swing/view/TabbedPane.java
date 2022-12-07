package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.controller.StateMouseListener;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TabbedPane extends JTabbedPane implements Subscriber {

    List<MapNode> tabs = new ArrayList<>();
    DescriptionLabel description = MainFrame.getInstance().getDescription();
    public TabbedPane()
    {
        super();

    }
    @Override
    public void update(Object var1) {
        this.setPreferredSize(this.getParent().getSize());
        if (var1 instanceof Project)
        {
            description.updateText(((Project) var1).getName(),((Project) var1).getAutor());
            List<MapNode> children = ((Project) var1).getChildren();
            tabs = children;

            render();
        }else if (var1 instanceof ProjectExplorer)
        {
            description.updateText(null,null);
            tabs = null;
            render();
        }
    }

    private void render()
    {
        for (int i = this.getTabCount() - 1; i >= 0; i--)
        {
            this.removeTabAt(i);

        }
        if (tabs == null)
            return;
        for (MapNode i : tabs)
        {
            MapView tab = new MapView((MindMap) i);
            tab.update(i);
            this.addTab(i.getName(),new MapView((MindMap) i));
        }
    }
}
