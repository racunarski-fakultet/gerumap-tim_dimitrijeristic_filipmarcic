package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.core.Subscriber;

import javax.swing.*;
import java.util.List;

public class MapView extends JPanel implements Subscriber
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
            for(MapNode element:elems)
                this.add(new JLabel(element.getName()));
    }

    public MapView(MindMap map) {
        this.myMap = map;
        map.subscribe(this);
        update(map);
    }
}
