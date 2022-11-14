package dsw.raf.geruMap.Logger;

import dsw.raf.geruMap.MessageGenerator.Message;

public class ConsoleLogger extends Logger {
    @Override
    public void update(Object var1) {
        createLog((Message) var1);
    }

    @Override
    public void createLog(Message message) {
        System.out.println(message);

    }
}
