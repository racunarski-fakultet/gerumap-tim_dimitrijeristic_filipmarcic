package dsw.raf.geruMap.core;

import java.util.ArrayList;
import java.util.List;

public interface Publisher {
    List<Subscriber> subscribers = new ArrayList<>();
    void unsubscribe(Subscriber var1);
    void subscribe(Subscriber var1);
    void publish(Object var1);
}
