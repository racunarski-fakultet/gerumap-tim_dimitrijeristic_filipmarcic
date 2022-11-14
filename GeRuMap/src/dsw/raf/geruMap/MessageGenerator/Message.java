package dsw.raf.geruMap.MessageGenerator;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Message {
    String content;
    MessageTypes types;
    LocalDateTime timestamp;

    public Message(String content,MessageTypes types)
    {
        this.timestamp = LocalDateTime.now();
        this.content = content;
        this.types = types;

    }

    @Override
    public String toString() {
        return this.timestamp.toString() +" "+ this.types +" "+ this.content;
    }
}
