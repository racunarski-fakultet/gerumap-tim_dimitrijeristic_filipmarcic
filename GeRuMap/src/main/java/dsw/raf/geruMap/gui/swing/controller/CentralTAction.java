package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CentralTAction extends AbstractGeruMapAction
{
    public CentralTAction()
    {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke((char) KeyEvent.VK_DELETE));
        putValue(SMALL_ICON, loadIcon("images/central.png"));
        putValue(NAME, "Central");
        putValue(SHORT_DESCRIPTION, "Create central thought that connects all other thoughts");
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        MindMap map = (MindMap) ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap();

        if (map == null || map.getCentral_thought() != null)
            return;

        Point pos = new Point(100,100);
        Dimension size = new Dimension(100,100);
        int thickness = 10;
        Color color = new Color(0,0,0);

        Thought thought = new Thought("Central", map, pos, size, thickness, color);

        map.setCentral_thought(thought);
        MainFrame.getInstance().getMapTree().add_node(MainFrame.getInstance().getMapTree().findNode(map), thought);
    }
}
