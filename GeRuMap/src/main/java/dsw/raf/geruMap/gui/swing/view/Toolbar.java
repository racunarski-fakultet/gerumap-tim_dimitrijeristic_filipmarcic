package dsw.raf.geruMap.gui.swing.view;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class Toolbar extends JToolBar
{
    public Toolbar()
    {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getAct_man().getExit_act());
        add(MainFrame.getInstance().getAct_man().getNewpr_act());
        add(MainFrame.getInstance().getAct_man().getInf_act());
        add(MainFrame.getInstance().getAct_man().getRename_act());
        add(MainFrame.getInstance().getAct_man().getAutor_act());
        add(MainFrame.getInstance().getAct_man().getDelete_act());
        add(MainFrame.getInstance().getAct_man().getZoomIn_act());
        add(MainFrame.getInstance().getAct_man().getZoomOut_act());
        add(MainFrame.getInstance().getAct_man().getSave_act());
        add(MainFrame.getInstance().getAct_man().getLoad_act());
        add(MainFrame.getInstance().getAct_man().getSavetemplate_act());
        add(MainFrame.getInstance().getAct_man().getLoadtemplate_act());
        add(MainFrame.getInstance().getAct_man().getSaveimage_act());
        add(MainFrame.getInstance().getAct_man().getCentral_act());

    }
}
