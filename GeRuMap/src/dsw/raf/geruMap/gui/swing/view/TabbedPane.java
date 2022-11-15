package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.core.Subscriber;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@NoArgsConstructor
public class TabbedPane extends JTabbedPane implements Subscriber {

    List<MapNode> tabs = new ArrayList<>();

    @Override
    public void update(Object var1) {
        this.setPreferredSize(this.getParent().getSize());
        if (var1 instanceof Project)
        {
            List<MapNode> children = ((Project) var1).getChildren();
            tabs = children;

            render();
        }else if (var1 instanceof ProjectExplorer)
        {
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
            Tab tab = new Tab((MindMap) i);
            tab.update(i);
            this.addTab(i.getName(),new Tab((MindMap) i));
        }
    }
}
