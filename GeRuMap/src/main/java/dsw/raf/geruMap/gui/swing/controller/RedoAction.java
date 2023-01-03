package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractGeruMapAction {
    public RedoAction()
    {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/plus.png"));
        putValue(NAME,"Redo");
        putValue(SHORT_DESCRIPTION,"Redo action");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppCore.getInstance().getGui().getCommandManager().doCommand();
    }
}
