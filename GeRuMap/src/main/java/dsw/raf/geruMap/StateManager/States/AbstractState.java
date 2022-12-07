package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;

public interface AbstractState {

    void mouseClick(int x, int y);
    void mouseDrag(int x, int y);
    void mousePress(int x, int y, MapTreeItem node);
    void mouseRelease(int x, int y);

}
