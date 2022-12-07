package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;

import java.awt.*;

public class AddState implements AbstractState{
    @Override
    public void mouseClick(int x, int y) {
        System.out.println("ADD STATE CLICK");
    }

    @Override
    public void mouseDrag(int x, int y) {

    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node)
    {
        Dimension size = new Dimension(150,100);
        Thought child = new Thought("node", (MapNodeComposite) node.getMapNode(),new Point(x - size.width/2, y - size.height/2),size,new BasicStroke(2f),new Color(255,255,255));

        node.add(new MapTreeItem(child));
    }

    @Override
    public void mouseRelease(int x, int y) {

    }
}
