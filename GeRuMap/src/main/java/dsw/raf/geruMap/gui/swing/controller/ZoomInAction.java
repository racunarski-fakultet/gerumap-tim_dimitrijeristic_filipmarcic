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
        putValue(SMALL_ICON, loadIcon("images/log-out.png"));
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "Zoom in");
    }

    public void actionPerformed(ActionEvent arg0) {
        MapView temp = (MapView) MainFrame.getInstance().getDesktop().getSelectedComponent();
        AffineTransform at = temp.getAffineTransform();
//        MindMap temp = (MindMap) view.getMyMap();
        Point mouse = new Point(MouseInfo.getPointerInfo().getLocation().x - temp.getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - temp.getLocationOnScreen().y);
        if(at.getScaleX()*1.2<=5)
        {
//            //temp.getAffineTransform().translate(x,y);
//            Graphics2D g2 = (Graphics2D) temp.getGraphics();
//            AffineTransform at = temp.getAffineTransform();
//            //at.translate(x,y);
//            at.setToScale(at.getScaleX()*1.25,at.getScaleY()*1.25);
//            //at.translate(-x,-y);
//            temp.setAffineTransform(at);
//            //((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).paintComponents(g2);
//            //temp.getAffineTransform().translate(-x,-y);
            //double x2 = at.getTranslateX();
            //double y2 = at.getTranslateY();
            at.translate(mouse.x ,mouse.y);
            at.scale(1.25,1.25);
            at.translate(-mouse.x,-mouse.y);
        }
//
//        else
//            temp.getAffineTransform().setToScale(5,5);
        //temp.setScale(temp.getAffineTransform().getScaleX());
        System.out.println(temp.getAffineTransform().getScaleX());

//        for(ElementPainter painter : temp.getElems())
//        {
//
//                if(painter.getScaleFactor()*1.2<3)
//                {
//                    painter.setScaleFactor(painter.getScaleFactor()*1.2);
//                    if(painter instanceof ThoughtPainter)
//                    {
//                        Thought thought = (Thought) painter.getElement();
//                       // thought.setSize(new Dimension((int) (thought.getSize().width*1.2), (int) (thought.getSize().height*1.2)));
//                    }
//                }
//                else
//                    painter.setScaleFactor(3.0);
//            }
//        if(temp.scaling*1.2>3)
//            temp.scaling=3.0;
//        else
//            temp.scaling= temp.scaling*1.2;



        temp.repaint();
    }
}
