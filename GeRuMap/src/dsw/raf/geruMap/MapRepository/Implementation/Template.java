package dsw.raf.geruMap.MapRepository.Implementation;

public class Template extends MindMap
{
    public Template(MindMap map,String name)
    {
        this.setChildren(map.getChildren());
        this.setName(name);
    }
}
