package dsw.raf.geruMap.MapRepository.Implementation;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.*;

@Getter
@Setter
public class MindMap extends MapNodeComposite implements Publisher
{
    private int counter =0;
    private Thought central_thought;
    private List<Thought> vertices = new ArrayList<>();
    private HashMap<Thought,List<Thought>> edges = new HashMap<>();

    public MindMap(String name,MapNodeComposite parent)
    {
        super(name,parent);
    }
    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof Element;}
    public int getCounter()
    {
        counter++;
        return counter;
    }

    @Override
    public void unsubscribe(Subscriber var1) {
    }

    @Override
    public void subscribe(Subscriber var1) {
        subscribers.add(var1);
    }

    @Override
    public void publish(Object var1) {
        if(!subscribers.isEmpty())
            for(Subscriber sub:subscribers)
                    sub.update(var1);
    }

    @Override
    public String toString() {
        return "MindMap{" + "counter=" + counter + '\'' + "name=" + this.getName() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MindMap mindMap = (MindMap) o;
        return counter == mindMap.counter && children==mindMap.children;
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter,children);
    }

    @Override
    public boolean add_child(MapNode child)
    {
        if (!this.allowsChild(child))
            return false;

        if (child instanceof Thought)
        {
            vertices.add((Thought) child);
            edges.put((Thought) child, new ArrayList<>());
        }
        else
        {
            add_edge((Link) child);

            if (check_cycle())
            {
                remove_edge((Link) child);
                return false;
            }
        }


        super.add_child(child);

        return true;
    }

    private boolean check_cycle()
    {
        if (edges.isEmpty())
            return true;

        HashMap <Thought, Boolean> visited = new HashMap<>();

        for (Thought i : vertices)
            visited.put(i, false);

        for (Thought i : vertices)
        {
            if (!visited.get(i))
                if (dfs(i, visited, null))
                    return true;
        }

        return false;
    }

    private boolean dfs(Thought cur, HashMap<Thought, Boolean> visited, Thought parent)
    {
        visited.put(cur, true);
        Thought temp;

        if(!edges.containsKey(cur))
            edges.put(cur, new ArrayList<>());

        Iterator<Thought> it = edges.get(cur).iterator();

        while (it.hasNext())
        {
            temp = it.next();

            if (!visited.get(temp))
            {
                if (dfs(temp, visited, cur))
                    return true;
            }
            else if (parent != null && !parent.equals(temp))
                return true;
        }

        return false;
    }

    private void add_edge(Link edge)
    {
        if (!edges.containsKey(edge.getParentThought()))
            edges.put(edge.getParentThought(), new ArrayList<>());

        if (!edges.containsKey(edge.getChildThought()))
            edges.put(edge.getChildThought(), new ArrayList<>());

        edges.get(edge.getChildThought()).add(edge.getParentThought());
        edges.get(edge.getParentThought()).add(edge.getChildThought());
    }

    private void remove_edge(Link edge)
    {
        if (!edges.containsKey(edge.getParentThought()) || !edges.containsKey(edge.getChildThought()))
        edges.get(edge.getParentThought()).remove(edge.getChildThought());
        edges.get(edge.getChildThought()).remove(edge.getParentThought());
    }

    public void reset_g()
    {
        vertices = new ArrayList<>();
        edges = new HashMap<>();

        Iterator<MapNode> it = getChildren().iterator();

        while(it.hasNext())
        {
            MapNode cur = it.next();

            if (cur instanceof Thought)
            {
                vertices.add((Thought) cur);
            }
            else
            {
                add_edge((Link) cur);
            }
        }
    }

}
