package dsw.raf.geruMap.gui.swing.view;

import javax.swing.*;

public class Toolbar extends JToolBar
{
    public Toolbar()
    {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getAct_man().getExit_act());
        add(MainFrame.getInstance().getAct_man().getNewpr_act());
    }
}
