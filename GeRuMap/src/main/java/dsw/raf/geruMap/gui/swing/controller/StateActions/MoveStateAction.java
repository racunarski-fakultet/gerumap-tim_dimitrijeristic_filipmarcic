package dsw.raf.geruMap.gui.swing.controller.StateActions;

import dsw.raf.geruMap.gui.swing.controller.AbstractGeruMapAction;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MoveStateAction extends AbstractGeruMapAction
{
    public MoveStateAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/move_state.png"));
        putValue(NAME, "Move");
        putValue(SHORT_DESCRIPTION, "Move nodes");
    }

    public void actionPerformed(ActionEvent arg0) {
        MainFrame.getInstance().getState_man().setMoveState();
    }
}