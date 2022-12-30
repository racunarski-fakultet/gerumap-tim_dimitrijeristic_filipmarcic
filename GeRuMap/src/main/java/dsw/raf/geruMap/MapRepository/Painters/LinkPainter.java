package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;


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
        g.setPaint(element.getPaint());
        g.setStroke(new BasicStroke(1.5f*((float) (element.getThickness()))));//*2))));//((MindMap)view.getMyMap()).scaling))));
        if (((MapRepositoryImpl) AppCore.getInstance().getRep()).getMapSelection().getSelection().contains(element))
        {
            g.setPaint(Color.RED);
        }
        else
        {
            g.setPaint(element.getPaint());
        }
        g.drawLine(xFrom,yFrom,xTo,yTo);
    }

    @Override
    public boolean elementAt(Element element, Point pos) {

        Point from = ((Link) element).getChildThought().getPosition();
        Point to = ((Link) element).getParentThought().getPosition();
        double deltay = from.y-to.y;
        double deltax = from.x-to.x;
        double m = deltay/deltax;
        double plus = (-m* from.x)+from.y+((double) element.getThickness());
        double minus = (-m* from.x)+from.y-((double) element.getThickness());

        return((pos.y<=m*pos.x+plus && pos.y>=m*pos.x+minus)&&((pos.x>=from.x && pos.x<=to.x)||(pos.x>=to.x && pos.x<=from.x))
                &&((pos.y>=from.y && pos.y<=to.y)||(pos.y>=to.y && pos.y<=from.y)));

    }

}
