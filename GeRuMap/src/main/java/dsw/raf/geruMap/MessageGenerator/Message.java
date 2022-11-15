package dsw.raf.geruMap.MessageGenerator;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Message {
    String content;
    MessageTypes type;
    LocalDateTime timestamp;

    public Message(String content,MessageTypes types)
    {
        this.timestamp = LocalDateTime.now();
        this.content = content;
        this.type = types;

    }

    @Override
    public String toString() {
        if(timestamp!=null)
        return this.timestamp.toString() +" "+ this.type +" "+ this.content;
        else
            return this.content;

    }
    public Message returnNoTimestamp()
    {
        Message temp = new Message(this.content,this.type);
        temp.setTimestamp(null);
        return temp;
    }
}
