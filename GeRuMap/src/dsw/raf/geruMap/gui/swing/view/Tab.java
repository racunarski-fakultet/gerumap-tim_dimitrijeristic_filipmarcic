package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.core.Subscriber;

import javax.swing.*;
import java.util.List;

public class Tab extends JPanel implements Subscriber
{
    List<MapNode> elems;
    MapNode myMap;
    @Override
    public void update(Object var1)
    {
        if (var1 instanceof MindMap)
        {
            elems = ((MindMap) var1).getChildren();
            render();
        }
    }

    private void render()
    {
        if (!elems.isEmpty())
            this.add(new JLabel(elems.get(0).getName()));
    }

    public Tab(MindMap map) {
        this.myMap = map;
        map.subscribe(this);
        update(map);
    }
}
