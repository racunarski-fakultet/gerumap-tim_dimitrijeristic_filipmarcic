package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomOutAction extends AbstractGeruMapAction {
    public ZoomOutAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/log-out.png"));
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out");
    }

    public void actionPerformed(ActionEvent arg0) {
        MapView temp = (MapView) MainFrame.getInstance().getDesktop().getSelectedComponent();
        if(temp.getAffineTransform().getScaleX()*0.8>1)
            temp.getAffineTransform().setToScale(temp.getAffineTransform().getScaleX()*0.8,temp.getAffineTransform().getScaleY()*0.8);
        else
            temp.getAffineTransform().setToScale(1,1);

        temp.repaint();
    }
}
