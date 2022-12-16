package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import javax.swing.text.Position;
import java.awt.*;

public class ThoughtPainter extends ElementPainter{
    public ThoughtPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {
        MapView view = (MapView) MainFrame.getInstance().getDesktop().getSelectedComponent();

        g.setStroke(new BasicStroke((float) (element.getThickness()*2)));//((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).affineTransform.getScaleX())));
        //g.setFont(new Font(g.getFont().getName(),g.getFont().getStyle(), (int) (g.getFont().getSize()*((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).affineTransform.getScaleX())));

        if (element instanceof Thought)
        {
            Thought temp=(Thought)element;
            int width = (int) (g.getFontMetrics().stringWidth(temp.getName())*2);//*((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).affineTransform.getScaleX());;
            int height = (int) (g.getFontMetrics().getHeight()*2);//)((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).affineTransform.getScaleX());;
        //    width *= scaleFactor;
            temp.setSize(new Dimension(width, height));
            int x = (int)temp.getPosition().getX() - temp.getSize().width/2;
            int y = (int)temp.getPosition().getY() - temp.getSize().height/2;

            if (((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().getSelection().contains(element))
            {
                g.setPaint(Color.RED);
                g.drawOval(x,y,width,temp.getSize().height);
                g.setPaint(Color.WHITE);
                g.fillOval(x,y,width,temp.getSize().height);
                g.setPaint(Color.BLACK);
                g.drawString(temp.getName(), x + temp.getSize().width/4,y + temp.getSize().height/2+5);
            }
            else
            {
                g.setPaint(element.getPaint());
                g.drawOval(x, y, width, temp.getSize().height);
                g.setPaint(Color.WHITE);
                g.fillOval(x, y, width, temp.getSize().height);
                g.setPaint(Color.BLACK);
                g.drawString(temp.getName(), x + temp.getSize().width/4,y + temp.getSize().height/2+5);

            }

        }
    }

    @Override
    public boolean elementAt(Element element, Point pos) {
        int x = pos.x;
        int y = pos.y;
        Point hit = element.getPosition();
        Dimension size = element.getSize();

        if ((x > hit.getX() - size.width/2 && x < hit.getX() + size.width/2) && (y > hit.getY() - size.height/2 && y < hit.getY() + size.height/2))
        {
            return true;
        }

        return false;
    }
}
