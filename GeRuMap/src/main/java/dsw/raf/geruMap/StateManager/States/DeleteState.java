package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;

import java.awt.*;

public class DeleteState implements AbstractState {
    @Override
    public void mouseClick(int x, int y) {

    }

    @Override
    public void mouseDrag(int x, int y) {

    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node)
    {
        MapTreeItem temp;

        for (int i = node.getChildCount() - 1; i >= 0; i--)
        {
            temp = (MapTreeItem) node.getChildAt(i);

            Point hit = ((Element)temp.getMapNode()).getPosition();
            Dimension size = ((Element)temp.getMapNode()).getSize();

            if ((x > hit.getX() && x < hit.getX() + size.width) && (y > hit.getY() && y < hit.getY() + size.height))
            {
                temp.delete();
                break;
            }
        }
    }

    @Override
    public void mouseRelease(int x, int y) {

    }
}
