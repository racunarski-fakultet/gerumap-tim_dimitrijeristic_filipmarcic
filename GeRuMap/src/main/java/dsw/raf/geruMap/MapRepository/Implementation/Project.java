package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
public class Project extends MapNodeComposite
{
    private String autor;
    private String home_folder;
    private int counter =0;
    private boolean changed = true;

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

    @Override
    public String toString() {
        return "Project{" + "autor='" + autor + '\'' + ", home_folder='" +
                home_folder + '\'' + ", counter=" + counter + '\'' + "name=" + this.getName() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Project project = (Project) o;
        return counter == project.counter && changed == project.changed && home_folder.equals(project.home_folder) && children==project.children;
    }

    @Override
    public int hashCode() {
        return Objects.hash( home_folder, counter, changed, children);
    }
}
