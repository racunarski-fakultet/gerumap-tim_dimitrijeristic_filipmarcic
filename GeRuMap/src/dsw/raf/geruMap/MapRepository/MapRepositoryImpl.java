package dsw.raf.geruMap.MapRepository;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.core.MapRepository;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MapRepositoryImpl implements MapRepository, Publisher
{
    private ProjectExplorer projectExplorer;
    private List<Subscriber> subscribers;
    private FactoryUtils factoryUtils;

    public MapRepositoryImpl() {
        projectExplorer = new ProjectExplorer("",null);
        subscribers = new ArrayList<>();
        factoryUtils = new FactoryUtils();

    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    public FactoryUtils getFactoryUtils()
    {
        return this.factoryUtils;
    }
    @Override
    public void unsubscribe(Subscriber var1) {
        subscribers.remove(var1);
    }

    @Override
    public void subscribe(Subscriber var1) {
        subscribers.add(var1);
    }

    @Override
    public void publish(Object var1) {
        for(Subscriber subs:subscribers)
        {
            subs.update(var1);
            SwingUtilities.updateComponentTreeUI(((MapTreeImplementation)MainFrame.getInstance().getMapTree()).getTreeView());
        }

    }
}
