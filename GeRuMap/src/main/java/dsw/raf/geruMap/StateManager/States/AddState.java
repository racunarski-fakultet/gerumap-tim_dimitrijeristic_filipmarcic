package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
import dsw.raf.geruMap.gui.swing.commands.implementation.AddCommand;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;

public class AddState implements AbstractState{


    @Override
    public void mouseDrag(int x, int y) {
    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node)
    {
        try
        {
            String content = JOptionPane.showInputDialog("Unesi sadrzaj");
            if(content.isEmpty())
                throw new Exception();
            int width = content.length()*6;
            Dimension size = new Dimension(width,50);
            Thought child = new Thought(content, (MapNodeComposite) node.getMapNode(),new Point(x , y),size,StylePicker.getInstance().getThickness(), StylePicker.getInstance().getColorChooserOut().getColor());
            AppCore.getInstance().getGui().getCommandManager().addCommand(new AddCommand(node,child));
            AppCore.getInstance().getGui().getCommandManager().doCommand();
        } catch (Exception e) {
            AppCore.getInstance().getGenerator().generateMessage("Ne moze biti prazan", MessageTypes.ERROR_MESSAGE);
        }

    }

    @Override
    public void mouseRelease(int x, int y) {

    }

    @Override
    public void mouseScrolled(MouseWheelEvent e) {

    }
}
