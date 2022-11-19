package dsw.raf.geruMap.MapRepository.Composite;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode
{
    protected List<MapNode> children;
    public MapNodeComposite(String name,MapNodeComposite parent)
    {
        super(name,parent);
        children = new ArrayList<MapNode>();
        setMaprep((MapRepositoryImpl) AppCore.getInstance().getRep());
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
            this.children.add(child);
           // if(getMaprep()!=null)
            getMaprep().publish(getMaprep().getSelectedProj());
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
            MapNodeComposite parent = child.getParent();


            parent.getChildren().remove(child);
            getMaprep().publish(getMaprep().getSelectedProj());
            //??? magija
            if(child.equals(getMaprep().getSelectedProj()))
                getMaprep().publish(parent);
            return true;
        }
    }
}
