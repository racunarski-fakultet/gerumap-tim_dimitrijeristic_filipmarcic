package dsw.raf.geruMap.StateManager.States;

import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.GhostLink;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;

public class AddState implements AbstractState{


    @Override
    public void mouseDrag(int x, int y) {
    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node)
    {
        System.out.println("ADD STATE press");
        String content = JOptionPane.showInputDialog("Unesi sadrzaj");
        int width = content.length()*6;
        Dimension size = new Dimension(width,50);
        Thought child = new Thought(content, (MapNodeComposite) node.getMapNode(),new Point(x , y),size,StylePicker.getInstance().getThickness(), StylePicker.getInstance().getColorChooserOut().getColor());
        MainFrame.getInstance().getMapTree().add_node(node,child);
    }

    @Override
    public void mouseRelease(int x, int y) {

    }
}
