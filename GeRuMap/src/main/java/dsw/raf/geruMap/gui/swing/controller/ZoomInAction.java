package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.LinkPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class ZoomInAction extends AbstractGeruMapAction{
    public ZoomInAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoom_in.png"));
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "Zoom in");
    }

    public void actionPerformed(ActionEvent arg0) {
        MapView temp = (MapView) MainFrame.getInstance().getDesktop().getSelectedComponent();
        AffineTransform at = temp.getAffineTransform();
        Point pt = new Point(MainFrame.getInstance().getDesktop().getSelectedComponent().getWidth()/2,MainFrame.getInstance().getDesktop().getSelectedComponent().getHeight()/2);

        if(at.getScaleX()*1.2<=5)
        {
            at.translate(pt.x ,pt.y);
            at.scale(1.25,1.25);
            at.translate(-pt.x,-pt.y);
        }

        temp.repaint();
    }
}
