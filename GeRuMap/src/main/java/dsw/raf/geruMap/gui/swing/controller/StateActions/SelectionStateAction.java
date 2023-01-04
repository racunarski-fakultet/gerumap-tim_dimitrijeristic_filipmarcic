package dsw.raf.geruMap.gui.swing.controller.StateActions;

import dsw.raf.geruMap.gui.swing.controller.AbstractGeruMapAction;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectionStateAction extends AbstractGeruMapAction
{
    public SelectionStateAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/selection_state.png"));
        putValue(NAME, "Select");
        putValue(SHORT_DESCRIPTION, "Select 1 or more nodes");
    }

    public void actionPerformed(ActionEvent arg0) {
        MainFrame.getInstance().getState_man().setSelectionState();
    }
}
