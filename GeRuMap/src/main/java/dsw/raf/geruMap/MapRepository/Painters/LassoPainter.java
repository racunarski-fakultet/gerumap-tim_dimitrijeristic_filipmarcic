package dsw.raf.geruMap.MapRepository.Painters;

import dsw.raf.geruMap.MapRepository.Implementation.Lasso;

import java.awt.*;

public class LassoPainter
{
    Lasso lasso;

    public LassoPainter(Lasso lasso)
    {
        this.lasso = lasso;
    }

    public void paint(Graphics2D g)
    {
        Point start = lasso.getStart();
        Point end = lasso.getEnd();

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));

        if (start.x < end.x && start.y < end.y)
            g.drawRect(start.x, start.y, -(start.x - end.x),-(start.y - end.y));
        else if (start.x < end.x && start.y > end.y)
            g.drawRect(start.x, end.y, -(start.x - end.x),-(end.y - start.y));
        else if (start.x > end.x && start.y < end.y)
            g.drawRect(end.x, start.y, -(end.x - start.x),-(start.y - end.y));
        else
            g.drawRect(end.x, end.y, -(end.x - start.x),-(end.y - start.y));
    }
}
