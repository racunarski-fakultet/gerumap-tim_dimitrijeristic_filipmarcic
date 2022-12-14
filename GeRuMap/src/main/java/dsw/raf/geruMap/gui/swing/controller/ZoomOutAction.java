package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import javax.swing.*;
import java.awt.*;
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
        MapView view = (MapView) MainFrame.getInstance().getDesktop().getSelectedComponent();
        MindMap temp = (MindMap) view.getMyMap();

//        if(temp.getAffineTransform().getScaleX()*0.8>1)
//            temp.getAffineTransform().setToScale(temp.getAffineTransform().getScaleX()*0.8,temp.getAffineTransform().getScaleY()*0.8);
//        else
//            temp.getAffineTransform().setToScale(1,1);
//        System.out.println(temp.getAffineTransform().getScaleX());

//        for(ElementPainter painter : temp.getElems())
//        {
//            if(painter.getScaleFactor()*0.8>1)
//            {
//                painter.setScaleFactor(painter.getScaleFactor()*0.8);
//                if(painter instanceof ThoughtPainter)
//                {
//                    Thought thought = (Thought) painter.getElement();
//               //     thought.setSize(new Dimension((int) (thought.getSize().width*0.8), (int) (thought.getSize().height*0.8)));
//                }
//            }
//            else
//            {
//                painter.setScaleFactor(1.0);
//                if(painter instanceof ThoughtPainter)
//                {
//                    Thought thought = (Thought) painter.getElement();
//                  //  thought.getSize().height=50;
//                }
//            }
//        }
//        if(temp.getScaling()*0.8<1)
//            temp.setScaling(1.0);
//        else
//            temp.setScaling(temp.getScaling()*0.8);
        if(temp.scaling*0.8<1)
            temp.scaling=1.0;
        else
            temp.scaling= temp.scaling*0.8;



        view.repaint();

//        temp.repaint();
    }
}
