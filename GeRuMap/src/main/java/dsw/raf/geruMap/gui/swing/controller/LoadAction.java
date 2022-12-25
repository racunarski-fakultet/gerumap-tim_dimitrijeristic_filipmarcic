package dsw.raf.geruMap.gui.swing.controller;

import java.awt.event.ActionEvent;

public class LoadAction extends AbstractGeruMapAction {
    public LoadAction()
    {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/plus.png"));
        putValue(NAME,"Load");
        putValue(SHORT_DESCRIPTION,"Load a saved project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
