package dsw.raf.geruMap.MessageGenerator;

import dsw.raf.geruMap.core.Publisher;
import dsw.raf.geruMap.core.Subscriber;

public class MessageGenerator implements Publisher {

    public void generateMessage(String content,MessageTypes types)
    {
        Message message = new Message(content,types);
        this.publish(message);

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
        for (Subscriber subs:subscribers)
        {
            subs.update(var1);
        }
    }
}
