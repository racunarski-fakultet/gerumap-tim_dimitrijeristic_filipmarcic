package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Project extends MapNodeComposite
{
    private String home_folder;
    public Project(String name)
    {
        children = new HashMap<>();
        this.setName(name);

    }
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof MindMap;}
}
