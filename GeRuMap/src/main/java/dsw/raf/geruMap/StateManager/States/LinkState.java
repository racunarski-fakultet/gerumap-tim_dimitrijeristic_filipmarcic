package dsw.raf.geruMap.StateManager.States;

import com.sun.tools.javac.Main;
import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.GhostLink;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.GhostPainter;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.awt.*;

public class LinkState implements AbstractState {
    GhostLink ghostLink;

    @Override
    public void mouseDrag(int x, int y) {
        this.ghostLink.setTo(new Point(x,y));


        MainFrame.getInstance().getDesktop().repaint();
    }

    @Override
    public void mousePress(int x, int y, MapTreeItem node) {
        this.ghostLink = new GhostLink(new Point(x,y));
        ghostLink.subscribe((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent());
        ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).setGhost(new GhostPainter(ghostLink));
       // MainFrame.getInstance().getDesktop().repaint();

        System.out.println("PRESS");
    }

    @Override
    public void mouseRelease(int x, int y) {
        ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).setGhost(null);
        MainFrame.getInstance().getDesktop().repaint();
        System.out.println("RELEASE");


    }
}
