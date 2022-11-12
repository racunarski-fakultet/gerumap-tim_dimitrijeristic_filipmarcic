package dsw.raf.geruMap.gui.swing.view;

import javax.swing.*;

public class MenuBar extends JMenuBar
{
    public MenuBar()
    {
        JMenu file_menu = new JMenu("File");
        JMenu help_menu = new JMenu("Help");

        JMenuItem exit_a = new JMenuItem(MainFrame.getInstance().getAct_man().getExit_act());
        JMenuItem new_a = new JMenuItem(MainFrame.getInstance().getAct_man().getNewpr_act());
        JMenuItem info_a = new JMenuItem(MainFrame.getInstance().getAct_man().getInf_act());

        file_menu.add(exit_a);
        file_menu.add(new_a);
        help_menu.add(info_a);

        this.add(file_menu);
        this.add(help_menu);
    }
}
