package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;
import java.util.Random;

public class LinkPainter extends ElementPainter{
    public LinkPainter(Element element) {
        super(element);

    }

    @Override
    public void paint(Graphics2D g)
    {
        MapView view = (MapView) MainFrame.getInstance().getDesktop().getSelectedComponent();

        Link temp=(Link)element;
        Thought from = temp.getParentThought();
        Thought to = temp.getChildThought();
        int xFrom = (int) from.getPosition().getX();
        int yFrom = (int) from.getPosition().getY();
        int xTo = (int) to.getPosition().getX();
        int yTo = (int) to.getPosition().getY();
        g.setPaint(Color.BLACK);
        g.setStroke(new BasicStroke((float) (element.getThickness()*view.getScaling()*1.5)));
        g.drawLine(xFrom,yFrom,xTo,yTo);
    }

    @Override
    public boolean elementAt(Element element, Point pos) {

        Point from = ((Link) element).getChildThought().getPosition();
        Point to = ((Link) element).getParentThought().getPosition();

        return false;
    }
}
