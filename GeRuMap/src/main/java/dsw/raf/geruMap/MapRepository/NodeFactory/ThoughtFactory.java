package dsw.raf.geruMap.MapRepository.NodeFactory;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;

import java.awt.*;

public class ThoughtFactory extends NodeFactory
{
    public MapNode createNode(String name, MapNodeComposite parent)
    {
        Paint fill = new Color(255,255,255);
        return new Thought(name,parent,new Point(500,500),new Dimension(100,50),new BasicStroke(2f),fill);
    }
}
