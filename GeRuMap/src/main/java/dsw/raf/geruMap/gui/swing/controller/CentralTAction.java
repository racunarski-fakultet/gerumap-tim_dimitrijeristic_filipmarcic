package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MapRepository.Painters.ElementPainter;
import dsw.raf.geruMap.MapRepository.Painters.ThoughtPainter;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

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
    public void actionPerformed(ActionEvent e) {
        MapView mapView = (MapView) MainFrame.getInstance().getDesktop().getSelectedComponent();
        MindMap map = (MindMap) (mapView.getMyMap());

        if (map == null || map.getCentral_thought() != null)
            return;
        Point pos;
        if (mapView.getElems().size() != 0) {

            pos = new Point(0,0);

            int count = 0;

            for (ElementPainter elementPainter : mapView.getElems()) {
                if (elementPainter instanceof ThoughtPainter) {
                    count++;
                    pos.x += elementPainter.getElement().getPosition().x;
                    pos.y += elementPainter.getElement().getPosition().y;
                }
            }
            pos.x = pos.x / count;
            pos.y = pos.y / count;

        }
        else
            pos = new Point(MainFrame.getInstance().getDesktop().getSelectedComponent().getWidth()/2, MainFrame.getInstance().getDesktop().getSelectedComponent().getHeight()/2);


        Dimension size = new Dimension(100,100);
        int thickness = 10;
        Color color = new Color(0,0,0);

        Thought thought = new Thought("Central", map, pos, size, thickness, color);

        map.setCentral_thought(thought);
        MainFrame.getInstance().getMapTree().add_node(MainFrame.getInstance().getMapTree().findNode(map), thought);

        for(ElementPainter elementPainter: mapView.getElems())
            if(elementPainter instanceof ThoughtPainter)
            {
                Link link = new Link(thought, (Thought) elementPainter.getElement(), StylePicker.getInstance().getThickness(), StylePicker.getInstance().getColorChooserOut().getColor());
                MainFrame.getInstance().getMapTree().add_node(MainFrame.getInstance().getMapTree().findNode(map), link);
                map.reset_g();
            }
    }
}
