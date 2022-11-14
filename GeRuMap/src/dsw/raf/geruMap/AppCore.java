package dsw.raf.geruMap;

import dsw.raf.geruMap.Logger.ConsoleLogger;
import dsw.raf.geruMap.Logger.FileLogger;
import dsw.raf.geruMap.Logger.Logger;
import dsw.raf.geruMap.MessageGenerator.MessageGenerator;
import dsw.raf.geruMap.core.ApplicationFramework;
import dsw.raf.geruMap.core.Gui;
import dsw.raf.geruMap.core.MapRepository;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.SwingGui;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppCore extends ApplicationFramework {
    private static AppCore instance = null;
    public static void main(String Args[])
    {

        Gui gui = new SwingGui();
        MapRepository rep = new MapRepositoryImpl();
        ApplicationFramework app_core = AppCore.getInstance();
        MessageGenerator generator = new MessageGenerator();
        Logger logger = new ConsoleLogger();
        Logger logger2 = new FileLogger();



        app_core.initialize(gui,rep,generator,logger);
        generator.subscribe(logger);
        generator.subscribe(logger2);

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
