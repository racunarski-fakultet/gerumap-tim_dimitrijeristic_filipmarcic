package dsw.raf.geruMap.gui.swing.view;

import javax.swing.*;

public class StateToolbar extends JToolBar {
    public StateToolbar()
    {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getAct_man().getSelectionstate_act());
        add(MainFrame.getInstance().getAct_man().getAddstate_act());
        add(MainFrame.getInstance().getAct_man().getDeletestate_act());
        add(MainFrame.getInstance().getAct_man().getMovestate_act());
        add(MainFrame.getInstance().getAct_man().getLinkstate_act());
        add(MainFrame.getInstance().getAct_man().getZoomstate_act());
        add(MainFrame.getInstance().getAct_man().getStyle_act());

    }
}
