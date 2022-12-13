package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;

import java.util.HashMap;

public class ProjectExplorer extends MapNodeComposite
{
    private int counter =0;
    public ProjectExplorer(String name,MapNodeComposite parent)
    {
        super(name,null);
        setName("ProjectExplorer");

    }
    public int getCounter()
    {
        counter++;
        return counter;
    }
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof Project;}

}
