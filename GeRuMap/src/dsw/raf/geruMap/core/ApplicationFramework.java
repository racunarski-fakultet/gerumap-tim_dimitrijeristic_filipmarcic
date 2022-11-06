package dsw.raf.geruMap.core;

import dsw.raf.geruMap.gui.swing.SwingGui;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public abstract class ApplicationFramework
{
    protected Gui gui;

    public abstract void run();

    public void initialize(Gui gui)
    {
        this.gui = gui;
    }
}
