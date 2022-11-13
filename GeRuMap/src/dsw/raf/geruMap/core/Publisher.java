package dsw.raf.geruMap.core;

public interface Publisher {
    void unsubscribe(Subscriber var1);
    void subscribe(Subscriber var1);
    void publish(Object var1);
}
