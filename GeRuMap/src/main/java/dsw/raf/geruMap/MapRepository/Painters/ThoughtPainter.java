package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;

import javax.swing.text.Position;
import java.awt.*;

public class ThoughtPainter extends ElementPainter{
    public ThoughtPainter(Element element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g) {

       // g.setPaint(Color.RED);

        g.setStroke(element.getStroke());
        //g.draw(getShape());
        g.setPaint(element.getPaint());

        //g.fill(getShape());

        if (element instanceof Thought)
        {
            Thought temp=(Thought)element;
            System.out.println(temp.getSize());
            int width = g.getFontMetrics().stringWidth(temp.getName())*2;
            temp.setSize(new Dimension(width, (int) temp.getSize().getHeight()));
            int x = (int)temp.getPosition().getX() - temp.getSize().width/2;
            int y = (int)temp.getPosition().getY() - temp.getSize().height/2;

            if (((MapRepositoryImpl)AppCore.getInstance().getRep()).getMapSelection().getSelection().contains(element))
            {
                g.setPaint(Color.RED);
                g.drawOval(x,y,width,temp.getSize().height);
                g.setPaint(Color.WHITE);
                g.fillOval(x,y,width,temp.getSize().height);
                g.setPaint(Color.BLACK);
                g.drawString(temp.getName(), x,y);
            }
            else
            {
                g.setPaint(Color.BLACK);
                g.drawOval(x, y, width, temp.getSize().height);
                g.setPaint(Color.WHITE);
                g.fillOval(x, y, width, temp.getSize().height);
                g.setPaint(Color.BLACK);
                g.drawString(temp.getName(), x, y);
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
