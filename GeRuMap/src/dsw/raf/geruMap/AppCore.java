package dsw.raf.geruMap;

import dsw.raf.geruMap.core.ApplicationFramework;
import dsw.raf.geruMap.core.Gui;
import dsw.raf.geruMap.gui.swing.SwingGui;

public class AppCore extends ApplicationFramework {
    private static AppCore instance = null;
    public static void main(String Args[])
    {
        Gui gui = new SwingGui();
        ApplicationFramework app_core = AppCore.getInstance();
        app_core.initialize(gui);
        app_core.run();
    }
    public static AppCore getInstance()
    {
        if (instance == null)
            instance = new AppCore();
        return instance;
    }
    private AppCore(){}

    public void run() {this.gui.start();}

}
