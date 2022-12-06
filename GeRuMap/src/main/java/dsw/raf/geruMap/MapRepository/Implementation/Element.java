package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;

public abstract class Element extends MapNode
{
    private int boja;
    private int debljina;

    public Element(String name, MapNodeComposite parent)
    {
        super(name,parent);
    }
}
