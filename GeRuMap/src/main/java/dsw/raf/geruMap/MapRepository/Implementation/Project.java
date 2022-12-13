package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Project extends MapNodeComposite
{
    private String autor;
    private String home_folder;
    private int counter =0;

    public Project(String name,MapNodeComposite parent)
    {
        super(name,parent);

    }
    public int getCounter()
    {
        counter++;
        return counter;
    }
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof MindMap;}
    public void setAutor(String autor)
    {
        this.autor = autor;
        getMaprep().publish(getMaprep().getSelectedProj());

    }

}
