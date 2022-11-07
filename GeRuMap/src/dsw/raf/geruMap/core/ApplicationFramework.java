package dsw.raf.geruMap.core;

import dsw.raf.geruMap.gui.swing.SwingGui;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public abstract class ApplicationFramework
{
    protected Gui gui;
    protected MapRepository rep;

    public abstract void run();

    public void initialize(Gui gui,MapRepository rep)
    {
        this.gui = gui;
        this.rep = rep;
    }
}
