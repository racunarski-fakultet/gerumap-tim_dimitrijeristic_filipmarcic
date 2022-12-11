package dsw.raf.geruMap.MapRepository;


import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.core.MapRepository;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapRepositoryImpl implements MapRepository, Publisher
{
    private ProjectExplorer projectExplorer;
    private List<Subscriber> subscribers;
    private FactoryUtils factoryUtils;
    private Project selectedProj;
    private MapTreeItem selectedItem;
    private MapSelection mapSelection;

    public MapRepositoryImpl() {
        projectExplorer = new ProjectExplorer("",null);
        subscribers = new ArrayList<>();
        factoryUtils = new FactoryUtils();
        mapSelection = new MapSelection();
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

    public void setSelectedProj(Project selectedProj) {
        this.selectedProj = selectedProj;
        this.publish(selectedProj);
    }
}
