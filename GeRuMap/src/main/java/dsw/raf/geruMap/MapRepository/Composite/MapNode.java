package dsw.raf.geruMap.MapRepository.Composite;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class MapNode
{
    private String name;
    private MapNodeComposite parent;
    private MapRepositoryImpl maprep;

    public MapNode()
    {
        maprep= (MapRepositoryImpl) AppCore.getInstance().getRep();
    }

    public MapNode(String name, MapNodeComposite parent)
    {
        maprep = (MapRepositoryImpl) AppCore.getInstance().getRep();
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapNode mapNode = (MapNode) o;
        return name.equals(mapNode.name) && parent.equals(mapNode.parent);
    }

    public void delete()
    {
        MapNodeComposite parent = this.getParent();
        parent.delete_child(this);
    }
    public void setName(String name)
    {
        this.name=name;
        if(AppCore.getInstance().getRep()!=null)
        maprep.publish(maprep.getSelectedProj());

//        if(this instanceof Project)
//            ((MapRepositoryImpl) AppCore.getInstance().getRep()).publish(this);
//        else
//            ((MapRepositoryImpl)AppCore.getInstance().getRep()).publish(this.getParent());
    }

    public MapRepositoryImpl getMaprep()
    {
        if(maprep==null)
        {
            maprep= (MapRepositoryImpl) AppCore.getInstance().getRep();
        }
        return maprep;
    }
}
