package dsw.raf.geruMap.MapRepository.NodeFactory;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import java.awt.*;
import java.util.Random;

public class ThoughtFactory extends NodeFactory
{
    public MapNode createNode(String name, MapNodeComposite parent)
    {

        Random r = new Random();
        int x = Math.abs(r.nextInt()%500);
        int y = Math.abs(r.nextInt()%500);
        Thought ret = new Thought(name,parent,new Point(x,y),new Dimension(100,50), StylePicker.getInstance().getThickness(),StylePicker.getInstance().getColorChooserOut().getColor());

        return ret;
    }
}
