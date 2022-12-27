package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.GhostLink;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import java.awt.*;

public class GhostPainter {
    GhostLink element;

    public GhostPainter(GhostLink element) {
        this.element = element;
    }


    public void paint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(StylePicker.getInstance().getThickness()));//(float) (2*((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).affineTransform.getScaleX())));
        g.drawLine(element.getFrom().x, element.getFrom().y, element.getTo().x, element.getTo().y);
    }
}



