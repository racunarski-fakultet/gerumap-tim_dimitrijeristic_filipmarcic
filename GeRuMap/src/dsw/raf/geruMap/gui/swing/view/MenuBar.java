package dsw.raf.geruMap.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar
{
    public MenuBar()
    {
        JMenu file_menu = new JMenu("File");
        file_menu.setMnemonic(KeyEvent.VK_F);
        file_menu.add(MainFrame.getInstance().getAct_man().getExit_act());
        file_menu.add(MainFrame.getInstance().getAct_man().getNewpr_act());

        this.add(file_menu);
    }
}
