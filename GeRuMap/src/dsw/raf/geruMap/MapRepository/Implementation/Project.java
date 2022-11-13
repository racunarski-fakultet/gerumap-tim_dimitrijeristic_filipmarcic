package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Project extends MapNodeComposite
{
    private String autor;
    private String home_folder;
    public Project(String name,MapNodeComposite parent)
    {
        super(name,parent);

    }
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof MindMap;}

    @Override
    public boolean add_child(MapNode child) {
        super.add_child(child);
        ((MapRepositoryImpl)AppCore.getInstance().getRep()).publish(this);
        return true;
    }

    @Override
    public boolean delete_child(MapNode child) {
        super.delete_child(child);
        ((MapRepositoryImpl)AppCore.getInstance().getRep()).publish(this);
        return true;
    }
}
