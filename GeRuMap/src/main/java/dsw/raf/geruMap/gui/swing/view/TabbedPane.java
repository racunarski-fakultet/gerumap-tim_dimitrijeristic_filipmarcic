package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.StateManager.States.AbstractState;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.controller.StateMouseListener;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import lombok.NoArgsConstructor;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
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

        MapView temp = (MapView) getSelectedComponent();
        Boolean flag = false;
        AffineTransform at = null;
        List<MapView> temp_tabs = new ArrayList<>();

        for (int i = this.getTabCount() - 1; i >= 0; i--)
        {
            temp_tabs.add((MapView) this.getComponentAt(i));
            this.removeTabAt(i);
        }

        if (tabs == null)
            return;

        for (MapNode i : tabs)
        {
            MapView tab = new MapView((MindMap) i);

            if (temp != null && temp.getMyMap().equals(tab.getMyMap()))
            {
                flag = true;
                temp = tab;
            }

            for(MapView j : temp_tabs)
            {
                if (j.getMyMap().equals((tab.getMyMap())))
                    tab.setAffineTransform(j.getAffineTransform());
            }

            tab.update(i);
            this.addTab(i.getName(),tab);
        }

        if (flag)
        {
            this.setSelectedComponent(temp);
        }
    }
}
