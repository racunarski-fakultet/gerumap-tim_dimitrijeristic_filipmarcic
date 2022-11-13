package dsw.raf.geruMap.MapRepository;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.core.MapRepository;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MapRepositoryImpl implements MapRepository, Publisher
{
    private ProjectExplorer projectExplorer;
    List<Subscriber> subscribers;

    public MapRepositoryImpl() {
        projectExplorer = new ProjectExplorer("",null);
        subscribers = new ArrayList<>();

    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
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
        }

    }
}
