package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.core.Subscriber;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.Map;
@NoArgsConstructor
public class TabbedPane extends JTabbedPane implements Subscriber {

    Map<String, MapNode> children;

    @Override
    public void update(Object var1) {
        if (var1 instanceof Project)
        {

            children = ((Project) var1).getChildren();
            for (MapNode i : children.values())
            {
                this.setPreferredSize(this.getParent().getSize());
                this.addTab(i.getName(),new JPanel());
            }


        }
    }
}