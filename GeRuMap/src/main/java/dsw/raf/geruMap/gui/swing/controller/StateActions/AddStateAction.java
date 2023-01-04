package dsw.raf.geruMap.gui.swing.controller.StateActions;

import com.sun.tools.javac.Main;
import dsw.raf.geruMap.gui.swing.controller.AbstractGeruMapAction;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddStateAction extends AbstractGeruMapAction
{
    public AddStateAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add_state.png"));
        putValue(NAME, "Add");
        putValue(SHORT_DESCRIPTION, "Add new node");
    }

    public void actionPerformed(ActionEvent arg0) {
        MainFrame.getInstance().getState_man().setAddState();
    }
}
