package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;

import java.awt.event.MouseWheelEvent;

public interface AbstractState {

    void mouseDrag(int x, int y);
    void mousePress(int x, int y, MapTreeItem node);
    void mouseRelease(int x, int y);
    void mouseScrolled(MouseWheelEvent e);

}
