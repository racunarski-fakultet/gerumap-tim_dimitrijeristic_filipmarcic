package dsw.raf.geruMap.MapRepository;

import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.view.MapView;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class MapSelection implements Publisher {
    private List<Element> selection;
    private MapView sub;

    public MapSelection() {
        this.selection = new ArrayList<>();
    }

    public void add_selected(Element elem)
    {
        selection.add(elem);
        publish(sub.getMyMap());
    }

    public void remove_selected(Element elem)
    {
        selection.remove(elem);
        publish(sub.getMyMap());
    }

    public void remove_all()
    {
        selection = new ArrayList<>();
        publish(sub.getMyMap());
    }

    @Override
    public void unsubscribe(Subscriber var1) {
        this.sub.remove((MapView)var1);
    }

    @Override
    public void subscribe(Subscriber var1) {
        this.sub = (MapView) var1;
    }

    @Override
    public void publish(Object var1) {
        sub.update(var1);
    }
}
