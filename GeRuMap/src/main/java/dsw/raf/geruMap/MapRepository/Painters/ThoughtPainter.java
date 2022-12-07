package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;

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

        if (element instanceof Thought){
            g.setPaint(Color.BLACK);
            Thought device=(Thought)element;
            System.out.println(device.getSize());
           // int x = (int) (device.getPosition().getX()+device.getSize().width/2);
            int y = (int) (device.getPosition().getY()+device.getSize().height/2);


            g.drawOval(device.getPosition().x,device.getPosition().y,device.getSize().width,device.getSize().height);
            g.setPaint(Color.WHITE);
            g.fillOval(device.getPosition().x,device.getPosition().y,device.getSize().width,device.getSize().height);
            g.setPaint(Color.BLACK);
            g.drawString(device.getName(), device.getPosition().x+2,y+3);

        }
    }

    @Override
    public boolean elementAt(Element element, Point pos) {
        return false;
    }
}
