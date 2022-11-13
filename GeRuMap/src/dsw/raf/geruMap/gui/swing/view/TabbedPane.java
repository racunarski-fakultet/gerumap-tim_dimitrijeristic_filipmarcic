package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.core.Subscriber;

import javax.swing.*;
import java.util.Map;

public class TabbedPane extends JPanel implements Subscriber {
    JTabbedPane tabs;
    Map<String, MapNode> children;

    TabbedPane()
    {

        tabs = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);

    }

    @Override
    public void update(Object var1) {
        if (var1 instanceof Project)
        {

            children = ((Project) var1).getChildren();
            for (MapNode i : children.values())
            {
                tabs.addTab(i.getName(),new JPanel());
            }
            this.add(tabs);

        }
    }
}
