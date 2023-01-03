package dsw.raf.geruMap.gui.swing.commands.implementation;

import java.util.List;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.gui.swing.commands.AbstractCommand;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import java.awt.*;

public class MoveCommand extends AbstractCommand {
    List<Element> nodes;

    Point start;
    Boolean init;
    public MoveCommand(List<Element> nodes, Point start)
    {
        this.nodes = nodes;
        this.start = start;
        this.init = true;
    }

    @Override
    public void doCommand()
    {
        if(init)
        {
            init = false;
            return;
        }

        for (Element cur : nodes)
        {
            if (cur.getPosition() == null)
                continue;

            cur.getPosition().x += start.x;
            cur.getPosition().y += start.y;
        }

        MainFrame.getInstance().getDesktop().getSelectedComponent().repaint();
    }

    @Override
    public void undoCommand()
    {
        for (Element cur : nodes)
        {
            if (cur.getPosition() == null)
                continue;

            cur.getPosition().x -= start.x;
            cur.getPosition().y -= start.y;
        }

        MainFrame.getInstance().getDesktop().getSelectedComponent().repaint();
    }
}
