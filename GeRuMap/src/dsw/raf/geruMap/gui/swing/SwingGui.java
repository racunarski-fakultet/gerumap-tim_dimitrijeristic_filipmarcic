package dsw.raf.geruMap.gui.swing;

import dsw.raf.geruMap.core.Gui;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

public class SwingGui implements Gui
{
    public void start()
    {
        MainFrame.getInstance().setVisible(true);
    }
}
