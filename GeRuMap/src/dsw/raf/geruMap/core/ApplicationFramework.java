package dsw.raf.geruMap.core;


public abstract class ApplicationFramework {

    protected Gui gui;

    public abstract void run();

    public void initialize(){
        this.gui = gui;
    }
}
