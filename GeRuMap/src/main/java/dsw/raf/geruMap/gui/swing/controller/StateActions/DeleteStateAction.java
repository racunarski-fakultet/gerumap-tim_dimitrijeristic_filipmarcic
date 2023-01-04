package dsw.raf.geruMap.gui.swing.controller.StateActions;

import dsw.raf.geruMap.gui.swing.controller.AbstractGeruMapAction;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteStateAction extends AbstractGeruMapAction
{
    public DeleteStateAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete_state.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete node");
    }

    public void actionPerformed(ActionEvent arg0) {
        MainFrame.getInstance().getState_man().setDeleteState();
    }
}
