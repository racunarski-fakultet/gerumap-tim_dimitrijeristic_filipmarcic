package dsw.raf.geruMap.Logger;

import dsw.raf.geruMap.MessageGenerator.Message;
import dsw.raf.geruMap.core.Subscriber;

public abstract class Logger implements Subscriber {
    public abstract void createLog(Message message);
}
