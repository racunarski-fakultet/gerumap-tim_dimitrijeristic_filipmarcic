package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class ZoomState implements AbstractState
{
    @Override
    public void mouseDrag(int x, int y) {

    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node) {

    }

    @Override
    public void mouseRelease(int x, int y) {

    }

    public void mouseScrolled(MouseWheelEvent e) {
        MapView mv = (MapView) MainFrame.getInstance().getDesktop().getSelectedComponent();
        AffineTransform at = mv.getAffineTransform();
        Point pt = new Point(MouseInfo.getPointerInfo().getLocation().x - mv.getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y - mv.getLocationOnScreen().y);

        if (e.getWheelRotation() < 0)
        {
            if (mv.getAffineTransform().getScaleX() * 1.25 <= 5)
            {
                at.translate(pt.x, pt.y);
                at.scale(1.25, 1.25);
                at.translate(-pt.x, -pt.y);
            }
        }
        else
        {
            if (mv.getAffineTransform().getScaleX() / 1.25 >= 1)
            {
                at.translate(pt.x, pt.y);
                at.scale(Math.pow(1.25, -1), Math.pow(1.25, -1));
                at.translate(-pt.x, -pt.y);
            }
        }

            mv.repaint();
    }
}

