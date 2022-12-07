package dsw.raf.geruMap.StateManager.States;

public interface AbstractState {

    void mouseClick(int x, int y);
    void mouseDrag(int x, int y);
    void mousePress(int x, int y);
    void mouseRelease(int x, int y);

}
