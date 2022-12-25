package dsw.raf.geruMap.core;


import dsw.raf.geruMap.Logger.Logger;
import dsw.raf.geruMap.MessageGenerator.MessageGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public abstract class ApplicationFramework
{
    protected Gui gui;
    protected MapRepository rep;
    protected MessageGenerator generator;
    protected Logger logger;
    protected Serializer serializer;
    public abstract void run();

    public void initialize(Gui gui,MapRepository rep,MessageGenerator generator,Logger logger,Serializer serializer)
    {
        this.logger = logger;
        this.generator = generator;
        this.gui = gui;
        this.rep = rep;
        this.serializer = serializer;
    }
}
