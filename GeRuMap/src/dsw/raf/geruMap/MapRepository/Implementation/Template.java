package dsw.raf.geruMap.MapRepository.Implementation;

public class Template extends MindMap
{
    public Template(MindMap map,String name)
    {
        super(name,map);
        this.setChildren(map.getChildren());
        this.setName(name);
    }
}
