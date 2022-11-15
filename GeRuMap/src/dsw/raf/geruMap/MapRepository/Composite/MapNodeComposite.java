package dsw.raf.geruMap.MapRepository.Composite;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.core.MapRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode
{
    protected List<MapNode> children;
    public MapNodeComposite(String name,MapNodeComposite parent)
    {
        super(name,parent);
        children = new ArrayList<MapNode>();
    }
    protected abstract boolean allowsChild(MapNode child);

    public boolean add_child(MapNode child)
    {
        if(!this.allowsChild(child))
        {
            return false;
        }
        else
        {
            MapRepositoryImpl map = (MapRepositoryImpl)AppCore.getInstance().getRep();
            this.children.add(child);
            if(this.equals(map.getSelectedProj()))
                map.publish(child.getParent());
            return true;
        }

    }
    public boolean delete_child(MapNode child)
    {
        if(!this.allowsChild(child))
        {
            return false;
        }
        else
        {
            MapRepositoryImpl map = (MapRepositoryImpl)AppCore.getInstance().getRep();
            MapNodeComposite parent = child.getParent();

            parent.getChildren().remove(child);
            if(parent.equals(map.getSelectedProj()))
                map.publish(parent);


            return true;
        }
    }
}
