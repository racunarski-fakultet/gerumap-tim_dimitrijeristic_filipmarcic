package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomInAction extends AbstractGeruMapAction{
    public ZoomInAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/log-out.png"));
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "Zoom in");
    }

    public void actionPerformed(ActionEvent arg0) {
        MapView temp = (MapView) MainFrame.getInstance().getDesktop().getSelectedComponent();
        if(temp.getAffineTransform().getScaleX()*1.2<5)
            temp.getAffineTransform().setToScale(temp.getAffineTransform().getScaleX()*1.2,temp.getAffineTransform().getScaleY()*1.2);
        else
            temp.getAffineTransform().setToScale(5,5);
        System.out.println(temp.getAffineTransform().getScaleX());

        temp.repaint();
    }
}
